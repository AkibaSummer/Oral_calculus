package com.silentselene.Oral_calculus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    static MainActivity mainActivity = null;

    private TextView mTextMessage;

    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private DashboardFragment dashboardFragment;
    private SettingFragment settingFragment;

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (dashboardFragment != null) {
            transaction.hide(dashboardFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            hideAllFragment(transaction);
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                        transaction.add(R.id.main_fragment_container, homeFragment);
                    } else {
                        transaction.show(homeFragment);
                    }
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    if (dashboardFragment == null) {
                        dashboardFragment = new DashboardFragment();
                        transaction.add(R.id.main_fragment_container, dashboardFragment);
                    } else {
                        transaction.show(dashboardFragment);
                    }
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    if (settingFragment == null) {
                        settingFragment = new SettingFragment();
                        transaction.add(R.id.main_fragment_container, settingFragment);
                    } else {
                        transaction.show(settingFragment);
                    }
                    transaction.commit();
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initboard();
        mainActivity = this;

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        frameLayout = findViewById(R.id.main_fragment_container);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.add(R.id.main_fragment_container, homeFragment);
        transaction.commit();
    }

    //初始化排行榜文件
    void initboard() {
        for (int i = 0; i < Constant.problemtypes; i++) {
            try {
                openFileInput("board_" + i).close();
            } catch (FileNotFoundException e) {
                try {
                    openFileOutput("board_" + i, Context.MODE_PRIVATE).close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    ArrayList<Board> getBoard(int i) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput("board_" + i);
        } catch (FileNotFoundException e) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(ret);
        return ret;
    }
}
