package com.captons.twipaedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by capton on 6/2/15.
 */
public class MyListAdapter extends ArrayAdapter<String> {
    Context mContext;// = context;
    ArrayList<String> wordList;
    TextView wordView;



    public MyListAdapter(Context context, ArrayList<String> wordList) {
        super(context, R.layout.word_entry_layout, wordList);
        this.mContext = context;
        this.wordList = wordList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.word_entry_layout, null, true);
        }
        wordView = (TextView)convertView.findViewById(R.id.word_view);
        wordView.setText(wordList.get(position));
        return convertView;
    }
}
