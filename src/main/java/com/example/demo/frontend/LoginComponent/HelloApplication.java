package com.example.demo.frontend.LoginComponent;

//import com.example.demo.backend.TextToSpeech;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
        import javafx.stage.Stage;
//import com.sun.speech.freetts.*;
        import java.io.IOException;

/*import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;*/
public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        primaryStage.setTitle("JavaFX FXML Example");
        primaryStage.setScene(new Scene(root, 1536, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
//        Connection connection= userDatabaseConnect.connect();
//        userDatabaseSQL.createtable(connection);
//        userDatabaseSQL.insertIntoDict(connection,"name1","pass1",1);
//        userDatabaseSQL.showAll(connection);
        launch(args);
    }
}