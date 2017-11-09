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
    }

    private void initPath() {
        mPath = new Path();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mContext.getResources().getColor(R.color.path_color));
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
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                postInvalidate();
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
        invalidate();
    }

}
