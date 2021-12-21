package com.dy.fastframework.util;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.dy.fastframework.R;
import com.dy.fastframework.application.SuperBaseApp;

import yin.deng.normalutils.utils.BaseToastUtil;

public class ToastUtil extends BaseToastUtil{
    private static BaseToastUtil baseToastUtil;
    private static Toast mToast;

    private ToastUtil(String msg) {
        super(SuperBaseApp.superApp, msg);
    }

    public static void show(String msg){
        if(baseToastUtil ==null) {
            baseToastUtil = new BaseToastUtil(SuperBaseApp.superApp, msg);
        }
        baseToastUtil.setText(msg);
        baseToastUtil.show();
    }

    public static void showCenter(String msg) {
        LayoutInflater inflater = (LayoutInflater) SuperBaseApp.superApp.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //自定义布局
        View view = inflater.inflate(R.layout.toast_layout, null);
        //自定义toast文本
        TextView mTextView = (TextView)view.findViewById(R.id.toast_msg);
        mTextView.setText(msg);
        Log.i("BaseToastUtil", "Toast start...");
        if(mToast==null) {
            mToast = new Toast(SuperBaseApp.superApp);
        }
        //设置toast居中显示
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(view);
        mToast.show();
    }

}
