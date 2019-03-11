package com.silentselene.Oral_calculus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    long nowNum = 0, score = 0, begin_time;
    Ret nowProblem;
    int nowTime;

    CountDownTimer timer;                               //get view
    EditText answerE;
    TextView problemT, nowNumT, scoreT, nowTimeT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Constant.score = -1;

        answerE = findViewById(R.id.answer);
        problemT = findViewById(R.id.problem);
        scoreT = findViewById(R.id.score);
        nowTimeT = findViewById(R.id.times);
        nowNumT = findViewById(R.id.nownum);

        answerE.requestFocus();                             //keyboard push
        nowProblem = getProblem.get(Constant.type);

        answerE.addTextChangedListener(new TextWatcher() { //text changed action
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals(nowProblem.ans)) {
                    long now_score = Constant.type_time[Constant.type] * 1000 - (System.currentTimeMillis() - begin_time);
                    now_score = now_score * Constant.type_time[Constant.type] / 200 + 500; //base score:500 point; max time score:500 point;
                    score += now_score;
                    start_next_problem();
                }
            }
        });

        timer = new CountDownTimer(Constant.type_time[Constant.type] * 1000, 1000) {         //set time action
            @Override
            public void onTick(long millisUntilFinished) {
                nowTime--;
                updateView();
            }

            @Override
            public void onFinish() {                        // time out
                start_next_problem();
            }
        };

        start_next_problem();
    }

    private void start_next_problem() {          // start next problem
        timer.cancel();
        ++nowNum;
        judgeIsEnd();
        nowTime = Constant.type_time[Constant.type] + 1;
        begin_time = System.currentTimeMillis();
        nowProblem = getProblem.get(Constant.type);
        answerE.setText(null);
        updateView();
        timer.start();
    }

    @SuppressLint("SetTextI18n")
    private void updateView() {     //update view text
        nowNumT.setText("第" + nowNum + "/" + Constant.problemnums + "题");
        problemT.setText(nowProblem.problem);
        scoreT.setText(String.valueOf(score));
        nowTimeT.setText(String.valueOf(nowTime));
    }

    private void judgeIsEnd() {
        if (nowNum == Constant.problemnums) {
            Constant.score = (int) score;
            Intent intent = new Intent();
            intent.setClass(TestActivity.this, ScoreActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}
