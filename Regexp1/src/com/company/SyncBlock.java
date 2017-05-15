package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 05.05.17.
 */
public class SyncBlock {
    private List nameList = new ArrayList();
    private int nameCount = 0;
    public void addName(String name){
        synchronized (this){
            nameCount++;
        }
        nameList.add(name);
    }
}
