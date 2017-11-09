package com.gentler.drawview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.gentler.drawview.R;
import com.gentler.drawview.utils.ImageUtils;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by admin on 2017/11/8.
 */

public class DrawSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private static final String TAG=DrawSurfaceView.class.getSimpleName();
    private Context mContext;
    private boolean isRun=false;
    private Paint mPaint;
    private Bitmap mBitmap;
    private Bitmap mLastBitmap;
    private Bitmap mScaledBitmap;
    CopyOnWriteArrayList<Point> mPointList=new CopyOnWriteArrayList<>();
//    private List<Point> mPointList=new ArrayList<>();
    private int mDownX;
    private int mDownY;
    private int mLastX;
    private int mLastY;


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

    public void setBitmapSource(int bitmapSource){
        mPointList.clear();
        if (null!=mBitmap){
            mLastBitmap=mBitmap;
            mLastBitmap=null;
            mBitmap = BitmapFactory.decodeResource(getResources(), bitmapSource);
            mScaledBitmap= ImageUtils.scale(mBitmap,0.6f,0.6f);
        }
    }

    private void initBitmap() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.heart_small);
        mScaledBitmap= ImageUtils.scale(mBitmap,0.6f,0.6f);
    }


    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mContext.getResources().getColor(R.color.colorAccent));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
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
        isRun=true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRun){
                    Canvas canvas=null;
                    try {
                        canvas=getHolder().lockCanvas();
                        if (null!=canvas){
                            //TODO 绘制
                            drawSomething(canvas);
                            getHolder().unlockCanvasAndPost(canvas);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        if (null!=canvas){
                            getHolder().unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }).start();
    }

    private void drawSomething(Canvas canvas) {
        if (mPointList.size()==0){
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);
            return;
        }
//        canvas.drawBitmap(mScaledBitmap,100,100,mPaint);
        Iterator<Point> iterator=mPointList.iterator();
        while (iterator.hasNext()){
            Point point=iterator.next();
            if (null!=point){
                canvas.drawBitmap(mScaledBitmap, point.x-mScaledBitmap.getWidth()/2,point.y-mScaledBitmap.getHeight()/2,mPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) event.getX();
                mDownY = (int) event.getY();
                mLastX = mDownX;
                mLastY = mDownY;
                mPointList.add(new Point(mDownX, mDownY));
                return true;
            case MotionEvent.ACTION_MOVE:
                int moveX= (int) event.getX();
                int moveY= (int) event.getY();
//                Log.e(TAG,"moveX:"+moveX);
//                Log.e(TAG,"moveY:"+moveY);
                int distance=(int)Math.pow(moveX-mLastX,2)+(int) Math.pow(moveY-mLastY,2);
                int reference= (int) Math.pow(75,2);
//                Log.e(TAG,"distance:"+distance);
//                Log.e(TAG,"Math.pow(90,2):"+(int)Math.pow(90,2));
                if (distance>=reference-2000&&distance<=reference+2000){
                    mPointList.add(new Point(moveX,moveY));
                    mLastX=moveX;
                    mLastY=moveY;
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


    public void reset(){
        mPointList.clear();
        postInvalidate();
    }






}
