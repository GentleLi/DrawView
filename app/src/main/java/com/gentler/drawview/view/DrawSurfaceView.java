package com.gentler.drawview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.gentler.drawview.R;
import com.gentler.drawview.model.DIYGiftModel;
import com.gentler.drawview.utils.ImageUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by admin on 2017/11/8.
 */

public class DrawSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = DrawSurfaceView.class.getSimpleName();
    private Context mContext;
    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mLastBitmap;
    private Bitmap mScaledBitmap;
    private DIYGiftModel mDIYGiftModel;
    private int mBitmapResId;
    CopyOnWriteArrayList<DIYGiftModel> mDiyGiftModelList = new CopyOnWriteArrayList<>();
    //    private List<Point> mDiyGiftModelList=new ArrayList<>();
    private int mDownX;
    private int mDownY;
    private int mLastX;
    private int mLastY;
    private DrawThread mDrawThread;


    public DrawSurfaceView(Context context) {
        this(context, null);
    }

    public DrawSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        initBitmap();
        initPaint();
        initSurfaceView();
    }

    private void initSurfaceView() {
        getHolder().addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    public void setBitmapSource(int bitmapSource) {
        mDiyGiftModelList.clear();
        this.mBitmapResId = bitmapSource;
        if (null != mBitmap) {
            mLastBitmap = mBitmap;
            mLastBitmap = null;
            mBitmap = BitmapFactory.decodeResource(getResources(), bitmapSource);
            mScaledBitmap = ImageUtils.scale(mBitmap, 0.6f, 0.6f);
        }
    }

    private void initBitmap() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.heart_small);
        mScaledBitmap = ImageUtils.scale(mBitmap, 0.6f, 0.6f);
    }


    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mContext.getResources().getColor(R.color.colorAccent));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    public void setDataList(CopyOnWriteArrayList<DIYGiftModel> dataList) {
        this.mDiyGiftModelList = dataList;
    }

    public List<DIYGiftModel> getDataList() {
        ArrayList<DIYGiftModel> diyGiftModels = new ArrayList<>();
        diyGiftModels.addAll(mDiyGiftModelList);
//        DIYGiftModel diyGiftModel = null;
//        for (DIYGiftModel model : mDiyGiftModelList) {
//            diyGiftModel = new DIYGiftModel();
//            diyGiftModel.setX(model.getX());
//            diyGiftModel.setY(model.getY());
//            diyGiftModel.setGiftRes(model.getGiftRes());
//            diyGiftModels.add(diyGiftModel);
//        }
        return diyGiftModels;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG, "surfaceCreated");
        if (null==mDrawThread){
            startDraw();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e(TAG, "surfaceDestroyed");
        if (null!=mDrawThread){
            mDrawThread.isRun = false;
            mDrawThread=null;
        }
    }

    public void startDraw() {
        mDrawThread = new DrawThread();
        mDrawThread.isRun = true;
        new Thread(mDrawThread).start();
    }

    private void drawSomething(Canvas canvas) {
        if (mDiyGiftModelList.size() == 0) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);
            return;
        }
//        canvas.drawBitmap(mScaledBitmap,100,100,mPaint);
        Iterator<DIYGiftModel> iterator = mDiyGiftModelList.iterator();
        while (iterator.hasNext()) {
            DIYGiftModel point = iterator.next();
            if (null != point) {
                canvas.drawBitmap(mScaledBitmap, point.getX() - mScaledBitmap.getWidth() / 2, point.getY() - mScaledBitmap.getHeight() / 2, mPaint);
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDIYGiftModel = new DIYGiftModel();
                mDIYGiftModel.setGiftRes(mBitmapResId);
                mDownX = (int) event.getX();
                mDownY = (int) event.getY();
                mLastX = mDownX;
                mLastY = mDownY;
                mDIYGiftModel.setX(mDownX);
                mDIYGiftModel.setY(mDownY);
                mDiyGiftModelList.add(mDIYGiftModel);
                return true;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int moveY = (int) event.getY();
//                Log.e(TAG,"moveX:"+moveX);
//                Log.e(TAG,"moveY:"+moveY);
                int distance = (int) Math.pow(moveX - mLastX, 2) + (int) Math.pow(moveY - mLastY, 2);
                int reference = (int) Math.pow(100, 2);
//                Log.e(TAG,"distance:"+distance);
//                Log.e(TAG,"Math.pow(90,2):"+(int)Math.pow(90,2));
                if (distance >= reference - 2000 && distance <= reference + 2000) {
                    mDIYGiftModel = new DIYGiftModel();
                    mDIYGiftModel.setGiftRes(mBitmapResId);
                    mDIYGiftModel.setX(moveX);
                    mDIYGiftModel.setY(moveY);
                    mDiyGiftModelList.add(mDIYGiftModel);
                    mLastX = moveX;
                    mLastY = moveY;
                    postInvalidate();
                }
//                Log.e(TAG,"action move mLastX: "+mLastX);
//                Log.e(TAG,"action move mLastY:"+mLastY);
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(event);
    }


    public void reset() {
        mDiyGiftModelList.clear();
        postInvalidate();
    }


    public class DrawThread implements Runnable {
        public boolean isRun = false;

        @Override
        public void run() {

            while (isRun) {
                Canvas canvas = null;
                try {
                    canvas = getHolder().lockCanvas();
                    if (null != canvas) {
                        //TODO 绘制
                        drawSomething(canvas);
                        getHolder().unlockCanvasAndPost(canvas);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (null != canvas) {
                        getHolder().unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }


}
