package com.example.mysudubomb.myview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mysudubomb.R;


public class MyDialog extends Dialog {

    private TextView tv_dakatime;
    private String messageStr;

    public MyDialog(@NonNull Context context) {
        super(context);


    }
    @Override
     protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daka);
               //空白处不能取消动画
        setCanceledOnTouchOutside(true);
        initView();
        initData();
   }

    private void initData() {
        if (messageStr != null) {
            tv_dakatime.setText(messageStr);
                   }

    }

    private void initView() {
        tv_dakatime = (TextView) findViewById(R.id.tv_dakatime);
    }
    public void setMessage(String message) {
      messageStr = message;
  }


}
