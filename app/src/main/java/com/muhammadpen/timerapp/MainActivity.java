package com.muhammadpen.timerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSlider;
    Button timerStartButton;
    TextView timerTextView;

    public void timerUpdate(int progress){
        int timerMinutes = (int) progress / 60;
        int timerSeconds = (int) progress - timerMinutes * 60;

        timerTextView.setText(Integer.toString(timerMinutes) + ":" + Integer.toString(timerSeconds));

    }
        public void timerButtonPress(View view) {

            new CountDownTimer(timerSlider.getProgress() , 1000){

                @Override
                public void onTick(long l) {

                    timerUpdate((int) l);

                }

                @Override
                public void onFinish() {

                }
            };

        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSlider = (SeekBar) findViewById(R.id.timerSeekBar);
        timerStartButton = (Button) findViewById(R.id.timerStartButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        timerSlider.setMax(600);
        timerSlider.setProgress(0);

        timerSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean activeUser) {

                timerUpdate(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
