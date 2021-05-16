package com.example.mysudubomb.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mysudubomb.bean.GuideInfo;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.sourceop.AssetssStreamOP;
import com.example.mysudubomb.utils.GuideXmlParser;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuideManager {
    private Context context;
    public  GuideManager(Context context){
        this.context=context;
    }

    public boolean DakaTimeSP(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);

        SharedPreferences sp = context.getSharedPreferences(AppProperties.SP_NAME,Context.MODE_PRIVATE);/*保存的文件名字(生产  名字.xml文件)  指定该SharedPreferences数据只能被本应用程序读、写*/
        SharedPreferences.Editor edit = sp.edit(); /*获得读写操作,封装 底层是map*/
        edit.putString(AppProperties.SP_DAKA_KEY,time).commit();/*强调原子性 同生共死 一起存*/
        return true;

    }
    public List<GuideInfo> getListFromAssets(String path){
        List<GuideInfo>list= new ArrayList<>();
        InputStream inputStream=null;
        try {
            AssetssStreamOP op = new AssetssStreamOP(context);
            inputStream = op.getInputStream(path);
            list = GuideXmlParser.parse(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
