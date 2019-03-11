package com.silentselene.Oral_calculus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        TextView score=findViewById(R.id.score_score);
        score.setText(String.valueOf(Constant.score));
        TextView type=findViewById(R.id.score_type);
        type.setText(Constant.type_name[Constant.type-1]);
    }
}