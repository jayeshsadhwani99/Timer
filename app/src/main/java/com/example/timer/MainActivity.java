package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerText;
    SeekBar timerSeekBar;
    Button goButton;
    Button resetButton;
    Boolean counterIsActive = false;
    CountDownTimer timer;

    public void resetTimer() {
        timerText.setText("00:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        timer.cancel();
        goButton.setText("Go!");
    }

    public void goButtonClick(View view) {

        if(counterIsActive) {
            resetTimer();
        } else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("Stop!");
            timer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    //MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), );
                    //mPlayer.start();
                    Log.i("Finished", "Done!");
                    resetTimer();
                }
            }.start();

        }
    }

    public void updateTimer(int SecondsLeft) {
        int minutes = SecondsLeft/60;
        int seconds = SecondsLeft - minutes * 60;

        String secondsString = Integer.toString(seconds);
        if(seconds <= 9) {
            secondsString = "0" + seconds;
        }
        String minutesString = Integer.toString(minutes);
        if(minutes <= 9) {
            minutesString = "0" + minutes;
        }

        timerText.setText(minutesString + ":" + secondsString);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerText = findViewById(R.id.timerText);
        goButton = findViewById(R.id.goButton);
        resetButton = findViewById(R.id.resetButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
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
