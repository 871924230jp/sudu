package com.example.mysudubomb.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class HealthInfo implements  Parcelable {
    public  HealthInfo(){}
    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }

    public String getTizhong() {
        return tizhong;
    }

    public void setTizhong(String tizhong) {
        this.tizhong = tizhong;
    }

    private  String xingbie;

    public String getNianling() {
        return nianling;
    }

    public void setNianling(String nianling) {
        this.nianling = nianling;
    }

    public String getShengao() {
        return shengao;
    }

    public void setShengao(String shengao) {
        this.shengao = shengao;
    }

    private  String nianling;
    private  String shengao;
    private  String tizhong;


    protected HealthInfo(Parcel in) {
        xingbie= in.readString();
        nianling = in.readString();
        shengao = in.readString();
        tizhong = in.readString();
    }
    public static final Parcelable.Creator<HealthInfo> CREATOR = new Parcelable.Creator<HealthInfo>() {
        @Override
        public HealthInfo createFromParcel(Parcel in) {
            return new HealthInfo(in);
        }

        @Override
        public HealthInfo[] newArray(int size) {
            return new HealthInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(xingbie);
        dest.writeString(nianling);
        dest.writeString(shengao);
        dest.writeString(tizhong);
    }
}
