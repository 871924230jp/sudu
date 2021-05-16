package com.example.mysudubomb.bean;

import cn.bmob.v3.BmobObject;

public class Comment extends BmobObject {
    private String content;
    private Circle circle;
    private MyUser user;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }
}
