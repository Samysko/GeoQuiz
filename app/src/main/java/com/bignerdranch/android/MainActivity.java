package com.bignerdranch.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    Question[] mQuestionBank = {new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;
    ArrayList<Integer> answeredQuestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate(Bundle) called");

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = findViewById(R.id.question_text_view);

        //Instance of the next button with its own listener
        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState(Bundle outState)");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    //Method to update the current question inside the QuestionTextView
    public void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        checkIfQuestionIsAnswered();
    }

    //Method to check whether the user's answer was correct
    public void checkAnswer(Boolean userPressedTrue){
        int messageResId;
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct;
        }else{
            messageResId = R.string.incorrect;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show();
    }

    //Method to check if a question was previously answered,
    //if yes true and false buttons will be disabled
    public void checkIfQuestionIsAnswered(){
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);

        if(answeredQuestions.contains(mCurrentIndex)){
            //If the question is previously answered it will disable the buttons
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);

        }else{
            //Instance of the true button with its own listener
            mTrueButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    checkAnswer(true);
                    answeredQuestions.add(mCurrentIndex);
                }
            });

            //Instance of the false button with its own listener
            mFalseButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    checkAnswer(false);
                    answeredQuestions.add(mCurrentIndex);
                }
            });

        }
    }

}
