package com.app.mybaseframwork;

import android.app.Activity;
import android.view.View;

import com.app.mybaseframwork.base.base_model.MyBaseViewModel;
import com.app.mybaseframwork.databinding.ActivityMainBinding;

public class MainActivityViewModel extends MyBaseViewModel<ActivityMainBinding> {
    public MainActivityViewModel(Activity context, ActivityMainBinding binding) {
        super(context, binding);
    }

    @Override
    public void init() {
        setClickListener(binding.tvTitle);
    }





    @Override
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_title:
                getSystemData();
                break;
        }
    }


    public void getSystemData(){

    }
}
