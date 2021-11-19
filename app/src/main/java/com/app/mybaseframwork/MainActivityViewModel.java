package com.app.mybaseframwork;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.app.mybaseframwork.base.MyBaseViewModel;
import com.app.mybaseframwork.databinding.ActivityMainBinding;

public class MainActivityViewModel extends MyBaseViewModel<ActivityMainBinding> {
    public MainActivityViewModel(Activity context, ActivityMainBinding binding) {
        super(context, binding);
    }

    @Override
    public void init() {

    }

    @Override
    public void onViewClick(View v) {

    }
}
