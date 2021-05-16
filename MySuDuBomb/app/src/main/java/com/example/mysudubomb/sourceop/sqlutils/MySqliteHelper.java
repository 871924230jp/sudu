package com.example.mysudubomb.sourceop.sqlutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mysudubomb.properties.AppProperties;

public class MySqliteHelper extends SQLiteOpenHelper {


    public MySqliteHelper(@Nullable Context context) {
        super(context, AppProperties.SQLITE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ AppProperties.FOOD_TABLE_NAME+"(_id integer primary key autoincrement,shicai varchar(20),kcal varchar(20),tanshui varchar(20),danbai varchar(20),zhifang varchar(20),foodImage varchar(100),description text )");
        db.execSQL("create table "+ AppProperties.COLLECT_TABLE_NAME+"(_id integer primary key autoincrement,shicai varchar(20),kcal varchar(20),tanshui varchar(20),danbai varchar(20),zhifang varchar(20),foodImage varchar(100),description text )");
        new MySqliteOP().insert(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
