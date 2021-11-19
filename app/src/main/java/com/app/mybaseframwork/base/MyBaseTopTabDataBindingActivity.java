package com.app.mybaseframwork.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.app.mybaseframwork.base.base_model.MyBaseViewModel;
import com.dy.fastframework.activity.BaseBottomTabActivity;
import com.dy.fastframework.activity.BaseTopTabViewActivity;

/**
 * 复制此Activity到自己的基类中
 * @param <T>
 */
public abstract class MyBaseTopTabDataBindingActivity<V extends MyBaseViewModel<T>,T extends ViewDataBinding> extends BaseTopTabViewActivity {
    public T binding;
    public V viewModel;



    @Override
    public void setLayoutVoid(int setLayout) {
        binding = DataBindingUtil.setContentView(this,setLayout);
        viewModel=createViewModel();
    }

    protected abstract V createViewModel();
}
