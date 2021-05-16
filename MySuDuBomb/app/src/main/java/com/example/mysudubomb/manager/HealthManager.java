package com.example.mysudubomb.manager;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.mysudubomb.bean.HealthInfo;
import com.example.mysudubomb.properties.AppProperties;

public class HealthManager {
    private  Context context;
    public HealthManager(Context context){
        this.context=context;


    }

    public Intent setintent ( Class context2,CharSequence xingbie, String shengao, String tizhong, String nianling){

        boolean panduan = testpanduan( shengao, tizhong, nianling);

        if (panduan) {
            Intent intent = new Intent(context, context2);
            HealthInfo healthInfo = new HealthInfo();
            healthInfo.setXingbie(xingbie.toString());
            healthInfo.setNianling(nianling);
            healthInfo.setShengao(shengao);
            healthInfo.setTizhong(tizhong);
            intent.putExtra(AppProperties.HEALTH_INTENT_COMPUTE, healthInfo);
            return intent;
        }
        return  null;
    }

    private   boolean testpanduan(String shengao, String tizhong, String nianling){



        if(shengao.isEmpty()  || tizhong.isEmpty()|| nianling.isEmpty() )
        {

            Toast.makeText(context, "身高体重年龄不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if ( shengao.startsWith(".") || tizhong.startsWith(".") )
        {
            Toast.makeText(context, "请输入正确的身高体重", Toast.LENGTH_SHORT).show();

            return false;
        }
        else if  ( shengao.equals("0") || tizhong.equals("0") || nianling.equals("0"))
        {
            Toast.makeText(context, "请输入正确的身高体重", Toast.LENGTH_SHORT).show();

            return false;
        }
        else if ( shengao.equals("0.") || tizhong.equals("0.") )
        {
            Toast.makeText(context, "请输入正确的身高体重", Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;

    }


}
