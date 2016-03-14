package com.bs23.bstest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LockScreenActivity extends AppCompatActivity {
    final String PREFS_NAME = "MyPrefs";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        if(sharedPreferences.getBoolean("first_run", true)) {
            Log.i("Test", "first run");
            sharedPreferences.edit().putBoolean("first_run", false).commit();
            Intent intent = new Intent();
        } else {
            Log.i("Test", "seccond run");
        }

    }
}
