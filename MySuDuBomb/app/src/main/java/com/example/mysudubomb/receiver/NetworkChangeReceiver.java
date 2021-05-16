package com.example.mysudubomb.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);//获取connectivityManager实例
        //通过networkInfo实例来调用connectivityManager中的getActivieNetworkInfo来获取网络状态
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isAvailable()){
            Toast.makeText(context,"网络已连接",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"网络断开",Toast.LENGTH_SHORT).show();
        }
    }
}
