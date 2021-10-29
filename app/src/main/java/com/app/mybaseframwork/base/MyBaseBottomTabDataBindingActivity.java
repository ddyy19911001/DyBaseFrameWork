package com.app.mybaseframwork.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.dy.fastframework.activity.BaseActivity;
import com.dy.fastframework.activity.BaseBottomTabActivity;

/**
 * 复制此Activity到自己的基类中
 * @param <T>
 */
public abstract class MyBaseBottomTabDataBindingActivity<T extends ViewDataBinding> extends BaseBottomTabActivity {
    public T binding;

    @Override
    public void setLayoutVoid(int setLayout) {
        binding = DataBindingUtil.setContentView(this,setLayout);
    }
}
