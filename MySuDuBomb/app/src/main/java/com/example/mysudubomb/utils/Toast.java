package com.example.mysudubomb.utils;

import android.content.Context;

public class Toast {
    public static void show(Context context , String content){
        android.widget.Toast.makeText(context, content, android.widget.Toast.LENGTH_SHORT).show();
    }
}
