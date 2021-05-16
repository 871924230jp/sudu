package com.example.mysudubomb.bean;

import java.io.Serializable;

public class FoodInfo implements Serializable {
    private String shicai;
    private String kcal;

    public String getShicai() {
        return shicai;
    }

    public void setShicai(String shicai) {
        this.shicai = shicai;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTanshui() {
        return tanshui;
    }

    public void setTanshui(String tanshui) {
        this.tanshui = tanshui;
    }

    public String getDanbai() {
        return danbai;
    }

    public void setDanbai(String danbai) {
        this.danbai = danbai;
    }

    public String getZhifang() {
        return zhifang;
    }

    public void setZhifang(String zhifang) {
        this.zhifang = zhifang;
    }

    public String getFoodImagePath() {
        return foodImagePath;
    }

    public void setFoodImagePath(String foodImagePath) {
        this.foodImagePath = foodImagePath;
    }

    private String description;
    private String tanshui;
    private String danbai;
    private String zhifang;
    private String foodImagePath;

}
