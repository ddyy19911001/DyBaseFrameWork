package com.app.mybaseframwork.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.dy.fastframework.activity.BaseBottomTabActivity;
import com.dy.fastframework.activity.BaseTopTabViewActivity;

/**
 * 复制此Activity到自己的基类中
 * @param <T>
 */
public abstract class MyBaseTopTabDataBindingActivity<T extends ViewDataBinding> extends BaseTopTabViewActivity {
    public T binding;

    @Override
    public void setLayoutVoid(int setLayout) {
        binding = DataBindingUtil.setContentView(this,setLayout);
    }
}
