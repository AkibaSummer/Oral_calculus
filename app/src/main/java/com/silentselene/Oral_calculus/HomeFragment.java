package com.silentselene.Oral_calculus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        setButtonAction(view, inflater);
        return view;
    }

    private void setButtonAction(View view, final LayoutInflater inflater) {
        Button button;
        for (int i = 0; i < Constant.problemtypes; i++) {
            button = view.findViewById(id[i]);
            final int ii = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constant.type = ii;
                    Intent intent = new Intent();
                    intent.setClass(inflater.getContext(), TestActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}

