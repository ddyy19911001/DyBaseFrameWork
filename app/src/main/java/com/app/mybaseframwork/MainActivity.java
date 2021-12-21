package com.app.mybaseframwork;

import com.app.mybaseframwork.base.MyBaseDataBindingActivity;
import com.app.mybaseframwork.databinding.ActivityMainBinding;

/**
 * ViewModel继承基类ViewModel,实现模型和Activity，fragment分离
 */
public class MainActivity extends MyBaseDataBindingActivity<MainActivityViewModel,ActivityMainBinding> {
    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initFirst() {
        viewModel.getSystemData();
    }


    @Override
    protected MainActivityViewModel createViewModel() {
        return new MainActivityViewModel(this,binding);
    }
}