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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_path)
    AppCompatButton mBtnPath;

    @BindView(R.id.btn_heart)
    AppCompatButton mBtnHeart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


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
