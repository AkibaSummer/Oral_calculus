package com.silentselene.Oral_calculus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DashboardFragment extends Fragment {

    int[] id = {R.id.button2_1,
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_dashbord,container,false);
        return  view;
    }
}

