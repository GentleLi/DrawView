package com.gentler.drawview.ui;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.gentler.drawview.R;
import com.gentler.drawview.view.DIYView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_reset)
    AppCompatButton mBtnReset;
    @BindView(R.id.diy_view)
    DIYView mDiyView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_reset)
    public void onClickReset(View view){
        mDiyView.reset();
    }


}
