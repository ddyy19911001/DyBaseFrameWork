package com.app.mybaseframwork;

import android.content.Intent;
import android.view.View;

import com.app.mybaseframwork.base.MyBaseDataBindingActivity;
import com.app.mybaseframwork.base.base_model.MyBaseViewModel;
import com.app.mybaseframwork.databinding.ActivitySplashBinding;

import yin.deng.normalutils.utils.DownTimer;

public class SplashActivity extends MyBaseDataBindingActivity<SplashViewModel,ActivitySplashBinding> {

    @Override
    public int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initFirst() {
        viewModel.jump();
    }




    @Override
    protected SplashViewModel createViewModel() {
        return new SplashViewModel(this,binding);
    }
}
