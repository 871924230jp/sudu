package com.example.mysudubomb.bean;

import java.io.Serializable;

public class ArticleInfo implements Serializable {/*简单的标识一个类的对象可以被序列化。*/
    private String title;
    private String link;
    private String author;
    private String image;
    private String pubDate;
    private String contentone;
    private String contenttwo;
    private String contentthree;
    private String descriptionone;
    private String descriptiontwo;

    public String getContenttwo() {
        return contenttwo;
    }

    public void setContenttwo(String contenttwo) {
        this.contenttwo = contenttwo;
    }

    public String getContentthree() {
        return contentthree;
    }

    public void setContentthree(String contentthree) {
        this.contentthree = contentthree;
    }

    public String getDescriptiontwo() {
        return descriptiontwo;
    }

    public void setDescriptiontwo(String descriptiontwo) {
        this.descriptiontwo = descriptiontwo;
    }

    public String getDescriptionthree() {
        return descriptionthree;
    }

    public void setDescriptionthree(String descriptionthree) {
        this.descriptionthree = descriptionthree;
    }

    private String descriptionthree;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getContentone() {
        return contentone;
    }

    public void setContentone(String contentone) {
        this.contentone = contentone;
    }

    public String getDescriptionone() {
        return descriptionone;
    }

    public void setDescriptionone(String descriptionone) {
        this.descriptionone = descriptionone;
    }
}
