package com.app.mybaseframwork;

import android.view.View;

import com.app.mybaseframwork.base.MyBaseDataBindingActivity;
import com.app.mybaseframwork.base.MyBaseRequestUtil;
import com.app.mybaseframwork.base.MyBaseViewModel;
import com.app.mybaseframwork.data.UpdateVersionInfo;
import com.app.mybaseframwork.databinding.ActivityMainBinding;

import yin.deng.normalutils.utils.LogUtils;

public class MainActivity extends MyBaseDataBindingActivity<ActivityMainBinding> {
    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initFirst() {
       MainActivityViewModel viewModel=new MainActivityViewModel(this,binding);
        MyBaseRequestUtil.getInstance().<UpdateVersionInfo>sendGet("/system/init", new MyBaseRequestUtil.OnDataOkListener<UpdateVersionInfo>() {
            @Override
            public void onDataOk(UpdateVersionInfo data) {
                LogUtils.v("获取到的信息："+data.getData().getStartUp());
            }

            @Override
            public void onDataFail(int code, String msg) {
                showTs(msg);
            }
        });
    }


    @Override
    public View setLoadingRootView() {
        return super.setLoadingRootView();
    }
}