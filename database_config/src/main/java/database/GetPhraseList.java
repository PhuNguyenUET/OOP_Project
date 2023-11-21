package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetPhraseList {
    public static void getPhraseList(Connection connection) {
        File inFile = new File("src\\main\\resources\\E_V.txt");        //Personal file to get database
        File outFile = new File("src\\main\\resources\\output.txt");    //Personal file to test conversion

        try {
            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter(outFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            int count = 0;
            StringBuilder decider = new StringBuilder();
            StringBuilder word = new StringBuilder();
            StringBuilder explanation = new StringBuilder();
            StringBuilder phrase = new StringBuilder();
            StringBuilder phraseEx = new StringBuilder();


            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                count++;
                System.out.println(count);
                int i = 0;

                //Get word
                word.delete(0, word.length());
                while (line.charAt(i) != '<') {
                    word.append(line.charAt(i));
                    i++;
                }

                //Get everything else
                while (i < line.length()) {
                    //Decide what to store next
                    decider.delete(0, decider.length());
                    while (i < line.length() && line.charAt(i) == '<') {
                        while (line.charAt(i) != '>') {
                            decider.append(line.charAt(i));
                            i++;
                        }

                        decider.append('>');
                        i++;
                    }

                    if (i >= line.length()) break;

                    switch (decider.toString()) {
                        //Get word explanation
                        case ("</i></b><ul><li><font color='#cc0000'><b>"):
                        case ("</li></ul></li></ul><ul><li><font color='#cc0000'><b>"):
                        case ("</b></font></li></ul><ul><li><font color='#cc0000'><b>"):
                            explanation.delete(0, explanation.length());
                            i++;
                            while (line.charAt(i) != '<') {
                                explanation.append(line.charAt(i));
                                i++;
                            }

                            break;

                        //Get phrase that uses word
                        case ("</b></font><ul><li><b>"):
                        case ("</li><li><b>"):
                            phrase.delete(0, phrase.length());
                            while (line.charAt(i) != '<') {
                                phrase.append(line.charAt(i));
                                i++;
                            }

                            break;

                        //Get phrase explanation
                        case ("</b>"):
                            i += 2;
                            phraseEx.delete(0, phraseEx.length());
                            while (line.charAt(i) != '<') {
                                phraseEx.append(line.charAt(i));
                                i++;
                            }

                            // bufferedWriter.write("\t\t" + phrase.toString() + "\t" + phraseEx.toString() + "\n");
                            String query = "INSERT INTO phrases (wordID, word, phrase, phraseExplanation, wordExplanation) VALUES (?, ?, ?, ?, ?)";
                            try {
                                PreparedStatement preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, count);
                                preparedStatement.setString(2, word.toString());
                                preparedStatement.setString(3, phrase.toString());
                                preparedStatement.setString(4, phraseEx.toString());
                                preparedStatement.setString(5, explanation.toString());
                                preparedStatement.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            break;
                        case ("</i></b></font><ul><li><b>"):
                            i++;
                            phraseEx.delete(0, phraseEx.length());
                            while (line.charAt(i) != '<') {
                                phraseEx.append(line.charAt(i));
                                i++;
                            }

                            // bufferedWriter.write("\t\t" + phrase.toString() + "\t" + phraseEx.toString() + "\n");
                            String query2 = "INSERT INTO phrases (wordID, word, phrase, phraseExplanation, wordExplanation) VALUES (?, ?, ?, ?, ?)";
                            try {
                                PreparedStatement preparedStatement = connection.prepareStatement(query2);
                                preparedStatement.setInt(1, count);
                                preparedStatement.setString(2, word.toString());
                                preparedStatement.setString(3, phrase.toString());
                                preparedStatement.setString(4, phraseEx.toString());
                                preparedStatement.setString(5, explanation.toString());
                                preparedStatement.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            
                            break;

                        //Skip if not in any of these cases
                        default:
                            i++;
                    }
                }
            }

            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
