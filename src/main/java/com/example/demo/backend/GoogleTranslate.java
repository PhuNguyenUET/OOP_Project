package com.example.demo.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleTranslate {
    private static String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbxM4tUW72--uXKWjDEuS8XZRmRhoXIIhhr3KFwTYiNA_V867liXRI_UdhwZoRHAzjEk/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    //Test o day nhe
    //
    //
    //
    // public static void main(String[] args) throws IOException{
    //     //Nhap text can translate o day
    //     String text = "Nếu các ngươi thành tâm muốn hỏi, thì bọn ta sẵn sàng trả lời";

    //     //Dich tu viet-anh thi langFrom = vi, langTo = en, dich anh-viet thi nguoc lai
    //     System.out.println(translate("vi", "en", text));
    // }
}