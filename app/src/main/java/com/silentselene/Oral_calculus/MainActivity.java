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
    public static final int problemnums = 13;       //每次题目数量
    public static final int problemtypes = 13;       //总题目类型
    public static int score = 0;                    //当前分数
    public static int type = 0;                     //当前类型
    private TextView mTextMessage;

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

        //setButtonAction();
    }

    private void setButtonAction() {
        Button button;
        button = findViewById(R.id.button1_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 1;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button1_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 2;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button1_3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 3;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button1_4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 4;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button1_5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 5;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button1_6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 6;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button1_7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 7;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button1_8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 8;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button1_9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 9;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button1_10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 10;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button1_11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 11;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button1_12);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 12;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button1_13);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 13;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
    }
}
