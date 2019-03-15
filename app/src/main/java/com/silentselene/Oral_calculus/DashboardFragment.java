package com.silentselene.Oral_calculus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Objects;


public class DashboardFragment extends Fragment {

    LinearLayout time, type, result;
    ArrayList<Board> boards;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dashbord, container, false);
        time = view.findViewById(R.id.dashboard_time);
        type = view.findViewById(R.id.dashboard_type);
        result = view.findViewById(R.id.dashboard_result);
        updateBoard();
        return view;
    }

    TextView getTextView(String string) {
        TextView ret = new TextView(this.getContext());
        ret.setText(string);
        ret.setTextSize(22);
        ret.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        return ret;
    }

    @Override
    public void onResume() {
        updateBoard();
        super.onResume();
    }

    void updateBoard() {
        boards = getBoard();
        time.removeAllViews();
        type.removeAllViews();
        result.removeAllViews();
        for (int i = boards.size() - 1; i >= 0; i--) {
            time.addView(getTextView(getTime(i)));
            type.addView(getTextView(getString(Constant.type_name[boards.get(i).type])));
            TextView resultT = getTextView("查看结果");
            resultT.setTextColor(getResources().getColor(R.color.colorBlue, Objects.requireNonNull(this.getActivity()).getTheme()));
            final int ii = i;
            resultT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    seeResult(ii);
                }
            });
            result.addView(resultT);
        }
    }

    String getTime(int i) {
        int year = boards.get(i).year, month = boards.get(i).month, day = boards.get(i).day;
        return String.format(getString(R.string.date_format), month, day);
    }

    void seeResult(int i) {
        Constant.year = boards.get(i).year;
        Constant.month = boards.get(i).month;
        Constant.day = boards.get(i).day;
        Constant.viewType = boards.get(i).type;
        Constant.problemNum = boards.get(i).problemNum;
        Constant.re_each_time = boards.get(i).each_time;
        Constant.totalTime = boards.get(i).totalTime;
        Constant.correct = boards.get(i).correct;
        Constant.incorrect = boards.get(i).incorrect;
        Constant.timeout = boards.get(i).timeout;
        Constant.score = boards.get(i).score;
        Constant.isReview = true;
        Intent intent = new Intent();
        intent.setClass(Objects.requireNonNull(this.getContext()), ScoreActivity.class);
        startActivity(intent);
    }

    ArrayList<Board> getBoard() {
        onAttach(this.getContext());
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = Objects.requireNonNull(getActivity()).openFileInput("board");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert fileInputStream != null;

        ArrayList<Board> ret = new ArrayList<>();
        try {
            while (true) {
                Board board = new Board();
                board.year = fileInputStream.read();
                if (board.year == -1) break;
                board.month = fileInputStream.read();
                board.day = fileInputStream.read();
                board.type = fileInputStream.read();
                board.problemNum = fileInputStream.read();
                board.each_time = fileInputStream.read();
                board.totalTime = fileInputStream.read();
                board.totalTime = board.totalTime * 100 + fileInputStream.read();
                board.totalTime = board.totalTime * 100 + fileInputStream.read();
                board.totalTime = board.totalTime * 100 + fileInputStream.read();
                board.correct = fileInputStream.read();
                board.incorrect = fileInputStream.read();
                board.timeout = fileInputStream.read();
                board.score = fileInputStream.read();
                board.score = board.score * 100 + fileInputStream.read();
                ret.add(board);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}