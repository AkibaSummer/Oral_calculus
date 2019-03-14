package com.silentselene.Oral_calculus;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SettingFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        problem = view.findViewById(R.id.incorrect_problem);
        answer = view.findViewById(R.id.incorrect_answer);
        status = view.findViewById(R.id.incorrect_status);
        Button button = view.findViewById(R.id.refresh_record);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh("board");
                refresh("incorrect");
                updateBoard();
            }
        });
        updateBoard();
        return view;
    }

    LinearLayout problem, answer, status;
    ArrayList<Incorrect> incorrect;

    void refresh(String s) {
        try {
            Objects.requireNonNull(this.getActivity()).openFileOutput(s, Context.MODE_PRIVATE).close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        updateBoard();
        super.onResume();
    }

    TextView getTextView(String string) {
        TextView ret = new TextView(this.getContext());
        ret.setText(string);
        ret.setTextSize(20);
        ret.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        return ret;
    }

    void updateBoard() {
        incorrect = getIncorrect();
        answer.removeAllViews();
        problem.removeAllViews();
        status.removeAllViews();
        for (int i = incorrect.size() - 1; i >= 0; i--) {
            answer.addView(getTextView(incorrect.get(i).ret.ans));
            problem.addView(getTextView(incorrect.get(i).ret.problem));
            status.addView(getTextView(incorrect.get(i).type == 1 ? "答案错误" : "超时"));
        }
    }

    ArrayList<Incorrect> getIncorrect() {
        onAttach(this.getContext());
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = Objects.requireNonNull(getActivity()).openFileInput("incorrect");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert fileInputStream != null;

        ArrayList<Incorrect> retA = new ArrayList<>();
        try {
            while (true) {
                Incorrect ret = new Incorrect();

                ret.type = fileInputStream.read();
                if (ret.type == -1) break;

                int p = fileInputStream.read();
                byte[] bytes = new byte[100];
                p = fileInputStream.read(bytes, 0, p);
                ret.ret.problem = new String(bytes, 0, p);

                int a = fileInputStream.read();
                bytes = new byte[100];
                a = fileInputStream.read(bytes, 0, a);
                ret.ret.ans = new String(bytes, 0, a);

                retA.add(ret);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retA;
    }

}

