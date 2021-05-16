package com.example.mysudubomb.manager;

import android.content.Context;
import android.widget.Toast;

public class MotionManager {
    private Context context;
    public MotionManager(Context context){
        this.context=context;

    }
    public Double getResultFromMotion(String kg,String time,String project){
        boolean panduan = panduan(kg, time, project);
        if (panduan){
            double kg1 = Double.parseDouble(kg);
            double time1 = Double.parseDouble(time);
            double result = 0;
            switch (project){
                case "慢跑":
                     result = 0.12 * kg1 * time1;
                    break;
                case "游泳":
                    result = 0.10 * kg1 * time1;
                    break;
                case "快走":
                    result = 0.08 * kg1 * time1;
                    break;
                case "舞蹈":
                    result = 0.11 * kg1 * time1;
                    break;
            }
            return result;

        }
        return null;


    }
    public  boolean panduan(String kg,String time,String project){
        if(kg.isEmpty()  || time.isEmpty()|| project.isEmpty() )
        {

            Toast.makeText(context, "身高体重年龄不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else  if (kg.startsWith(".") || time.startsWith(".")){

            Toast.makeText(context, "请输入正确的体重和时长", Toast.LENGTH_SHORT).show();
            return false;
        }
        else  if (kg.equals("0") || time.equals("0")){

            Toast.makeText(context, "请输入正确的体重和时长", Toast.LENGTH_SHORT).show();
            return false;
        }
        else  if (kg.equals("0.") || time.equals("0.")){

            Toast.makeText(context, "请输入正确的体重和时长", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;



    }

}
