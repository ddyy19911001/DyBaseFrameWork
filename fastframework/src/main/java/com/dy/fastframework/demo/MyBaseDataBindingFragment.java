package com.dy.fastframework.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dy.fastframework.fragment.BaseFragment;


/**
 * 复制此Fragment到自己的基类中
 * @param <T>
 */
//public abstract class MyBaseDataBindingFragment<T extends ViewDataBinding> extends BaseFragment {
//    public T binding;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        Log.d(TAG, "onCreateView");
//        if (mRootView != null) {
//            return mRootView;
//        }
//        binding = DataBindingUtil.inflate(inflater, setContentView(), container, false);
//        mRootView = binding.getRoot();
//        bindViewWithId(mRootView);
//        mViewInflateFinished = true;
//        init();
//        return mRootView;
//    }
//}
