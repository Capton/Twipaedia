package com.captons.twipaedia;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by capton on 5/20/15.
 */
public class Mainpage extends FragmentActivity implements WordListFragment.ViewWordDefinitionListener{
    WordDefinitionFragment wordDefinitionFragment;
    WordListFragment listFragment;
    ListView listView;
    ArrayList<String> wordList = new ArrayList<String>();
    FragmentTransaction fragmentTransaction;
    Database database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage_layout);

        //database = new Database(this);
        //database.open();
        //int count = database.getWordList();
        Database.setWordList();
        Database.setDefinitionList();
        addDefaultFragment();
    }

    @Override
    public void onListitemClick(int position) {
        //wordDefinitionFragment.definitionView.setText(Database.definitionList.get(position).toString());
        //Toast.makeText(getApplicationContext(), Database.definitionList.get(position).toString(), Toast.LENGTH_SHORT).show();
        wordDefinitionFragment = new WordDefinitionFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        wordDefinitionFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_frame, wordDefinitionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addDefaultFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_frame, new WordListFragment()).commit();
    }
    private void addWordDefinitionFragment(){
        fragmentTransaction.add(R.id.fragment_frame, wordDefinitionFragment).commit();

    }
}