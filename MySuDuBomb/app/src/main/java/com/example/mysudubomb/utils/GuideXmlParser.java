package com.example.mysudubomb.utils;

import android.util.Xml;

import com.example.mysudubomb.bean.GuideInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GuideXmlParser {
    public static List<GuideInfo>parse(InputStream in){
        List<GuideInfo>guideInfoList= new ArrayList<>();

        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(in,"utf-8");
            int type = parser.getEventType();

            GuideInfo guideInfo=null;
            while (type != XmlPullParser.END_DOCUMENT){
                switch (type){
                    case  XmlPullParser.START_TAG:
                        if (parser.getName().equals("item"))
                        {
                            guideInfo = new GuideInfo();
                        }else if(parser.getName().equals("food")){
                            System.out.println("--------------------food");
                            guideInfo.setFood(parser.nextText());
                        }
                        else if(parser.getName().equals("content")){
                            System.out.println("--------------------content");
                            guideInfo.setCount(parser.nextText());
                        }
                        else if(parser.getName().equals("image")){

                            guideInfo.setFoodImage(parser.nextText());
                            System.out.println("--------------------foodImage");
                        }
                        break;
                    case  XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")){
                            guideInfoList.add(guideInfo);
                        }


                        break;
                }
                type =parser.next();
                System.out.println("--------------------h");
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return  guideInfoList;

    }
}
