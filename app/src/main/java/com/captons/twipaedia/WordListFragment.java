package com.captons.twipaedia;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by capton on 6/7/15.
 */
public class WordListFragment extends Fragment{
    ListView listView;
    ViewWordDefinitionListener activityCallback;
    Database db;

    public interface ViewWordDefinitionListener{
        public void onListitemClick(int position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new Database(getActivity().getApplicationContext());
        try {
            db.open();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.word_list_layout, container, false);

        ArrayList<String> list = new ArrayList<String>();
        for (String newEntry : db.loadWordList()){
            list.add(newEntry);
        }

        listView = (ListView)view.findViewById(R.id.words_list_view);
        ListAdapter adapter = new MyListAdapter(getActivity().getBaseContext(), list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView word = (TextView)view.findViewById(R.id.word_view);
                activityCallback.onListitemClick(position);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCallback = (ViewWordDefinitionListener)activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement ViewWordDefinitionListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
