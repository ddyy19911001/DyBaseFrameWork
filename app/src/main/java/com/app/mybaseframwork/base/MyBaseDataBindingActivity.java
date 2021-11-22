package com.app.mybaseframwork.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.app.mybaseframwork.base.base_model.MyBaseViewModel;

import yin.deng.superbase.activity.SuperBaseActivity;

/**
 * 复制此Activity到自己的基类中
 * 默认顶到状态栏的
 * @param <T>
 */
public abstract class MyBaseDataBindingActivity<V extends MyBaseViewModel<T>,T extends ViewDataBinding> extends SuperBaseActivity {
    public T binding;
    public V viewModel;



    @Override
    public void setLayoutVoid(int setLayout) {
        binding = DataBindingUtil.setContentView(this,setLayout);
        viewModel=createViewModel();
    }

    protected abstract V createViewModel();
}
