package com.silentselene.Oral_calculus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int problemnums = 13;
    public static final int problemtypes = 1;
    public static int score = 0;
    public static int type = 0;
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
                    mTextMessage.setText(R.string.title_home);
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                        transaction.add(R.id.main_fragment_container, homeFragment);
                    } else {
                        transaction.show(homeFragment);
                    }
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    if (dashboardFragment == null) {
                        dashboardFragment = new DashboardFragment();
                        transaction.add(R.id.main_fragment_container, dashboardFragment);
                    } else {
                        transaction.show(dashboardFragment);
                    }
                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
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

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        frameLayout = findViewById(R.id.main_fragment_container);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.add(R.id.main_fragment_container, homeFragment);
        transaction.commit();

    }
}
