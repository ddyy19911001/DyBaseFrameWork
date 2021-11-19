package com.app.mybaseframwork;

import android.app.Activity;
import android.view.View;

import com.app.mybaseframwork.base.base_model.MyBaseViewModel;
import com.app.mybaseframwork.data.UpdateVersionInfo;
import com.app.mybaseframwork.databinding.ActivityMainBinding;

public class MainActivityViewModel extends MyBaseViewModel<ActivityMainBinding> {
    public MainActivityViewModel(Activity context, ActivityMainBinding binding) {
        super(context, binding);
    }

    @Override
    public void init() {

    }


    public void setViewData(UpdateVersionInfo data){

    }



    @Override
    public void onViewClick(View v) {

    }
}
