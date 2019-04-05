package com.bignerdranch.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextImageButton;
    private ImageButton mPreviousImageButton;
    private TextView mQuestionTextView;

    Question[] mQuestionBank = {new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateIndex(true);
                updateQuestion();
            }
        });

        //Instance of the next button with its own listener
        mNextImageButton = findViewById(R.id.next_image_button);
        mNextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateIndex(true);
                updateQuestion();
            }
        });

        //Instance of the back button with its own listener
        mPreviousImageButton = findViewById(R.id.previous_image_button);
        mPreviousImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateIndex(false);
                updateQuestion();
            }
        });

        //Instance of the true button with its own listener
        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });

        //Instance of the false button with its own listener
        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               checkAnswer(false);
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

        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct;
        }else{
            messageResId = R.string.incorrect;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show();
    }

    public void updateIndex(boolean userPressedNext){
        if(userPressedNext){
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        }else{
            if(mCurrentIndex > 0){
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
            }
        }
    }
}
