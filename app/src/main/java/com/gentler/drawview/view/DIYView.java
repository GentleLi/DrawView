package com.gentler.drawview.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.gentler.drawview.R;
import com.gentler.drawview.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 2017/11/8.
 */

public class DIYView extends View {

    private static final String TAG = DIYView.class.getSimpleName();
    private Context mContext;
    private Paint mPaint;
    private Path mPath;
    private Bitmap mBitmap;
    private Bitmap mScaledBitmap;
    private List<Point> mPointList=new ArrayList<>();
    private int mDownX;
    private int mDownY;
    private int mLastX;
    private int mLastY;
    private RectF mRectF =new RectF();


    public DIYView(Context context) {
        this(context, null);
    }

    public DIYView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DIYView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        initPaint();
        initPath();
        initBitmap();

    }

    private void initBitmap() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.heart);
        mScaledBitmap=ImageUtils.scale(mBitmap,2f,2f);
    }

    private void initPath() {
        mPath = new Path();


    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mContext.getResources().getColor(R.color.colorAccent));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);

        canvas.drawBitmap(mScaledBitmap,100,100,mPaint);
        for (Point point:mPointList){
            canvas.drawBitmap(mBitmap, point.x-mBitmap.getWidth()/2,point.y-mBitmap.getHeight()/2,mPaint);
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
//                mPath.moveTo(event.getX(),event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                int moveX= (int) event.getX();
                int moveY= (int) event.getY();
//                Log.e(TAG,"moveX:"+moveX);
//                Log.e(TAG,"moveY:"+moveY);

                int distance=(int)Math.pow(moveX-mLastX,2)+(int) Math.pow(moveY-mLastY,2);
//                Log.e(TAG,"distance:"+distance);
//                Log.e(TAG,"Math.pow(90,2):"+(int)Math.pow(90,2));

                if (distance>=(int)Math.pow(90,2)-1000&&distance<=Math.pow(90,2)+1000){
                    mPointList.add(new Point(moveX,moveY));
                    mLastX=moveX;
                    mLastY=moveY;
                    postInvalidate();
                }
//                Log.e(TAG,"action move mLastX: "+mLastX);
//                Log.e(TAG,"action move mLastY:"+mLastY);

//                mPath.lineTo(event.getX(),event.getY());
//                postInvalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(event);
    }


    public void reset(){
        mPath.reset();
        mPointList.clear();
        invalidate();
    }

}
