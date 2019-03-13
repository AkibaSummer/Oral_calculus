package com.silentselene.Oral_calculus;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

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
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                        transaction.add(R.id.main_fragment_container, homeFragment);
                    } else {
                        transaction.show(homeFragment);
                    }
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    if (dashboardFragment == null) {
                        dashboardFragment = new DashboardFragment();
                        transaction.add(R.id.main_fragment_container, dashboardFragment);
                    } else {
                        transaction.show(dashboardFragment);
                    }
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
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

        initBoard();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            transaction.add(R.id.main_fragment_container, homeFragment);
        } else {
            transaction.show(homeFragment);
        }
        transaction.commit();
    }

    //初始化排行榜文件
    void initBoard() {
        for (int i = 0; i < Constant.problemTypes; i++) {
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

}
