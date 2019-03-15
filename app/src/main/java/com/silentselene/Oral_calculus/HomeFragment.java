package com.silentselene.Oral_calculus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;

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
            R.id.button1_13,
            R.id.button1_14,
            R.id.button1_15,
            R.id.button1_16,
            R.id.button1_17};

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setButtonAction(view);

        NumberPicker numberPicker = view.findViewById(R.id.set_time);
        numberPicker.setMaxValue(20);
        numberPicker.setMinValue(3);
        numberPicker.setValue(10);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Constant.each_time = i1;
            }
        });

//        setColor(Constant.type);
        return view;
    }

    @Override
    public void onResume() {
//        setText(Constant.type);
        setColor(Constant.type);
        super.onResume();
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
//                    setText(ii);
                    setColor(ii);
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

//    void setText(int id) {
//        TextView textView = Objects.requireNonNull(this.getActivity()).findViewById(R.id.test_title);
//        textView.setText(Constant.type_name[id]);
//        textView = Objects.requireNonNull(this.getActivity()).findViewById(R.id.home_time);
//        textView.setText(String.valueOf(Constant.type_time[id]) + "秒");
//    }

    void setColor(int i) {
        for (int ii = 0; ii < id.length; ii++) {
            Button button = Objects.requireNonNull(this.getActivity()).findViewById(id[ii]);
            button.setBackgroundColor(ii == i ? getResources().getColor(R.color.colorClicked, this.getActivity().getTheme()) : 0);
        }
    }

    void startTest() {
        Constant.isReview = false;
        Intent intent = new Intent();
        intent.setClass(Objects.requireNonNull(getContext()), TestActivity.class);
        startActivity(intent);
    }
}

