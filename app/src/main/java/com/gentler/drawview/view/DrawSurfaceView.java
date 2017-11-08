package com.gentler.drawview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by admin on 2017/11/8.
 */

public class DrawSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private static final String TAG=DrawSurfaceView.class.getSimpleName();
    private Context mContext;
    private boolean isRun=false;

    public DrawSurfaceView(Context context) {
        this(context,null);
    }

    public DrawSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        init();
    }

    private void init() {
        getHolder().addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG,"surfaceCreated");

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG,"surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e(TAG,"surfaceDestroyed");
        isRun=false;


    }


    public void startDraw(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRun){
                    Canvas canvas=getHolder().lockCanvas();
                    if (null!=canvas){
                        //TODO 绘制




                    }
                }
            }
        }).start();
    }





}
