package com.muhammadpen.timerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSlider;
    Button timerControlButton;
    TextView timerTextView;
    boolean timerIsActive = false;
    CountDownTimer timeCounter;

    public void timerUpdate(int progress){
        int timerMinutes = (int) progress / 60;
        int timerSeconds = (int) progress - timerMinutes * 60;

        timerTextView.setText(Integer.toString(timerMinutes) + ":" + Integer.toString(timerSeconds));

    }
        public void timerButtonPress(View view) {

            if (timerIsActive = false) {
                timerIsActive = true;
                timerSlider.setEnabled(false);
                timerControlButton.setText("Stop");

                timeCounter = new CountDownTimer(timerSlider.getProgress() * 1000 + 100, 1000) {

                    @Override
                    public void onTick(long l) {

                        timerUpdate((int) l / 1000);

                    }

                    @Override
                    public void onFinish() {

                        timerTextView.setText("0:00");
                        MediaPlayer timerEndSound = MediaPlayer.create(getApplicationContext(), R.raw.torturesound);
                        timerEndSound.start();
                    }
                }.start();

            }else {
                timerTextView.setText("0:30");
                timerSlider.setProgress(30);

                timerControlButton.setText("Start");
                timerSlider.setEnabled(true);
                timerIsActive = false;
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSlider = (SeekBar) findViewById(R.id.timerSeekBar);
        timerControlButton = (Button) findViewById(R.id.timerControlButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        timerSlider.setMax(600);
        timerSlider.setProgress(30);

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
