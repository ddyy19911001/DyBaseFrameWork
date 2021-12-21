package com.dy.fastframework.activity;

import android.app.Activity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import yin.deng.normalutils.utils.MyUtils;
import yin.deng.superbase.activity.AppActivityListManager;
import yin.deng.superbase.activity.NetErroInfo;
import yin.deng.superbase.activity.ServerErrInfo;
import yin.deng.superbase.activity.SuperBaseActivity;

public abstract class BaseActivity extends SuperBaseActivity {
    //服务器异常
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServerErr(ServerErrInfo serverErrInfo){
        Activity currentActivity = AppActivityListManager.getScreenManager().currentActivity();
        if(this!=currentActivity){
            return;
        }
        closeDialog();
        dealWithServerErr(serverErrInfo);
    }


    /**
     * 按需处理服务器异常
     * @param serverErrInfo
     */
    public void dealWithServerErr(ServerErrInfo serverErrInfo) {

    }


    //网络异常情况
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetErr(NetErroInfo netErroInfo){
        closeDialog();
        Activity currentActivity = AppActivityListManager.getScreenManager().currentActivity();
        if(this!=currentActivity){
            return;
        }
        if(!MyUtils.isEmpty(netErroInfo.errMsg)){
            showTs(netErroInfo.getErrMsg());
        }else{
            showTs("网络异常，请稍后重试");
        }
    }
}
