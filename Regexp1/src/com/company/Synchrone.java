package com.company;

/**
 * Created by user on 05.05.17.
 */
public class Synchrone {
    public static void main(String[] args){

    }
}
class Counter{
    private int c =0;

    public synchronized void increment(){
        c++;
    }

    public synchronized void decrement(){
        c--;
    }

    public synchronized int value(){
        return c;
    }
}