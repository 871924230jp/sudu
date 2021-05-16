package com.example.mysudubomb.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;


public class ShareManager {

    private  Context context;


    public ShareManager(Context context){

        this.context=context;

    }




    public Uri getUriFromFile(){

        File file;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            file = Environment.getExternalStorageDirectory();
        }
        else{
            file =context.getFilesDir();
        }
        Uri uri = Uri.fromFile(new File(file, "xiangji.jpg"));
        return uri;
    }




}
