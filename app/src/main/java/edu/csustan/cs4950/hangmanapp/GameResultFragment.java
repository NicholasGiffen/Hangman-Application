package edu.csustan.cs4950.hangmanapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameResultFragment extends Fragment {
    private TextView lblGameResult;

    public GameResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        buildGUI(container);
        return super.onCreateView(inflater, container,
                savedInstanceState);
        //commented out since we're creating onCreateView from
        //code, not XML.

//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
//        return textView;
    }

    public void buildGUI(ViewGroup container) {
        if (lblGameResult == null) {
            lblGameResult = new TextView(getActivity());
            lblGameResult.setGravity(Gravity.CENTER);
            container.addView(lblGameResult);
        }
    }

    public void onStart() {
        super.onStart();
        lblGameResult.setText("GOOD LUCK");
    }

    //Displays whether or not the user wins or loses
    public void setResult(String result) {
        lblGameResult.setText(result);
    }

}
