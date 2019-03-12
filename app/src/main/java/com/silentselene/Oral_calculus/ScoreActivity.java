package com.silentselene.Oral_calculus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;

public class ScoreActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        TextView score = findViewById(R.id.score_score);
        score.setText(String.valueOf(Constant.score));
        TextView type = findViewById(R.id.score_type);
        type.setText(Constant.type_name[Constant.type]);

        Button button = findViewById(R.id.confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fileOutputStream = openFileOutput("board_" + Constant.type, MODE_APPEND);
                    EditText nameT = findViewById(R.id.name);
                    String name = nameT.getText().toString();
                    if (name.length() == 0) name = "匿名";
                    fileOutputStream.write(name.getBytes().length);
                    fileOutputStream.write(name.getBytes());
                    fileOutputStream.write(Constant.score / 100);
                    fileOutputStream.write(Constant.score % 100);
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exit();
            }
        });
    }

    void exit() {
        finish();
    }
}