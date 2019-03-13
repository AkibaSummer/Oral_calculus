package com.silentselene.Oral_calculus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.util.Calendar;

public class TestActivity extends AppCompatActivity {
    long nowNum = 0, score = 0, begin_time, starttime;
    Ret nowProblem;
    int nowTime;

    CountDownTimer timer, popTimer;                               //get view
    EditText answerE;
    TextView problemT, nowNumT, scoreT, nowTimeT;
    PopUtil popUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setTitle(Constant.type_name[Constant.type]);
        Constant.score = -1;
        Constant.incorrect = Constant.correct = Constant.timeout = Constant.totalTime = 0;
        starttime = System.currentTimeMillis();

        answerE = findViewById(R.id.answer);
        problemT = findViewById(R.id.problem);
        scoreT = findViewById(R.id.score);
        nowTimeT = findViewById(R.id.times);
        nowNumT = findViewById(R.id.nownum);

        popUtil = new PopUtil(this);
        popTimer = new CountDownTimer(1500, 1500) {
            @Override
            public void onTick(long millisUntilFinished) {
                popUtil.showAtLocation(findViewById(R.id.linearLayout), Gravity.TOP, 0, 0);
            }

            @Override
            public void onFinish() {
                popUtil.dismiss();
            }
        };  //pop window

        answerE.requestFocus();                             //keyboard push
        nowProblem = getProblem.get(Constant.type);

        answerE.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (textView.getText().toString().equals(nowProblem.ans)) {
                    long now_score = Constant.type_time[Constant.type] * 1000 - (System.currentTimeMillis() - begin_time);
                    now_score = (now_score * 500 / (Constant.type_time[Constant.type] * 1000) + 500) * Constant.problemNum / 10; //base score:5000 point; max time score:5000 point;
                    score += now_score;
                    pop("正确,获得 " + now_score + " 分", true);
                    Constant.correct++;
                    start_next_problem();
                } else {
                    pop("答案错误", false);
                    Constant.incorrect++;
                    start_next_problem();
                }
                return false;
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
                pop("超时", false);
                Constant.totalTime += Constant.type_time[Constant.type] * 1000;
                Constant.timeout++;
            }
        };

        start_next_problem();
    }

    private void start_next_problem() {          // start next problem
        timer.cancel();
        if (judgeIsEnd()) return;
        ++nowNum;
        nowTime = Constant.type_time[Constant.type];
        begin_time = System.currentTimeMillis();
        nowProblem = getProblem.get(Constant.type);
        answerE.setText("");
        timer.start();
    }

    @SuppressLint("SetTextI18n")
    private void updateView() {     //update view text
        nowNumT.setText("第" + nowNum + "/" + Constant.problemNum + "题");
        problemT.setText(nowProblem.problem);
        scoreT.setText(String.valueOf(score));
        nowTimeT.setText(String.valueOf(nowTime));
    }

    private boolean judgeIsEnd() {
        if (nowNum == Constant.problemNum) {
            Constant.score = (int) score;
            Intent intent = new Intent();
            intent.setClass(TestActivity.this, ScoreActivity.class);
            startActivity(intent);
            Calendar calendar = Calendar.getInstance();
            Constant.year = calendar.get(Calendar.YEAR)%100;
            Constant.month = calendar.get(Calendar.MONTH) + 1;
            Constant.day = calendar.get(Calendar.DAY_OF_MONTH);
            Constant.totalTime = (int) (System.currentTimeMillis() - starttime)/1000;

            finish();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    void pop(String text, boolean type) {
        popTimer.cancel();
        popUtil.dismiss();
        popUtil.textView.setText(text);
        popUtil.mPopWindow.setBackgroundColor(type ? getResources().getColor(R.color.colorPrimaryDark, getTheme()) : getResources().getColor(R.color.colorAccent, getTheme()));
        popTimer.start();
    }

    @Override
    public void finish() {
        timer.cancel();
        popTimer.cancel();
        popUtil.dismiss();
        super.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
