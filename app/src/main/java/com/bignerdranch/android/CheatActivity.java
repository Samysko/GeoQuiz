package com.bignerdranch.android;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.answer_is_true";
    private static String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.answer_shown";

    Button mAnswerButton;
    TextView mAnswerTextView;

    boolean mAnswerIsTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerButton = findViewById(R.id.answer_button);
        mAnswerTextView = findViewById(R.id.answer_text_view);

        mAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);

                int cx = mAnswerButton.getWidth() / 2;
                int cy = mAnswerButton.getHeight() / 2;
                float radius = mAnswerButton.getWidth();
                Animator anim = ViewAnimationUtils
                        .createCircularReveal(mAnswerButton, cx, cy, radius, 0);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mAnswerButton.setVisibility(View.INVISIBLE);
                    }
                });

                anim.start();
            }
        });

    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue ){
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    public void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

}
