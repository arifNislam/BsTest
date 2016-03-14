package com.bs23.bstest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import me.zhanghai.android.patternlock.PatternUtils;
import me.zhanghai.android.patternlock.PatternView;

public class SetPatternActivity extends me.zhanghai.android.patternlock.SetPatternActivity {

    final String PREFS_NAME = "MyPrefs";
    SharedPreferences sharedPreferences;
    @Override
    protected void onSetPattern(List<PatternView.Cell> pattern) {
        String patternSha1 = PatternUtils.patternToSha1String(pattern);
        sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        sharedPreferences.edit().putString("lock", patternSha1).commit();
        Intent intent = new Intent(SetPatternActivity.this, AppListActivity.class);
        startActivity(intent);
        // TODO: Save patternSha1 in SharedPreferences.
    }
}
