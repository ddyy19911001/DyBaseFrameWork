package com.runshui.mall.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.app.mybaseframwork.base.base_model.MyBaseViewModel;
import com.dy.fastframework.activity.BaseTopTabViewActivity;
import com.dy.fastframework.util.NetErroInfo;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * 复制此Activity到自己的基类中
 * @param <T>
 */
public abstract class MyBaseTopTabDataBindingActivity<V extends MyBaseViewModel<T>,T extends ViewDataBinding> extends BaseTopTabViewActivity{
    public T binding;
    public V viewModel;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealWithNetErr(NetErroInfo netErroInfo) {
        closeDialog();
        if(viewModel!=null){
            viewModel.onNetErrShowNormal();
        }
    }

    @Override
    public void setLayoutVoid(int setLayout) {
        binding = DataBindingUtil.setContentView(this,setLayout);
        viewModel=createViewModel();
    }

    protected abstract V createViewModel();

    @Override
    public void closeDialog() {
        super.closeDialog();
        if(viewModel!=null){
            viewModel.closeDialog();
        }
    }
}
