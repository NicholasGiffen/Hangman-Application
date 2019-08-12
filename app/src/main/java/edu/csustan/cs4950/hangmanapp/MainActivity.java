package edu.csustan.cs4950.hangmanapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Hangman hangman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create hangman model object
        if (hangman == null) {
            hangman = new Hangman(Hangman.DEFAULT_GUESSES);
        }

        // show the number of guesses left
        TextView lblNumOfGuessesLeft = findViewById(R.id.lblNumOfGuessesLeft);
        lblNumOfGuessesLeft.setText("" + hangman.getGuessesLeft());

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.layoutGameState) == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            GameStateFragment fragment = new GameStateFragment();
            transaction.add(R.id.layoutGameState, fragment);
            transaction.commit();
        }

        if (fragmentManager.findFragmentById(R.id.layoutGameResult) == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            GameResultFragment fragment = new GameResultFragment();
            transaction.add(R.id.layoutGameResult, fragment);
            transaction.commit();
        }

//        To check if the fragment exists, we use “findFragmentByTag(“<fragment name>”) rather than “findFragmentById()” because the fragment runs in the background (without having id for the foreground process).
//        Other steps are same as what we did to run fragment.
        if (fragmentManager.findFragmentByTag("background") == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            BackgroundFragment fragment = new BackgroundFragment();
            transaction.add(fragment, "background");
            transaction.commit();
        }


    }

    public Hangman getGame() {
        return hangman;
    }


    public void play(View v) {
        Log.i("MainActivity", "play");
        EditText input = findViewById(R.id.inputLetter);
        Editable userText = input.getText();

        if (userText != null && userText.length() > 0) {
            char letter = userText.charAt(0);
            getGame().guess(letter);
            TextView lblNumOfGuessesLeft = findViewById(R.id.lblNumOfGuessesLeft);
            lblNumOfGuessesLeft.setText("" + hangman.getGuessesLeft());
//            If input letter is not null, we get the letter. o“charAt(0)” returns the first letter of a text(e.g. ‘A’ from “ABC”). oHangman.getGuessesLeft() returns the number of guesses left


            FragmentManager fragmentManager = getSupportFragmentManager();
            GameStateFragment gameStateFragment =
                    (GameStateFragment) fragmentManager.findFragmentById(R.id.layoutGameState);

            View container = gameStateFragment.getView();
            TextView lblGameState = container.findViewById(R.id.lblStateOfGame);
            lblGameState.setText(hangman.currentIncompleteWord());

            input.setText("");
//            Step1: we access “GameState” fragment (whose id is “LayoutGameState”).
//            Step2:  The  fragment  is  same  as  container  with  a  label  to  which  incomplete word  is  shown.  We  access  the  label  (lblStateOfGame)  in  the  container  by “findViewById()” method. Then, we show incomplete word to the label (by using setText() method).
//            Step3:we reset the input field for the next input.

            int result = hangman.gameOver();
            if (result != 0) {
                GameResultFragment gameResultFragment =
                        (GameResultFragment) fragmentManager.findFragmentById(R.id.layoutGameResult);

                if (result == 1) {
                    gameResultFragment.setResult("YOU WON");
                } else if (result == -1) {
                    gameResultFragment.setResult("YOU LOST");
                }
                input.setHint("");
            }

            if (hangman.getGuessesLeft() == 1) {
                BackgroundFragment backgroundFragment =
                        (BackgroundFragment)fragmentManager.findFragmentByTag("background");
                GameResultFragment gameResultFragment =
                        (GameResultFragment)fragmentManager.findFragmentById(R.id.layoutGameResult);
                gameResultFragment.setResult(backgroundFragment.warning());


            }

        }


    }

}
