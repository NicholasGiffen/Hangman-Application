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
public class GameStateFragment extends Fragment {


    public GameStateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_state, container, false);
    }

    public void onStart() {
        super.onStart();
        View fragmentView = getView();
        TextView lblStateOfGame =
                fragmentView.findViewById(R.id.lblStateOfGame);
        MainActivity mainActivity = (MainActivity)getActivity();
        lblStateOfGame.setText(mainActivity.getGame().currentIncompleteWord());
    }

}
