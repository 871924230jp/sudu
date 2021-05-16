package com.example.mysudubomb.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.sourceop.sqlutils.MySqliteHelper;

public class MineManager {

    private Context context;
    private final MySqliteHelper helper;

    public  MineManager(Context context){
        this.context=context;
        helper = new MySqliteHelper(context);
    }


}
