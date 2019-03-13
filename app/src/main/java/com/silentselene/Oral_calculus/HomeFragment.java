package com.silentselene.Oral_calculus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Objects;

public class HomeFragment extends Fragment {

    int[] id = {R.id.button1_1,
            R.id.button1_2,
            R.id.button1_3,
            R.id.button1_4,
            R.id.button1_5,
            R.id.button1_6,
            R.id.button1_7,
            R.id.button1_8,
            R.id.button1_9,
            R.id.button1_10,
            R.id.button1_11,
            R.id.button1_12,
            R.id.button1_13};

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setButtonAction(view);
        return view;
    }

    private void setButtonAction(View view) {
        Button button = view.findViewById(R.id.home_test_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTest();
            }
        });
        for (int i = 0; i < Constant.problemTypes; i++) {
            button = view.findViewById(id[i]);
            final int ii = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constant.type = ii;
                    setText(ii);

//                    Intent intent = new Intent();
//                    intent.setClass(inflater.getContext(), TestActivity.class);
//                    startActivity(intent);
                }
            });
        }

        RadioButton p10 = view.findViewById(R.id.p10);
        p10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.problemNum = 10;
            }
        });
        RadioButton p20 = view.findViewById(R.id.p20);
        p20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.problemNum = 20;
            }
        });
        RadioButton p50 = view.findViewById(R.id.p50);
        p50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.problemNum = 50;
            }
        });

    }

    void setText(int id) {
        TextView textView = Objects.requireNonNull(this.getActivity()).findViewById(R.id.test_title);
        textView.setText(Constant.type_name[id]);
        textView = Objects.requireNonNull(this.getActivity()).findViewById(R.id.home_time);
        textView.setText(String.valueOf(Constant.type_time[id])+"秒");
    }

    void startTest() {
        Constant.isreview=false;
        Intent intent = new Intent();
        intent.setClass(Objects.requireNonNull(getContext()), TestActivity.class);
        startActivity(intent);
    }
}

