package com.app.mybaseframwork.base;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.app.mybaseframwork.R;

import yin.deng.normalutils.utils.ToastUtil;

public class MyToastUtil {
    private static ToastUtil toastUtil;
    private static Toast mToast;

    public static void show(String msg){
        if(toastUtil==null) {
            toastUtil = new ToastUtil(BaseApp.app, msg);
        }
        toastUtil.setText(msg);
        toastUtil.show();
    }

    public static void showCenter(String msg) {
        LayoutInflater inflater = (LayoutInflater) BaseApp.app.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //自定义布局
        View view = inflater.inflate(R.layout.toast_layout, null);
        //自定义toast文本
        TextView mTextView = (TextView)view.findViewById(R.id.toast_msg);
        mTextView.setText(msg);
        Log.i("ToastUtil", "Toast start...");
        if(mToast==null) {
            mToast = new Toast(BaseApp.app);
        }
        //设置toast居中显示
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(view);
        mToast.show();
    }

}
