package com.bignerdranch.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static String QUESTION_ANSWE_EXTRA_KEY = "com_bignerdranch_android_question_answer";

    Button mAnswerButton;
    TextView mAnswerTextView;

    boolean mAnswerIsTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(QUESTION_ANSWE_EXTRA_KEY, false);

        mAnswerButton = findViewById(R.id.answer_button);
        mAnswerTextView = findViewById(R.id.answer_text_view);

        mAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button;
                }else{
                    mAnswerTextView.setText(R.string.false_button);
                }
            }
        });

    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue ){
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(QUESTION_ANSWE_EXTRA_KEY, answerIsTrue);
        return intent;
    }
}
