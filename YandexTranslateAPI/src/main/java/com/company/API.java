package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API implements TranslateAPI{

    @Override
    public String translate(String word) throws IOException {
        String urlString = "https://translate.yandex.net/api/v1.5/tr.json/" +
                "translate?key=trnsl.1.1.20170921T151352Z.611b73992d0446aa." +
                "42b48c5089b73aaf0dce98e1414972674afcbf6a&text="
                + word + "&lang=en-ru";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() != 200){
            System.out.println("FAIL");
            System.out.println(new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine());
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = reader.readLine();
        return response;
    }
}
