package com.gentler.drawview.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.gentler.drawview.R;
import com.gentler.drawview.config.MyParams;
import com.gentler.drawview.model.DIYGiftModel;
import com.gentler.drawview.view.ReappearSurfaceView;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2017/11/10.
 */

public class ReappearActivity extends BaseActivity {

    private static final String TAG = ReappearActivity.class.getSimpleName();

    @BindView(R.id.reappear_surface_view)
    ReappearSurfaceView mReappearSurfaceView;
    private ArrayList<DIYGiftModel> mGiftModelList;
    private CopyOnWriteArrayList<DIYGiftModel> mDataList = new CopyOnWriteArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_reappear;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Log.e(TAG, "initData");
        Intent intent = getIntent();
        if (intent.hasExtra(MyParams.INTENT_ARRAY_LIST_GIFT)) {
            mGiftModelList = intent.getParcelableArrayListExtra(MyParams.INTENT_ARRAY_LIST_GIFT);
            for (DIYGiftModel model : mGiftModelList) {
                mDataList.add(model);
            }
        }
        mReappearSurfaceView.setDataList(mDataList);
        DIYGiftModel model = mDataList.get(0);
        mReappearSurfaceView.setBitmapSource(model.getGiftRes());
        mReappearSurfaceView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mReappearSurfaceView.startDraw();

            }
        }, 1000);
    }

    @Override
    public void initListener() {

    }


    @OnClick(R.id.btn_reset)
    public void onClickReset(View view) {
        mReappearSurfaceView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mReappearSurfaceView.reset();
                finish();
            }
        }, 1000);
    }


}
