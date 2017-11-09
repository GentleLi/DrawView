package com.gentler.drawview.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.gentler.drawview.R;
import com.gentler.drawview.view.DrawSurfaceView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_path)
    AppCompatButton mBtnPath;

    @BindView(R.id.btn_heart)
    AppCompatButton mBtnHeart;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
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

    @OnClick(R.id.btn_heart)
    public void onClickHeart(View view){
        Intent intent=new Intent(MainActivity.this,HeartActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_path)
    public void onClickPath(View view){
        Intent intent=new Intent(MainActivity.this,PathActivity.class);
        startActivity(intent);
    }
}
