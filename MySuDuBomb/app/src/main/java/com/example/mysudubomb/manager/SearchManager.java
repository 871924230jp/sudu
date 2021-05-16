package com.example.mysudubomb.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mysudubomb.bean.FoodInfo;
import com.example.mysudubomb.sourceop.sqlutils.MySqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class SearchManager {

    private final MySqliteHelper helper;
    private Cursor cursor;

    public SearchManager(Context context){

        helper = new MySqliteHelper(context);
    }

    public List<FoodInfo> getFoodListFromDB(String content){
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from food where shicai like '%" + content + "%'", null);
        List<FoodInfo> foodList = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()) {
                FoodInfo foodInfo = new FoodInfo();
                foodInfo.setShicai(cursor.getString(1));
                foodInfo.setKcal(cursor.getString(2));
                foodInfo.setTanshui(cursor.getString(3));
                foodInfo.setDanbai(cursor.getString(4));
                foodInfo.setZhifang(cursor.getString(5));
                foodInfo.setFoodImagePath(cursor.getString(6));
                foodInfo.setDescription(cursor.getString(7));
                foodList.add(foodInfo);
            }
        }
        db.close();
        return foodList;
    }
    public void closeCursor(){
        if (cursor!=null){
            cursor.close();
        }

    }
    public boolean noSearch(){
        if (cursor != null) {
            int columnCount = cursor.getCount();
            if (columnCount == 0) {
                     return true;
            }
        }
        return false;
    }


}
