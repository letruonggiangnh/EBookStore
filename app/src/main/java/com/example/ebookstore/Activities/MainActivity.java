package com.example.ebookstore.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ebookstore.Fragments.HomeFragment;
import com.example.ebookstore.Fragments.AccountFragment;
import com.example.ebookstore.Fragments.LoginFragment;
import com.example.ebookstore.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
        anhxa();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                int selectedId = item.getItemId();
                final int accountId = R.id.action_account;
                final int homeId = R.id.action_home;
                if (selectedId == accountId) {
                    if(isUserLoggedIn()){
                        AccountFragment fragment = new AccountFragment();
                        fragmentTransaction.replace(R.id.container, fragment);
                    }else
                    {
                        LoginFragment fragment = new LoginFragment();
                        fragmentTransaction.replace(R.id.container, fragment);
                    }
                }
                else if(selectedId == homeId) {
                        HomeFragment fragment = new HomeFragment();
                        fragmentTransaction.replace(R.id.container, fragment);
                }
                fragmentTransaction.commit();
                return true;
            }
        });
    }
    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    private void anhxa() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
    }

}