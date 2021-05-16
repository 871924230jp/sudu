package com.example.mysudubomb.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.mysudubomb.bean.FoodInfo;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.sourceop.sqlutils.MySqliteHelper;

public class XiangXiManager {

    private final MySqliteHelper helper;

    private  Context context;
    public XiangXiManager(Context context){
        helper = new MySqliteHelper(context);
        this.context=context;

    }
    public boolean queryFromFood(FoodInfo foodInfo){

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(AppProperties.COLLECT_TABLE_NAME, new String[]{"shicai"}, "shicai=?",new String[]{foodInfo.getShicai()}, null, null,null);

        if (cursor !=null &&  cursor.getCount()>0){
            cursor.close();
            return true;
        }else {
        cursor.close();
        return false;
        }
    }
    public  void addFoodInfo(FoodInfo foodInfo){
        SQLiteDatabase db = this.helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("shicai", foodInfo.getShicai());
        values.put("kcal", foodInfo.getKcal());
        values.put("tanshui", foodInfo.getDanbai());
        values.put("danbai", foodInfo.getZhifang());
        values.put("zhifang", foodInfo.getTanshui());
        values.put("foodimage", foodInfo.getFoodImagePath());
        values.put("description", foodInfo.getDescription());
        long result = db.insert(AppProperties.COLLECT_TABLE_NAME, null, values);
        if (result > 0)
            Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "收藏失败", Toast.LENGTH_SHORT).show();
        db.close();

    }
    public void  deleteFoodInfo(FoodInfo foodInfo){
        SQLiteDatabase db = this.helper.getReadableDatabase();
        int result = db.delete(AppProperties.COLLECT_TABLE_NAME, "shicai = ?",new String[]{foodInfo.getShicai()});/*whereclause查询条件  1是全删*/
        if (result>0)
            Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "取消收藏失败", Toast.LENGTH_SHORT).show();
        db.close();
    }
}
