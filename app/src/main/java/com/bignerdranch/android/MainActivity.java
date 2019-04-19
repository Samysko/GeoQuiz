package com.bignerdranch.android;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final String KEY_INDEX = "index";
    public static final String KEY_IS_CHEATER = "is cheater";
    public static final String KEY_CHEATED_QUESTIONS_LIST = "cheated question list";
    public static final int CHEAT_CODE = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;

    Question[] mQuestionBank = {new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;
    private boolean mIsCheater;
    private ArrayList<Boolean> mCheatedQuestionsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater = savedInstanceState.getBoolean(KEY_IS_CHEATER, false);
            mCheatedQuestionsList = fillBooleanArrayListFromBooleanArray(
                    savedInstanceState.getBooleanArray(KEY_CHEATED_QUESTIONS_LIST));

        }

        Log.d(TAG, "onCreate(Bundle) called");

        mQuestionTextView = findViewById(R.id.question_text_view);
        mCheatButton = findViewById(R.id.cheat_button);
        mNextButton = findViewById(R.id.next_button);
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);

        //Set the listener to the next button
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                mIsCheater = false;
            }
        });

        //Set the listener to the true button
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });

        //Set the listener to the false button
        mFalseButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               checkAnswer(false);
           }
        });

        //Set the listener to the cheat button
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent =
                        CheatActivity.newIntent(MainActivity.this, answerIsTrue);
                startActivityForResult(intent, CHEAT_CODE);
            }
        });

        updateQuestion();
    }

    //Method to update the current question inside the QuestionTextView
    public void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    //Method to check whether the user's answer was correct
    public void checkAnswer(Boolean userPressedTrue){
        int messageResId;
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        if(mCheatedQuestionsList.get(mCurrentIndex)){
            messageResId = R.string.judgment_toast;

        }else{
            if(userPressedTrue == answerIsTrue){
                messageResId = R.string.correct;

            }else{
                messageResId = R.string.incorrect;

            }

        }

        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show();
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
        outState.putBoolean(KEY_IS_CHEATER, mIsCheater);
        outState.putBooleanArray(KEY_CHEATED_QUESTIONS_LIST, getBooleanArray(mCheatedQuestionsList));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode != RESULT_OK){
            return;
        }

        if(requestCode == CHEAT_CODE){
            if(data == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
            mCheatedQuestionsList.add(mCurrentIndex, mIsCheater);
        }

    }

    private boolean[] getBooleanArray(ArrayList<Boolean> booleanArrayList){
        boolean[] booleanArray = new boolean[booleanArrayList.size()];

        for(int i = 0; i < booleanArray.length; i++){
            booleanArray[i] = booleanArrayList.get(i);
        }

        return booleanArray;
    }

    private ArrayList<Boolean> fillBooleanArrayListFromBooleanArray(boolean[] booleans){
        ArrayList<Boolean> booleanArrayList = new ArrayList<>();

        for(int i = 0; i < booleans.length; i++){
            booleanArrayList.add(i, booleans[i]);
        }

        return booleanArrayList;
    }
}
