package com.gentler.drawview.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.gentler.drawview.config.Constants;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2017/11/8.
 */

public class DIYGiftModel implements Parcelable {

    private int x;
    private int y;
    private int giftRes;
    private int count= (int) (Math.random()*3);
    private TimerTask task;

    public DIYGiftModel(){
        super();
    }

    protected DIYGiftModel(Parcel in) {
        x=in.readInt();
        y=in.readInt();
        giftRes =in.readInt();
    }

    public static final Creator<DIYGiftModel> CREATOR = new Creator<DIYGiftModel>() {
        @Override
        public DIYGiftModel createFromParcel(Parcel in) {
            return new DIYGiftModel(in);
        }

        @Override
        public DIYGiftModel[] newArray(int size) {
            return new DIYGiftModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
        dest.writeInt(giftRes);
    }

    public void changePosition(){
        TimerTask task=getTimerTask();
        new Timer().schedule(task,1000,50);
    }

    public TimerTask getTimerTask() {
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                // 需要做的事:发送消息
                count++;
                float angle = (float) (0.1* Constants.PI* count);
                int diff= (int) (4*Math.sin(angle));
                setX(x+diff);
//                setY(y+diff);
            }
        };
        return task;
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getGiftRes() {
        return giftRes;
    }

    public void setGiftRes(int giftRes) {
        this.giftRes = giftRes;
    }

    @Override
    public String toString() {
        return "DIYGiftModel{" +
                "x=" + x +
                ", y=" + y +
                ", giftRes=" + giftRes +
                '}';
    }
}
