package com.app.mybaseframwork;

import android.content.Intent;
import android.view.View;

import com.app.mybaseframwork.base.MyBaseDataBindingActivity;
import com.app.mybaseframwork.databinding.ActivitySplashBinding;

import yin.deng.normalutils.utils.DownTimer;

public class SplashActivity extends MyBaseDataBindingActivity<ActivitySplashBinding> {

    @Override
    public int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initFirst() {
        jump();
    }


    public void jump(){
        DownTimer timer=new DownTimer();
        timer.setIntervalTime(1000);
        timer.setTotalTime(3000);
        timer.setTimerLiener(new DownTimer.TimeListener() {
            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onInterval(long remainTime) {

            }
        });
        timer.start();
    }
}
