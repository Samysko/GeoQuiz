package com.bignerdranch.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mTrueButton;
    Button mFalseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instance of the true button with its own listener
        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //This code is part of the chapter code, not part of the challenge
//                Toast.makeText(MainActivity.this,
//                        R.string.correct, Toast.LENGTH_LONG).show();

                //CHALLENGE: Brief message to the final user that indicates the correct answer
                //at the top center of the activity
                Toast toast = Toast.makeText(MainActivity.this, R.string.true_button,
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, Gravity.AXIS_X_SHIFT, Gravity.AXIS_Y_SHIFT);
                toast.show();
            }
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               //This code is part of the chapter code, not part of the challenge
//               Toast.makeText(MainActivity.this,
//                       R.string.incorrect, Toast.LENGTH_LONG).show();

               //CHALLENGE: Brief message to the final user that indicates the incorrect answer
               //at the top center of the activity
               Toast toast = Toast.makeText(MainActivity.this,
                       R.string.incorrect, Toast.LENGTH_LONG);
               toast.setGravity(Gravity.TOP, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL);
               toast.show();

           }
        });
    }
}
