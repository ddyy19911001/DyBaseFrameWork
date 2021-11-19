package com.app.mybaseframwork.base.base_model;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.ViewDataBinding;

import com.app.mybaseframwork.R;
import com.dy.fastframework.util.ActivityLoadUtil;

import me.bakumon.statuslayoutmanager.library.OnStatusChildClickListener;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;
import yin.deng.normalutils.utils.NoDoubleClickListener;
import yin.deng.superbase.activity.ToastUtil;


/**
 * 视图模型基类
 * @param <T>
 */
public abstract class MyBaseViewModel<T extends ViewDataBinding> implements OnStatusChildClickListener {
    public Dialog loadingDialog;
    public  Context context;
    public  StatusLayoutManager loadingManager;
    public Activity activity;
    public Fragment fragment;
    private ToastUtil toast;
    public T binding;//用于快速找到对应控件

    public MyBaseViewModel(Activity activity,T binding) {
        this.activity = activity;
        this.binding=binding;
        this.context=getContext();
        onNotInitSetLayout();
        init();
    }

    public MyBaseViewModel(Fragment fragment, T binding) {
        this.fragment = fragment;
        this.binding=binding;
        this.context=getContext();
        onNotInitSetLayout();
        init();
    }


    public Context getContext(){
        if(activity!=null){
            return activity;
        }
        if(fragment!=null){
            return fragment.getActivity();
        }
        return null;
    }


    public Resources getResources(){
        return getContext().getResources();
    }

    /**
     * 如果重写生命周期方法，需要在对应Activity的生命周期中进行viewModle.onResume()的调用才可执行
     */
    public void onResume() {

    }

    public void onPause() {

    }

    public void onRestart() {

    }

    public void onDestroy() {

    }

    //主动在Activity中对应位置调用
    public void onActivityResult(Intent data){

    }

    public void startActivityForResult(Intent intent,int requestCode){
        if(activity!=null){
            activity.startActivityForResult(intent,requestCode);
        }

        if(fragment!=null){
            fragment.getActivity().startActivityForResult(intent,requestCode);
        }

    }

    public void startActivity(Intent intent){
        if(activity!=null){
            activity.startActivity(intent);
        }

        if(fragment!=null){
            fragment.getActivity().startActivity(intent);
        }

    }

    public void finish(){
        if(activity!=null){
            activity.finish();
        }

        if(fragment!=null){
            fragment.getActivity().finish();
        }
    }

    private void onNotInitSetLayout() {
        if(setLoadingRootView()!=null) {
            loadingManager = ActivityLoadUtil.getInstance().useDefaultLoadLayout(setLoadingRootView(), this);
        }
    }


    public Intent getIntent(){
        if(fragment!=null){
            return fragment.getActivity().getIntent();
        }else if(activity!=null){
            return activity.getIntent();
        }
        return null;
    }


    public Bundle getArguments(){
        if(fragment!=null){
            return fragment.getArguments();
        }
        return null;
    }


    /**
     * 获取颜色资源
     * @param res
     * @return
     */
    public int getResColor(int res){
        return getResources().getColor(res);
    }



    /**
     * 获取尺寸资源
     * @param res
     * @return
     */
    public float getResDimens(int res){
        return getResources().getDimension(res);
    }


    /**
     * 获取文字资源
     * @param res
     * @return
     */
    public String getResString(int res){
        return getResources().getString(res);
    }


    public View setLoadingRootView(){
        return null;
    }


    public TextView getEmptyTv(){
        if(loadingManager!=null&&loadingManager.getEmptyLayout()!=null) {
            return loadingManager.getEmptyLayout().findViewById(R.id.re_try_bt);
        }else{
            return null;
        }
    }



    /**
     * 创建模型的时候执行，可设置view的各种监听事件
     */
    public abstract void init();

    public void setClickListener(View...views){
        for(View view:views) {
            view.setOnClickListener(noDoubleClickListener);
        }
    }

    NoDoubleClickListener noDoubleClickListener=new NoDoubleClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            onViewClick(v);
        }
    };

    //可以重写
    public abstract void onViewClick(View v);


    /**
     * 设置加载中布局之后，为空时的点击事件
     * @param view
     */
    @Override
    public void onEmptyChildClick(View view) {

    }

    @Override
    public void onErrorChildClick(View view) {

    }

    @Override
    public void onCustomerChildClick(View view) {

    }


    /**
     * 显示转圈的Dialog
     *
     * @return
     */
    public Dialog showLoadingDialog(String msg, boolean canDismiss) {
        closeDialog();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(yin.deng.superbase.R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v
                .findViewById(yin.deng.superbase.R.id.dialog_loading_view);// 加载布局
        TextView tipTextView = (TextView) v.findViewById(yin.deng.superbase.R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, yin.deng.superbase.R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(canDismiss); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(yin.deng.superbase.R.style.PopWindowAnimStyle);
        loadingDialog.show();
        this.loadingDialog = loadingDialog;
        return loadingDialog;
    }


    /**
     * 关闭dialog
     */
    public void closeDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            this.loadingDialog.dismiss();
            this.loadingDialog = null;
        }
    }



    /**
     * 获取版本号
     *
     * @return
     */
    public int getVersionNum() {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }


    /**
     * 获取版本名称
     *
     * @return
     */
    public String getVersionName() {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String versionName = info.versionName;
            return versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }


    /**
     * 获取应用名称
     *
     * @return
     */
    public String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }



    public void showTs(String msg) {
        if (toast == null) {
            toast = new ToastUtil(context, msg);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }



}
