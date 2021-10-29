package com.app.mybaseframwork;

import android.view.View;

import com.app.mybaseframwork.base.MyBaseDataBindingActivity;
import com.app.mybaseframwork.databinding.ActivityMainBinding;

public class MainActivity extends MyBaseDataBindingActivity<ActivityMainBinding> {
    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initFirst() {

    }

    @Override
    public void onViewClicked(View v) {

    }




}