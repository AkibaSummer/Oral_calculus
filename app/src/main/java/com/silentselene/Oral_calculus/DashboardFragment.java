package com.silentselene.Oral_calculus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dashbord, container, false);

        setButtonAction(view);

        return view;
    }

    private void setButtonAction(final View view) {
        Button button;
        for (int i = 0; i < Constant.problemtypes; i++) {
            button = view.findViewById(id[i]);
            final TextView textView = view.findViewById(R.id.dashboard_title);
            final int ii = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView.setText(Constant.type_name[ii]);
                }
            });
        }
    }
}