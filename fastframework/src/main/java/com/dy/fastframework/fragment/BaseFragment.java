package com.dy.fastframework.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dy.fastframework.R;
import com.dy.fastframework.util.ActivityLoadUtil;
import com.dy.fastframework.util.OnViewOnceClickListener;
import com.dy.fastframework.view.CommonMsgDialog;

import org.greenrobot.eventbus.EventBus;

import me.bakumon.statuslayoutmanager.library.OnStatusChildClickListener;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;
import yin.deng.normalutils.utils.NoDoubleClickListener;
import yin.deng.superbase.fragment.SuperBaseFragment;
import yin.deng.superbase.fragment.ViewPagerSuperBaseFragment;

public abstract class BaseFragment extends SuperBaseFragment implements OnViewOnceClickListener, OnStatusChildClickListener {
    private CommonMsgDialog commonMsgDialog;
    public StatusLayoutManager loadingManager;
    private boolean isFirstGetData=true;
    public Dialog mLoadingDialog;

    public void showMsgDialog(String msg){
        commonMsgDialog=getCommonMsgDialog();
        commonMsgDialog.showMsg(msg);
    }

    private CommonMsgDialog getCommonMsgDialog() {
        if(commonMsgDialog==null) {
            commonMsgDialog = new CommonMsgDialog(getActivity());
        }
        return commonMsgDialog;
    }

    public TextView getEmptyTv(){
        if(loadingManager!=null&&loadingManager.getEmptyLayout()!=null) {
            return loadingManager.getEmptyLayout().findViewById(R.id.re_try_bt);
        }else{
            return null;
        }
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
     * 获取颜色资源
     * @param res
     * @return
     */
    public String getResString(int res){
        return getResources().getString(res);
    }


    /**
     * 单次点击事件监听类
     */
    NoDoubleClickListener clickListener=new NoDoubleClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            BaseFragment.this.onViewClicked(v);
        }
    };

    /**
     * 将需要设置点击事件的按钮设置进来
     * @param views
     */
    public void setClickListener(View...views){
        for(View view:views) {
            view.setOnClickListener(clickListener);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        if (mRootView != null) {
            return mRootView;
        }
        bindViewWithId(mRootView);
        mViewInflateFinished = true;
        if(setLoadingRootView()!=null) {
            loadingManager = ActivityLoadUtil.getInstance().useDefaultLoadLayout(setLoadingRootView(), this);
        }
        init();
        return mRootView;
    }


    public View setLoadingRootView() {
        return null;
    }




    @Override
    public void onEmptyChildClick(View view) {

    }

    @Override
    public void onErrorChildClick(View view) {

    }

    @Override
    public void onCustomerChildClick(View view) {

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(mViewInflateFinished&&isVisibleToUser&&isFirstGetData){
            isFirstGetData=false;
            onFirstVisibleToGetData();
        }
    }




    //被隐藏的fragment才能调用这个方法在首次打开时进行获取数据，已执行过bindviewwithid
    public void onFirstVisibleToGetData() {

    }


    /**
     * 显示转圈的Dialog
     *
     * @return
     */
    public Dialog showLoadingDialog(String msg, boolean canDismiss) {
        closeDialog();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v
                .findViewById(R.id.dialog_loading_view);// 加载布局
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(getActivity(), R.style.MyDialogStyle);// 创建自定义样式dialog
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
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        this.mLoadingDialog = loadingDialog;
        return loadingDialog;
    }


    /**
     * 关闭dialog
     */
    public void closeDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            this.mLoadingDialog.dismiss();
            this.mLoadingDialog = null;
        }
    }


    @Override
    public void onDestroy() {
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }


}
