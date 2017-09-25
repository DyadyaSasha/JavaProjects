package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        API api = new API();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = "";
        try {
            string = reader.readLine();
            String response = api.translate(string);
            ObjectMapper mapper = new ObjectMapper();
            /**
             * маппинг происходит через геттеры и
             * сеттеры указаного класса
             * */
            ApiResponse apiResponse = mapper.readValue(response, ApiResponse.class);
            apiResponse.getText().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
