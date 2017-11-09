package com.gentler.drawview.ui;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gentler.drawview.R;
import com.gentler.drawview.view.DrawSurfaceView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2017/11/9.
 */

public class HeartActivity extends BaseActivity {

    @BindView(R.id.btn_reset)
    AppCompatButton mBtnReset;

    @BindView(R.id.draw_surface_view)
    DrawSurfaceView mDrawSurfaceView;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_heart;
    }

    @Override
    public void initView() {
        GridLayoutManager mGridLayoutManager=new GridLayoutManager(mActivity,4);


    }

    @Override
    public void initData() {
        mDrawSurfaceView.startDraw();
    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.btn_reset)
    public void onClickReset(View view){
        mDrawSurfaceView.reset();
    }



}
