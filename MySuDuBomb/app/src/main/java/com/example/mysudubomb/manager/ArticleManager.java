package com.example.mysudubomb.manager;

import android.content.Context;

import com.example.mysudubomb.bean.ArticleInfo;
import com.example.mysudubomb.enumeration.SourceType;
import com.example.mysudubomb.sourceop.AssetssStreamOP;
import com.example.mysudubomb.utils.FoodXmlParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ArticleManager {
    private Context context;
    public ArticleManager(Context context){
        this.context=context;
    }

    public ArticleInfo getInfoFromSource(String path, SourceType sourceType){
        List<ArticleInfo>list= new ArrayList<>();
        InputStream inputStream=null;
        try {
            AssetssStreamOP op = new AssetssStreamOP(context);
             inputStream = op.getInputStream(path);
            list = FoodXmlParser.parse(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArticleInfo articleInfo=null;
        switch (sourceType)
        {
            case SOURCE_ONE:
                 articleInfo = list.get(0);
                break;
            case SOURCE_TWO:
                articleInfo = list.get(1);
                break;
            case SOURCE_THREE:
                articleInfo=list.get(2);
                break;
        }
     return articleInfo;
    }
}
