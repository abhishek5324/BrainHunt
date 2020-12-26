package com.example.brainhunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answeres = new ArrayList<Integer>();
    int locationOfCorrectAnswere;
    int score=0;
    int numberOfQues=0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView resulttextview;
    TextView pointstextView;
    TextView sumTextView;
    TextView timer;
    Button playAgain;
    ConstraintLayout layout;


    public void playagain(View view)
    {
        generateQuestion();
        score = 0;
        numberOfQues = 0;

        timer.setText("30s");
        pointstextView.setText("0/0");
        resulttextview.setText("");
        playAgain.setVisibility(View.INVISIBLE);

        new CountDownTimer(31000, 1000)
        {
            @Override
            public void onTick(long l)
            {
                timer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish()
            {   playAgain.setVisibility(View.VISIBLE);
                timer.setText("0s");
                resulttextview.setText("Your score:" +" " + Integer.toString(score)+ "/" + Integer.toString(numberOfQues));
                // generateQuestion();
            }
        }.start();

    }

    public void generateQuestion()
    {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+" + "+ Integer.toString(b));

        locationOfCorrectAnswere = rand.nextInt(4);
        answeres.clear();
        int incorrectAns;


        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswere)
            {
                answeres.add(a+b);
            }
            else
            {
                incorrectAns = rand.nextInt(41);
                while(incorrectAns == a+b)
                {
                    incorrectAns = rand.nextInt(41);
                }
                answeres.add(incorrectAns);
            }
        }

        button0.setText(Integer.toString(answeres.get(0)));
        button1.setText(Integer.toString(answeres.get(1)));
        button2.setText(Integer.toString(answeres.get(2)));
        button3.setText(Integer.toString(answeres.get(3)));
    }
    public void chooseAnswere(View view)
    {
       //Log.i("tag ", (String) view.getTag());

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswere)))
        {
            //Log.i("Correct","correct");
            score++;
            resulttextview.setText("Correct!!");
        }
        else
        {
            resulttextview.setText("Wrong!!");
        }
        numberOfQues++;
        pointstextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQues));
        generateQuestion();
    }
    public void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.VISIBLE);
        playagain(findViewById(R.id.playAgain));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startbutton);

         sumTextView = findViewById(R.id.sumtextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resulttextview = findViewById(R.id.resulttextView);
        pointstextView = findViewById(R.id.pointTextView);
        timer = findViewById(R.id.TimertextView);
        playAgain = findViewById(R.id.playAgain);
        layout = findViewById(R.id.gameLayout);
        //generateQuestion();


    }
}