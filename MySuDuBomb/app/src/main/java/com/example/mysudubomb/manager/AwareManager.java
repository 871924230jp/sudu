package com.example.mysudubomb.manager;

import android.content.Context;

import com.example.mysudubomb.bean.FoodInfo;
import com.example.mysudubomb.sourceop.sqlutils.MySqliteOP;

import java.util.List;

public class AwareManager {

    public List<FoodInfo>  getFoodList(Context context,String table){
        List<FoodInfo> foodList = new MySqliteOP(context).queryFoodList(table);
        return foodList;

    }
}
