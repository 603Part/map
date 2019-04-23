package com.example.lbstest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {
    List<Activity> activities = new ArrayList<>();
    private static final String TAG = "BaseActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
    }

    protected void clearActivity() {
        Log.d(TAG, "clearActivity: " + activities.size());
        for (Activity activity : activities) {
            activity.finish();
        }
    }
}
