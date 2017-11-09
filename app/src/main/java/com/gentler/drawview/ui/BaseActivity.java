package com.gentler.drawview.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gentler.drawview.impl.IActivityLifecycle;

import butterknife.ButterKnife;

/**
 * Created by admin on 2017/11/9.
 */

public abstract class BaseActivity extends AppCompatActivity implements IActivityLifecycle{

    private BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this;
//        BarUtils.hideStatusBar(mActivity);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
