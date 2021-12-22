package com.runshui.mall.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.app.mybaseframwork.base.base_model.MyBaseViewModel;
import com.dy.fastframework.fragment.BaseTabViewFragment;
import com.dy.fastframework.util.NetErroInfo;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;



/**
 * 复制此Fragment到自己的基类中
 * @param <T>
 */
public abstract class MyBaseTabDataBindingFragment<V extends MyBaseViewModel<T>,T extends ViewDataBinding> extends BaseTabViewFragment {
    public T binding;
    public V viewModel;


    protected abstract V createViewModel();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        if (mRootView != null) {
            return mRootView;
        }
        binding = DataBindingUtil.inflate(inflater, setContentView(), container, false);
        viewModel=createViewModel();
        mRootView = binding.getRoot();
        bindViewWithId(mRootView);
        mViewInflateFinished = true;
        init();
        return mRootView;
    }

    @Override
    public void closeDialog() {
        super.closeDialog();
        if(viewModel!=null){
            viewModel.closeDialog();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealWithNetErr(NetErroInfo netErroInfo) {
        closeDialog();
        if(viewModel!=null){
            viewModel.onNetErrShowNormal();
        }
    }
}
