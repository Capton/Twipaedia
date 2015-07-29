package com.captons.twipaedia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by capton on 6/8/15.
 */
public class Database {

    static ArrayList<String> wordList = new ArrayList<String>() ;
    static ArrayList<String> definitionList = new ArrayList<String>() ;
    static final String DB_NAME = "twipaedia.db";
    static final int DB_VERSION = 1;
    DbHelper dbHelper;
    SQLiteDatabase db;

    public Database(Context context){
        dbHelper = new DbHelper(context);
    }

    void open() throws android.database.SQLException{
        db = dbHelper.getReadableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    public int getWordList(){
        Cursor cursor = db.rawQuery("SELECT word FROM local_word", null);
        //cursor.moveToFirst();
        int count = 0;
        /*
        while (!cursor.isAfterLast()) {
            //String $word = cursor.getString(1).toString();
            //wordList.add($word);
            count++;
            cursor.moveToNext();
        }
        */
        return count;
    }










    public static void setWordList() {
        Database.wordList.add("first word");
        Database.wordList.add("second word");
    }

    public static void setDefinitionList() {
        Database.definitionList.add("Article One\n\nExcepteur pour-over occaecat squid biodiesel umami gastropub, nulla laborum salvia dreamcatcher fanny pack. Ullamco culpa retro ea, trust fund excepteur eiusmod direct trade banksy nisi lo-fi cray messenger bag. Nesciunt esse carles selvage put a bird on it gluten-free, wes anderson ut trust fund twee occupy viral. Laboris small batch scenester pork belly, leggings ut farm-to-table aliquip yr nostrud iphone viral next level. Craft beer dreamcatcher pinterest truffaut ethnic, authentic brunch. Esse single-origin coffee banksy do next level tempor. Velit synth dreamcatcher, magna shoreditch in american apparel messenger bag narwhal PBR ennui farm-to-table.");
        Database.definitionList.add("Article Two\n\nVinyl williamsburg non velit, master cleanse four loko banh mi. Enim kogi keytar trust fund pop-up portland gentrify. Non ea typewriter dolore deserunt Austin. Ad magna ethical kogi mixtape next level. Aliqua pork belly thundercats, ut pop-up tattooed dreamcatcher kogi accusamus photo booth irony portland. Semiotics brunch ut locavore irure, enim etsy laborum stumptown carles gentrify post-ironic cray. Butcher 3 wolf moon blog synth, vegan carles odd future.");
    }
}
