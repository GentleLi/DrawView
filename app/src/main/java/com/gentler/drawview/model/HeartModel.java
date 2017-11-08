package com.gentler.drawview.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2017/11/8.
 */

public class HeartModel implements Parcelable {

    private int x;
    private int y;

    protected HeartModel(Parcel in) {

    }

    public static final Creator<HeartModel> CREATOR = new Creator<HeartModel>() {
        @Override
        public HeartModel createFromParcel(Parcel in) {
            return new HeartModel(in);
        }

        @Override
        public HeartModel[] newArray(int size) {
            return new HeartModel[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
