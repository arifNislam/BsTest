package com.bs23.bstest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.List;

import me.zhanghai.android.patternlock.ConfirmPatternActivity;
import me.zhanghai.android.patternlock.PatternUtils;
import me.zhanghai.android.patternlock.PatternView;

public class SampleConfirmPatternActivity extends ConfirmPatternActivity {
    final String PREFS_NAME = "MyPrefs";
    SharedPreferences sharedPreferences;


    @Override
    protected boolean isStealthModeEnabled() {
        // TODO: Return the value from SharedPreferences.
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected boolean isPatternCorrect(List<PatternView.Cell> pattern) {
        // TODO: Get saved pattern sha1.
        sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String patternSha1 = sharedPreferences.getString("lock", null);
        if(TextUtils.equals(PatternUtils.patternToSha1String(pattern), patternSha1)) {
            Intent intent = new Intent(SampleConfirmPatternActivity.this, AppListActivity.class);
            startActivity(intent);
        }
        return TextUtils.equals(PatternUtils.patternToSha1String(pattern), patternSha1);
    }

}
