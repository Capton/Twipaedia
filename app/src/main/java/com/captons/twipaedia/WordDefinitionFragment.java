package com.captons.twipaedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by capton on 6/8/15.
 */

public class WordDefinitionFragment extends Fragment implements View.OnClickListener {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    TextView definitionView;
    ImageButton nextBtn, prevBtn;
    Database db = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        //if (savedInstanceState != null) {
          //  mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        //}
        View view = inflater.inflate(R.layout.word_definition_layout, container, false);
        definitionView = (TextView)view.findViewById(R.id.word_definition_view);
        nextBtn = (ImageButton) view.findViewById(R.id.next_btn);
        prevBtn = (ImageButton) view.findViewById(R.id.prev_btn);
        nextBtn.setOnClickListener(this);
        prevBtn.setOnClickListener(this);
        int position = getArguments().getInt("position");
        db = new Database(getActivity().getApplicationContext());
        try {
            db.open();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateArticleView(mCurrentPosition);
        }
    }

    public void updateArticleView(int position) {
        mCurrentPosition = position++;
        definitionView.setText(db.loadWordDefinition(position));
        checkBtns();
    }

    private void checkBtns() {
        if(mCurrentPosition==0){
            //prevBtn.setVisibility(View.INVISIBLE);
            prevBtn.setEnabled(false);
        }
        else if(mCurrentPosition==Database.wordList.size()-1){
            //nextBtn.setVisibility(View.INVISIBLE);
            nextBtn.setEnabled(false);
        }
        else{
            //prevBtn.setVisibility(View.VISIBLE);
            //nextBtn.setVisibility(View.VISIBLE);
            prevBtn.setEnabled(true);
            nextBtn.setEnabled(true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next_btn:
                int id = ++mCurrentPosition;
                definitionView.setText(db.loadWordDefinition(++id));
                break;
            case R.id.prev_btn:
                int id2  = --mCurrentPosition;
                definitionView.setText(db.loadWordDefinition(++id2));
                break;
        }
        checkBtns();
    }

    @Override
    public void setExitTransition(Object transition) {
        super.setExitTransition(transition);
    }
}
