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
    public static final String KEY_ANSWERED_QUESTIONS = "answered questions";
    public static final String KEY_CORRECT_ANSWERS = "correct answers";

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
    ArrayList<Integer> mAnsweredQuestions = new ArrayList<>();
    private int mCorrectAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate(Bundle) called");

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mCorrectAnswers = savedInstanceState.getInt(KEY_CORRECT_ANSWERS, 0);
            mAnsweredQuestions = savedInstanceState.getIntegerArrayList(KEY_ANSWERED_QUESTIONS);
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

        //Instance of the true button with its own listener
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
                checkIfQuestionIsAnswered();
            }
        });

        //Instance of the false button with its own listener
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(false);
                checkIfQuestionIsAnswered();
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
        outState.putIntegerArrayList(KEY_ANSWERED_QUESTIONS, mAnsweredQuestions);
        outState.putInt(KEY_CORRECT_ANSWERS, mCorrectAnswers);

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
            mCorrectAnswers+=1;
            mAnsweredQuestions.add(mCurrentIndex);
        }else{
            messageResId = R.string.incorrect;
            mAnsweredQuestions.add(mCurrentIndex);
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show();

        if(mAnsweredQuestions.size() == mQuestionBank.length){
            float accurateRate = ((float)mCorrectAnswers/(float)mQuestionBank.length) * 100;

            String userRate = String.format("Your accurate rate is: %.2f%%", accurateRate);

            Toast.makeText(this, userRate,
                    Toast.LENGTH_LONG).show();
        }

    }

    //Method to check if a question was previously answered,
    //if yes true and false buttons will be disabled
    public void checkIfQuestionIsAnswered(){
        if(mAnsweredQuestions.contains(mCurrentIndex)){
            //If the question is previously answered it will disable the buttons
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);

        }else{
            //Else set enabled the buttons
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);

        }
    }

}
