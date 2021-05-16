package com.example.mysudubomb.bean;



import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class Circle extends BmobObject implements Comparable<Circle>, Serializable {



    private String content ;
    private String image ;
    private  MyUser user;
    private String imageRatio;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BmobFile getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(BmobFile authorImage) {
        this.authorImage = authorImage;
    }

    private String author;
    private BmobFile authorImage;

    public String getImageRatio() {
        return imageRatio;
    }

    public void setImageRatio(String imageRatio) {
        this.imageRatio = imageRatio;
    }

    private BmobRelation likers;
    private Integer likes;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }


    public BmobRelation getLikers() {
        return likers;
    }

    public void setLikers(BmobRelation likers) {
        this.likers = likers;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Override
    public int compareTo(Circle o) {

        if (this.likes == o.likes) {
            return this.content.compareTo(o.content);
        } else {
            return o.likes-this.likes ;
        }

    }


}
