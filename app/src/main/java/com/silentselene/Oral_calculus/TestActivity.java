package com.silentselene.Oral_calculus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    //    TextView scoreT = findViewById(R.id.score);
//    TextView nowNumT = findViewById(R.id.nownum);
//    TextView problemT = findViewById(R.id.problem);
//    TextView timesT = findViewById(R.id.times);
    int nowNum = 1, score = 0;
//    Ret nowProblem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        MainActivity.score = -1;

//        EditText answerE = (EditText) findViewById(R.id.answer);
//        answerE.requestFocus();
//        nowProblem = getProblem.get(MainActivity.problemtypes);
        updateView();
    }

    @SuppressLint("SetTextI18n")
    private void updateView() {
        TextView scoreT = findViewById(R.id.score);
        TextView nowNumT = findViewById(R.id.nownum);
        TextView problemT = findViewById(R.id.problem);
        nowNumT.setText("第" + nowNum + "/" + MainActivity.problemnums + "题");
//        problemT.setText(nowProblem.problem);
        scoreT.setText(String.valueOf(score));
    }
}
