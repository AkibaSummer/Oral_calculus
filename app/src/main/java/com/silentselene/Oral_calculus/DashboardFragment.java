package com.silentselene.Oral_calculus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


public class DashboardFragment extends Fragment {

    public final static int[] id = {R.id.button2_1,
            R.id.button2_2,
            R.id.button2_3,
            R.id.button2_4,
            R.id.button2_5,
            R.id.button2_6,
            R.id.button2_7,
            R.id.button2_8,
            R.id.button2_9,
            R.id.button2_10,
            R.id.button2_11,
            R.id.button2_12,
            R.id.button2_13};

    LinearLayout name, score;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dashbord, container, false);
        setButtonAction(view);
        name = view.findViewById(R.id.board_name);
        score = view.findViewById(R.id.board_score);
        return view;
    }

    private void setButtonAction(final View view) {
        Button button;
        for (int i = 0; i < Constant.problemTypes; i++) {
            button = view.findViewById(id[i]);
            final TextView textView = view.findViewById(R.id.dashboard_title);
            final int ii = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView.setText(Constant.type_name[ii]);
                    updateBoard(ii);
                }
            });
        }
    }

    TextView getTextView(String string) {
        TextView ret = new TextView(this.getContext());
        ret.setText(string);
        ret.setTextSize(20);
        return ret;
    }

    void updateBoard(int ii) {
        ArrayList<Board> boards = getBoard(ii);
        name.removeAllViews();
        score.removeAllViews();
        for (int i = 0; i < boards.size(); i++) {
            name.addView(getTextView((i + 1) + "  " + boards.get(i).name));
            score.addView(getTextView(String.valueOf(boards.get(i).score)));
        }
    }


    ArrayList<Board> getBoard(int i) {
        onAttach(this.getContext());
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = Objects.requireNonNull(getActivity()).openFileInput("board_" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert fileInputStream != null;

        ArrayList<Board> ret = new ArrayList<>();
        try {
            while (true) {
                Board board = new Board();
                int length = fileInputStream.read();
                byte[] name = new byte[200];
                if (fileInputStream.read(name, 0, length) == -1) break;
                board.name = new String(name, 0, length);
                board.score = fileInputStream.read() * 100 + fileInputStream.read();
                ret.add(board);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(ret);
        return ret;
    }

}