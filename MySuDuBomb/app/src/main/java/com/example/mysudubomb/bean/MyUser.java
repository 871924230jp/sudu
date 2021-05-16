package com.example.mysudubomb.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class MyUser extends BmobUser {

    private String name;
    private String description;
    private BmobFile headImage;
    private String gender;
    private BmobRelation circle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BmobFile getHeadImage() {
        return headImage;
    }

    public void setHeadImage(BmobFile headImage) {
        this.headImage = headImage;
    }


    public BmobRelation getCircle() {
        return circle;
    }

    public void setCircle(BmobRelation circle) {
        this.circle = circle;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
