package com.app.mybaseframwork;

import android.app.Activity;
import android.view.View;

import com.app.mybaseframwork.base.MyRequestUtil;
import com.app.mybaseframwork.base.base_model.MyBaseViewModel;
import com.app.mybaseframwork.data.UpdateVersionInfo;
import com.app.mybaseframwork.databinding.ActivityMainBinding;

import yin.deng.normalutils.utils.LogUtils;

public class MainActivityViewModel extends MyBaseViewModel<ActivityMainBinding> {
    public MainActivityViewModel(Activity context, ActivityMainBinding binding) {
        super(context, binding);
    }

    @Override
    public void init() {
        setClickListener(binding.tvTitle);
    }

    @Override
    public View setLoadingRootView() {
        return binding.tvTitle;
    }

    public void setViewData(UpdateVersionInfo data){
        showTs("获取到的信息为--->测试版本版本号："+data.getData().getVersion().getAndroid().getName());
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
        loadingManager.showLoadingLayout();
        MyRequestUtil.getInstance().sendGet("/system/init",UpdateVersionInfo.class, new MyRequestUtil.OnDataOkListener<UpdateVersionInfo>() {
            @Override
            public void onDataOk(UpdateVersionInfo data) {
                loadingManager.showSuccessLayout();
                setViewData(data);
            }
        });
    }
}
