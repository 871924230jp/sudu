package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.MyUser;
import com.example.mysudubomb.utils.NoActionBar;
import com.jaredrummler.android.widget.AnimatedSvgView;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity {
    private Context context;
    AnimatedSvgView svgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏        //getSupportActionBar().hide();//隐藏标题栏
        NoActionBar.noActionBar(this);
        this.context=MainActivity.this;
        /*MySqliteHelper helper = new MySqliteHelper(this);
        helper.getReadableDatabase();*/

        Thread thread = new Thread(){
            @Override
            public void run() {
                svgView.start();

            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                kk();
            }
        },2500);


    }

    private void initView() {
        TextView tv_main = (TextView) findViewById(R.id.tv_main);
        tv_main.getPaint().setFakeBoldText(true);

        svgView = (AnimatedSvgView) findViewById(R.id.animated_svg_view);
    }
    private void kk(){

        Bmob.initialize(this,"248237734f8a7580c0cbbeaa74151238");


        MyUser user = BmobUser.getCurrentUser(MyUser.class);

        Intent intent = new Intent();
        if(null == user){
            // 未登录，跳转到登录Or注册界面
            intent.setClass(MainActivity.this,LoginActivity.class);
        }else{
            // 已登录，正在进入应用
            intent.setClass(MainActivity.this,MajorActivity.class);
        }
        startActivity(intent);
        finish();


    }
}
