package com.example.mysudubomb.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NoActionBar {


    public static  void noActionBar(Context context){

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = ((Activity)context).getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            ((Activity)context).getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = ((AppCompatActivity)context).getSupportActionBar();
        actionBar.hide();
    }
}
