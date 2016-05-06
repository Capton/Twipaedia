package com.captons.twipaedia;

import java.util.ArrayList;

/**
 * Created by capton on 7/29/15.
 */
public class WordEntry {
    int id;
    String $word;
    String $audio;
    ArrayList<String> definitionList;
    ArrayList<String> englishList;

    public WordEntry(){
        definitionList = new ArrayList<String>();
        englishList = new ArrayList<String>();
    }
}
