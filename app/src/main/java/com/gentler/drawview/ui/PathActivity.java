package com.gentler.drawview.ui;

import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.gentler.drawview.R;
import com.gentler.drawview.view.DIYView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2017/11/9.
 */

public class PathActivity extends BaseActivity {

    @BindView(R.id.btn_reset)
    AppCompatButton mBtnReset;
    @BindView(R.id.diy_view)
    DIYView mDiyView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_path;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.btn_reset)
    public void onClickReset(View view){
        mDiyView.reset();
    }
}
