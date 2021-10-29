package com.app.mybaseframwork.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.dy.fastframework.activity.BaseTopTabViewActivity;
import com.dy.fastframework.activity.BaseWebViewActivity;

/**
 * 复制此Activity到自己的基类中
 * @param <T>
 */
public abstract class MyBaseWebDataBindingActivity<T extends ViewDataBinding> extends BaseWebViewActivity {
    public T binding;

    @Override
    public void setLayoutVoid(int setLayout) {
        binding = DataBindingUtil.setContentView(this,setLayout);
    }
}
