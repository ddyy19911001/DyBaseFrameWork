package com.dy.fastframework.activity;

import android.view.View;
import android.widget.TextView;

import com.dy.fastframework.R;
import com.dy.fastframework.util.ActivityLoadUtil;
import com.dy.fastframework.util.OnViewOnceClickListener;

import me.bakumon.statuslayoutmanager.library.OnStatusChildClickListener;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;
import yin.deng.normalutils.utils.NoDoubleClickListener;
import yin.deng.superbase.activity.SuperBaseActivity;

public abstract class BaseActivity extends SuperBaseActivity implements OnViewOnceClickListener, OnStatusChildClickListener {
    public StatusLayoutManager loadingManager;

    public TextView getEmptyTv(){
        if(loadingManager!=null&&loadingManager.getEmptyLayout()!=null) {
            return loadingManager.getEmptyLayout().findViewById(R.id.re_try_bt);
        }else{
            return null;
        }
    }

    @Override
    public void onNotInitFirst() {
        super.onNotInitFirst();
        if(setLoadingRootView()!=null) {
            loadingManager = ActivityLoadUtil.getInstance().useDefaultLoadLayout(setLoadingRootView(), this);
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
            BaseActivity.this.onViewClicked(v);
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
}
