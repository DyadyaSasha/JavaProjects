package com.company;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Thread a = new MyThread();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread start: " + Thread.currentThread().getName());
            }
        });
        t.start();
            try {
            Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.start();
    }
}

class MyThread extends Thread{

    @Override
    public void run(){
//        Thread.currentThread().setName("MyThread");
        System.out.println("MyThread start: " + Thread.currentThread().getName());
    }
}

