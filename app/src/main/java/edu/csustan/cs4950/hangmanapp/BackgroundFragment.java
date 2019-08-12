package edu.csustan.cs4950.hangmanapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BackgroundFragment extends Fragment {


    public BackgroundFragment() {
        // Required empty public constructor
    }

//    Remove existing “onCreateView”, and add warning()method that  returns  warning message.
    public String warning() {
        return "ONLY 1 LEFT";
    }

}






    //Danny had us get rid of to make an invisible fragment

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
//        return textView;
//    }



