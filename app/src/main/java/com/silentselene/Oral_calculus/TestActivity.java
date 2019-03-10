package com.silentselene.Oral_calculus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    long nowNum = 1, score = 0;
    Ret nowProblem;
    long begin_time = System.currentTimeMillis();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        final TextView problemT = findViewById(R.id.problem);
        final EditText answerE = findViewById(R.id.answer);

        Constant.score = -1;

        answerE.requestFocus();
        nowProblem = getProblem.get(Constant.type);
//        updateView();
        problemT.setText(nowProblem.problem);

        answerE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals(nowProblem.ans)) {
                    score += Constant.maxtime - (System.currentTimeMillis() - begin_time);
                    begin_time = System.currentTimeMillis();
                    ++nowNum;
                    nowProblem = getProblem.get(Constant.type);
                    answerE.setText(null);
                    updateView();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateView() {
        if (nowNum == Constant.problemnums) {
            Constant.score = (int) score;
            Intent intent = new Intent();
            intent.setClass(TestActivity.this, ScoreActivity.class);
            startActivity(intent);
            this.finish();
        }
        TextView scoreT = findViewById(R.id.score);
        TextView nowNumT = findViewById(R.id.nownum);
        TextView problemT = findViewById(R.id.problem);
        nowNumT.setText("第" + nowNum + "/" + Constant.problemnums + "题");
        problemT.setText(nowProblem.problem);
        scoreT.setText(String.valueOf(score));
    }
}
