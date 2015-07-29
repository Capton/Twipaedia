package com.captons.twipaedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by capton on 6/8/15.
 */

public class WordDefinitionFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    TextView definitionView;

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
        Database.setDefinitionList();
        definitionView = (TextView)view.findViewById(R.id.word_definition_view);
        int position = getArguments().getInt("position");
        definitionView.setText(Database.definitionList.get(position).toString());
        // Inflate the layout for this fragment
        return view;

    }
/*
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
        TextView definitionview = (TextView) getActivity().findViewById(R.id.word_definition_view);
        definitionview.setText(Database.definitionList.get(position));
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
    */
}
