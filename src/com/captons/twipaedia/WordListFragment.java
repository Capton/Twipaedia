package com.captons.twipaedia;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by capton on 6/7/15.
 */
public class WordListFragment extends Fragment{
    ListView listView;
    ViewWordDefinitionListener activityCallback;

    public interface ViewWordDefinitionListener{
        public void onListitemClick(int position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.word_list_layout, container, false);
        ArrayList<String> list = Database.wordList;

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
}
