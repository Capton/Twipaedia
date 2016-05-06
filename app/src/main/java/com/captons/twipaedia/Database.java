package com.captons.twipaedia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by capton on 6/8/15.
 */
public class Database {

    static final String DB_NAME = "twipaedia.db";
    static final int DB_VERSION = 1;
    static ArrayList<String> wordList = new ArrayList<String>() ;
    DbHelper dbHelper;
    SQLiteDatabase db;
    Context myContext;
    int numOfWords;

    public Database(Context context){

        this.myContext = context;
    }

    public void open() throws IOException, SQLException {
        dbHelper = new DbHelper(this.myContext);
        dbHelper.createDataBase();
        dbHelper.openDataBase();

    }

    public void close()
    {
        dbHelper.close();
    }

    public ArrayList<String> loadWordList(){
        if(!wordList.isEmpty()){
            wordList.clear();
        }
        Cursor cursor = dbHelper.myDataBase.rawQuery("SELECT * FROM local_word", null);

        cursor.moveToFirst();
        numOfWords = 0;
        while (!cursor.isAfterLast()) {
            WordEntry wordEntry = new WordEntry();
            wordEntry.$word = cursor.getString(1).toString();
            wordEntry.id = Integer.parseInt(cursor.getString(0));


            wordList.add(wordEntry.$word);
            numOfWords++;
            cursor.moveToNext();
        }
        return wordList;
    }

    public String loadWordDefinition(int wordId){

        String definitions = new String();
            Cursor cursor1 = dbHelper.myDataBase.rawQuery(
                    "SELECT definition FROM word_definition WHERE local_id = ?",
                    new String[]{ String.valueOf(wordId)});
            cursor1.moveToFirst();
            while (!cursor1.isAfterLast()){
                definitions = cursor1.getString(0).toString();
                cursor1.moveToNext();
            }
            /*
            Cursor cursor2 = dbHelper.myDataBase.rawQuery(
                    "SELECT english FROM local_to_english WHERE local_id = ?",
                    new String[]{ String.valueOf(wordEntry.id)});
            cursor2.moveToFirst();
            while (!cursor2.isAfterLast()){
                wordEntry.englishList.add(cursor2.getString(0).toString());
            }
            */

        return definitions;
    }


}
