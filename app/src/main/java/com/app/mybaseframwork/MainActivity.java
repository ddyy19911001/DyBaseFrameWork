package com.app.mybaseframwork;

import com.app.mybaseframwork.base.MyBaseDataBindingActivity;
import com.app.mybaseframwork.base.base_model.CommonViewModel;
import com.app.mybaseframwork.databinding.ActivityMainBinding;

/**
 * ViewModel继承基类ViewModel,实现模型和Activity，fragment分离
 */
public class MainActivity extends MyBaseDataBindingActivity<CommonViewModel<ActivityMainBinding>, ActivityMainBinding> {
    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initFirst() {
        viewModel.binding.tvContent.setText("我是修改的文字");
    }


    @Override
    protected CommonViewModel<ActivityMainBinding> createViewModel() {
        return new CommonViewModel<ActivityMainBinding>(this,binding);
    }
}