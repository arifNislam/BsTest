package com.bs23.bstest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AppListActivity extends AppCompatActivity {
    ListView listView;
    List<ResolveInfo> appList;
    ArrayList<String> appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        listView = (ListView) findViewById(R.id.listView);
        appList = new ArrayList<>();
        appName = new ArrayList<>();

        PackageManager pm = getPackageManager();
        final Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        appList = pm.queryIntentActivities(intent, 0);

        for(ResolveInfo rInfo : appList) {
            appName.add(rInfo.activityInfo.applicationInfo.loadLabel(pm).toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, appName);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityInfo activityInfo = appList.get(position).activityInfo;
                ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);

                Intent newIntent = new Intent(Intent.ACTION_MAIN);
                newIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                newIntent.setComponent(componentName);
                startActivity(newIntent);
            }
        });

    }
}
