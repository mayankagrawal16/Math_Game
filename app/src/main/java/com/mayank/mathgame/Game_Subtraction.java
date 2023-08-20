package com.mayank.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Game_Subtraction extends AppCompatActivity {

    TextView score,life,time;
    TextView question;
    EditText answer;
    Button ok,next;
    Random random=new Random();
    int num1,num2;
    int realAnswer;
    int userAnswer;
    int userScore=0;
    int userLife=3;
    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS=10000;
    boolean timer_running;
    long TIME_LEFT_IN_MILIS= START_TIMER_IN_MILIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_addition);

        score=findViewById(R.id.textScore);
        life=findViewById(R.id.textLife);
        time=findViewById(R.id.textTime);
        answer=findViewById(R.id.editAnswer);
        question=findViewById(R.id.textQuestion);
        ok=findViewById(R.id.buttoncheck);
        next=findViewById(R.id.buttonNext);

        gameContinue();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userAnswer= Integer.valueOf(answer.getText().toString());
                pauseTimer();
                if(userAnswer==realAnswer)
                {
                    userScore=userScore+10;
                    question.setText("Correct Answer");
                    score.setText(""+userScore);
                }
                else
                {
                    userLife=userLife-1;
                    question.setText("Wrong Answer");
                    life.setText(""+userLife);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(" ");
                gameContinue();
                resetTimer();
                if(userLife<=0)
                {
                    Toast.makeText(getApplicationContext(),"Game Over",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Game_Subtraction.this, Result.class);
                    startActivity(intent);
                    intent.putExtra("score",userScore);
                    finish();
                }
                else {
                    gameContinue();
                }
            }
        });

    }
    public void gameContinue()
    {
        num1=random.nextInt(100);
        num2=random.nextInt(100);
        realAnswer=num1-num2;
        if(num1>num2) {
            question.setText(num1 + " - " + num2);
        }
        else {
            question.setText(num2 + " - " + num1);
        }
        StartTimer();

    }
    public void StartTimer()
    {
        timer=new CountDownTimer(TIME_LEFT_IN_MILIS,1000) {
            @Override
            public void onTick(long l) {
                TIME_LEFT_IN_MILIS=l;
                UpdateText();
            }

            @Override
            public void onFinish() {
                timer_running=false;
                pauseTimer();
                resetTimer();
                UpdateText();
                userLife=userLife-1;
                life.setText(""+userLife);
                question.setText("Sorry! Time Over");
            }
        }.start();
        timer_running=true;
    }
    public void UpdateText()
    {
        int second= (int) ((TIME_LEFT_IN_MILIS/1000)%60);
        String time_left=String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);
    }
    public void pauseTimer()
    {
        timer.cancel();
        timer_running=false;
    }
    public void resetTimer()
    {
        TIME_LEFT_IN_MILIS=START_TIMER_IN_MILIS;
        UpdateText();
    }
}