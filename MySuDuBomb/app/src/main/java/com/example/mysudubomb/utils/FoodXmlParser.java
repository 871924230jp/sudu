package com.example.mysudubomb.utils;

import android.util.Xml;

import com.example.mysudubomb.bean.ArticleInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FoodXmlParser {
    public static List<ArticleInfo> parse(InputStream in){
        List<ArticleInfo>articleList= new ArrayList<>();

        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(in,"utf-8");
            int type = parser.getEventType();

            ArticleInfo articleInfo=null;
            while (type != XmlPullParser.END_DOCUMENT){
                switch (type){
                    case  XmlPullParser.START_TAG:
                        if (parser.getName().equals("item"))
                        {
                            articleInfo = new ArticleInfo();
                        }else if(parser.getName().equals("title")){
                            articleInfo.setTitle(parser.nextText());
                        }
                        else if(parser.getName().equals("link")){
                            articleInfo.setLink(parser.nextText());
                        }
                        else if(parser.getName().equals("author")){
                            articleInfo.setAuthor(parser.nextText());
                        }else if(parser.getName().equals("image")){
                            articleInfo.setImage(parser.nextText());
                        }else if(parser.getName().equals("pubDate")){
                            articleInfo.setPubDate(parser.nextText());
                        }else if(parser.getName().equals("contentone")){
                            articleInfo.setContentone(parser.nextText());
                        }else if(parser.getName().equals("contenttwo")){
                            articleInfo.setContenttwo(parser.nextText());
                        } else if(parser.getName().equals("contentthree")){
                            articleInfo.setContentthree(parser.nextText());
                        }else if(parser.getName().equals("descriptionone")){
                            articleInfo.setDescriptionone(parser.nextText());
                        }else if(parser.getName().equals("descriptiontwo")){
                            articleInfo.setDescriptiontwo(parser.nextText());
                        }else if(parser.getName().equals("descriptionthree")){
                            articleInfo.setDescriptionthree(parser.nextText());
                        }
                        break;
                    case  XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")){
                            articleList.add(articleInfo);
                        }
                        break;
                }
                type =parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  articleList;

    }
}
