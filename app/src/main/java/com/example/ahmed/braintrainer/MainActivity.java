package com.example.ahmed.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textViewTimer, textViewCounter, textViewSum, textViewCorrect, textViewResult;
    Button button1, button2, button3, button4, butnPlayAgain, btnGo;
    ArrayList<Integer> arr = new ArrayList<>();
    int locationOfCorrectAns;
    int locationOfInCorrectAns;
    int score = 0;
    int numberOfQuestion = 0;

    public void choosseAnswer(View view) {
        if (view.getTag().equals(Integer.toString(locationOfCorrectAns))) {
            score++;
            textViewCorrect.setText("correct");
        } else {
            textViewCorrect.setText("wrong!");
        }
        numberOfQuestion++;
        textViewCounter.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
        generateQuestion();
    }

    public void start(View view) {
        btnGo.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.btn_play_again));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        countDowneTimer();
        start(findViewById(R.id.but_go));
        playAgain(findViewById(R.id.btn_play_again));
    }

    private void countDowneTimer() {
        new CountDownTimer(20100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(String.valueOf(millisUntilFinished / 1000 + "s"));
            }

            @Override
            public void onFinish() {
                butnPlayAgain.setVisibility(View.VISIBLE);
                textViewTimer.setText("0s");
                textViewCounter.setText("0/0");
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
                button4.setClickable(false);
                textViewCorrect.setText("");
                textViewResult.setText("your score:" + Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
            }
        }.start();
    }

    private void generateQuestion() {
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        textViewSum.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOfCorrectAns = random.nextInt(4);
        arr.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAns) {
                arr.add(a + b);
            } else {
                locationOfInCorrectAns = random.nextInt(41);
//                while (locationOfInCorrectAns == a + b) {
//                    locationOfInCorrectAns = random.nextInt(41);
//                }
                arr.add(locationOfInCorrectAns);
            }
        }
        button1.setText(Integer.toString(arr.get(0)));
        button2.setText(Integer.toString(arr.get(1)));
        button3.setText(Integer.toString(arr.get(2)));
        button4.setText(Integer.toString(arr.get(3)));
    }

    private void initView() {
        textViewSum = (TextView) findViewById(R.id.text_sum);
        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        button3 = (Button) findViewById(R.id.btn3);
        button4 = (Button) findViewById(R.id.btn4);
        btnGo = (Button) findViewById(R.id.but_go);
        butnPlayAgain = (Button) findViewById(R.id.btn_play_again);
        textViewCorrect = (TextView) findViewById(R.id.text_correct);
        textViewCounter = (TextView) findViewById(R.id.text_counter);
        textViewTimer = (TextView) findViewById(R.id.text_timer);
        textViewResult = (TextView) findViewById(R.id.text_result);
    }

    public void playAgain(View view) {
        score = 0;
        numberOfQuestion = 0;
        butnPlayAgain.setVisibility(View.INVISIBLE);
        textViewTimer.setText("0s");
        textViewCorrect.setText("");
        textViewResult.setText("");
        textViewCounter.setText("0/0");
        generateQuestion();
        countDowneTimer();
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
        button4.setClickable(true);
    }
}
