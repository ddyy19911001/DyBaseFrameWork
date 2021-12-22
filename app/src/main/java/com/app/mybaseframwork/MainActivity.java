package com.app.mybaseframwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.mybaseframwork.base.MyBaseDataBindingActivity;
import com.app.mybaseframwork.base.base_model.CommonViewModel;
import com.app.mybaseframwork.databinding.ActivityMainBinding;
import com.app.mybaseframwork.test.CanScrollTopVpActivity;
import com.dy.fastframework.util.NoDoubleClickListener;
import com.dy.fastframework.util.ScreenUtils;
import com.dy.fastframework.view.PopWindowUtils;


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
        viewModel.binding.tvContent.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(new Intent(MainActivity.this, CanScrollTopVpActivity.class));
            }
        });
    }


    String nowStr;
    int count;
    private void showPop2(){
        nowStr="我是本次的数据"+count;
        Bundle bundle=new Bundle();
        bundle.putString("data",nowStr);
        TestDialogFragment testDialogFragment=new TestDialogFragment(bundle);
        testDialogFragment.show(getSupportFragmentManager(),"show");
        count++;
    }

    private void showPop1() {
        PopWindowUtils utils=new PopWindowUtils(this) {
            @Override
            public void createViewHolder(View contentView) {
               TextView tvTitle= contentView.findViewById(R.id.tv_title);
               tvTitle.setText("现在改变了数据");
            }
        };
        utils.createPopupLayout(R.layout.dialog_test, ScreenUtils.dipTopx(this,300), ViewGroup.LayoutParams.WRAP_CONTENT);
        utils.showCenter(binding.tvContent);
    }


    @Override
    protected CommonViewModel<ActivityMainBinding> createViewModel() {
        return new CommonViewModel<ActivityMainBinding>(this,binding);
    }
}