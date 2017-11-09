package com.gentler.drawview.impl;

/**
 * Created by admin on 2017/11/9.
 */

public interface IActivityLifecycle {
    int getLayoutId();

    void initView();

    void initData();

    void initListener();
}
