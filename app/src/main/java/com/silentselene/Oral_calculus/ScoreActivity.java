package com.silentselene.Oral_calculus;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;

public class ScoreActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        updateView();
        this.setTitle(R.string.app_title);

        if (!Constant.isReview)
            try {
                FileOutputStream fileOutputStream = openFileOutput("board", MODE_APPEND);
                fileOutputStream.write(Constant.year);
                fileOutputStream.write(Constant.month);
                fileOutputStream.write(Constant.day);
                fileOutputStream.write(Constant.type);
                fileOutputStream.write(Constant.problemNum);
                fileOutputStream.write(Constant.each_time);
                fileOutputStream.write(Constant.totalTime / 1000000);
                fileOutputStream.write(Constant.totalTime / 10000 % 100);
                fileOutputStream.write(Constant.totalTime / 100 % 100);
                fileOutputStream.write(Constant.totalTime % 100);
                fileOutputStream.write(Constant.correct);
                fileOutputStream.write(Constant.incorrect);
                fileOutputStream.write(Constant.timeout);
                fileOutputStream.write(Constant.score / 100);
                fileOutputStream.write(Constant.score % 100);
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        TextView rule = findViewById(R.id.score_score);
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("评分标准")
                        .setMessage(R.string.rule)
                        .setPositiveButton("确定", null)
                        .show();
            }
        });
        Button button = findViewById(R.id.confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });
    }

    void updateView() {
        TextView type = findViewById(R.id.score_type);
        TextView num = findViewById(R.id.score_num);
        TextView each_time = findViewById(R.id.score_each_time);
        TextView time = findViewById(R.id.score_time);
        TextView avg_time = findViewById(R.id.score_avgtime);
        TextView correct = findViewById(R.id.score_correct);
        TextView incorrect = findViewById(R.id.score_incorrect);
        TextView timeout = findViewById(R.id.score_timeout);
        TextView rate = findViewById(R.id.score_rate);
        TextView score = findViewById(R.id.score_score);
        type.setText(Constant.type_name[Constant.isReview ? Constant.viewType : Constant.type]);
        num.setText((getResources().getString(R.string.score_num) + Constant.problemNum));
        each_time.setText((getResources().getString(R.string.score_each_time) + (Constant.isReview ? Constant.re_each_time : Constant.each_time) + "秒"));
        time.setText((getResources().getString(R.string.score_time) + Constant.totalTime + "秒"));
        avg_time.setText((getResources().getString(R.string.score_avgTime) + String.format(getString(R.string.format_avg_time), (float) Constant.totalTime / Constant.problemNum) + "秒"));
        correct.setText((getResources().getString(R.string.score_correct) + Constant.correct));
        incorrect.setText((getResources().getString(R.string.score_incorrect) + Constant.incorrect));
        timeout.setText((getResources().getString(R.string.score_timeout) + Constant.timeout));
        rate.setText((getResources().getString(R.string.score_rate) + (Constant.correct * 100 / Constant.problemNum) + "%"));
        score.setText((getResources().getString(R.string.score_score) + (Constant.score / 100)));
    }

    void exit() {
        finish();
    }
}