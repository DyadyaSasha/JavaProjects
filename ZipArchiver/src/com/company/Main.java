package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Archiver zipper = new ZipArchiver();
        try {
            zipper.createZipArchiveWithFile(args[0], args[1]);
        } catch (IOException e) {
            System.out.println("Cannot read file. Exception: " + e.toString());
        } catch (Exception e){
            System.out.println("Exception: " + e.toString());
        }
        System.out.println("Success!");
    }
}
