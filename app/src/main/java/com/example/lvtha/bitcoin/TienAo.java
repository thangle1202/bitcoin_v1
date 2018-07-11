package com.example.lvtha.bitcoin;

/**
 * Created by lvtha on 10/05/2018.
 */

public class TienAo {
    private String mViettat;
    private String mTenrieng;
    private String mPrice;
    private String mMarket;
    private String mVolume;
    private String mChangeone;
    private String mChangeday;
    private String mChangeweek;

    public TienAo(){}

    public TienAo(String mViettat, String mTenrieng, String mPrice, String mMarket,
                  String mVolume, String mChangeone, String mChangeday, String mChangeweek) {
        this.mViettat = mViettat;
        this.mTenrieng = mTenrieng;
        this.mPrice = mPrice;
        this.mMarket = mMarket;
        this.mVolume = mVolume;
        this.mChangeone = mChangeone;
        this.mChangeday = mChangeday;
        this.mChangeweek = mChangeweek;
    }

    public String getmViettat() {
        return mViettat;
    }

    public void setmViettat(String mViettat) {
        this.mViettat = mViettat;
    }

    public String getmTenrieng() {
        return mTenrieng;
    }

    public void setmTenrieng(String mTenrieng) {
        this.mTenrieng = mTenrieng;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmMarket() {
        return mMarket;
    }

    public void setmMarket(String mMarket) {
        this.mMarket = mMarket;
    }

    public String getmVolume() {
        return mVolume;
    }

    public void setmVolume(String mVolume) {
        this.mVolume = mVolume;
    }

    public String getmChangeone() {
        return mChangeone;
    }

    public void setmChangeone(String mChangeone) {
        this.mChangeone = mChangeone;
    }

    public String getmChangeday() {
        return mChangeday;
    }

    public void setmChangeday(String mChangeday) {
        this.mChangeday = mChangeday;
    }

    public String getmChangeweek() {
        return mChangeweek;
    }

    public void setmChangeweek(String mChangeweek) {
        this.mChangeweek = mChangeweek;
    }
}
