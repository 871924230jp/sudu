package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mysudubomb.R;
import com.example.mysudubomb.adapter.FoodAdapter;
import com.example.mysudubomb.bean.FoodInfo;
import com.example.mysudubomb.manager.AwareManager;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.utils.NoActionBar;

import java.util.List;

public class AwareActivity extends AppCompatActivity {

    private ListView lv_aware;
    private AwareManager awareManager;
    private List<FoodInfo> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aware);
        NoActionBar.noActionBar(this);
        initView();
        initData();
        show();

    }

    private void show() {
        foodList = awareManager.getFoodList(this, AppProperties.FOOD_TABLE_NAME);
        FoodAdapter foodAdapter = new FoodAdapter(this, foodList,R.layout.food_item);
        lv_aware.setAdapter(foodAdapter);


        lv_aware.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                FoodInfo foodInfo = foodList.get(position);
                intent.putExtra(AppProperties.INTENT_KEY,foodInfo);
                intent.setClass(AwareActivity.this, XiangXiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        awareManager = new AwareManager();
    }

    private void initView() {
        lv_aware = (ListView) findViewById(R.id.lv_aware);
    }

    public void onclick(View view) {
        finish();
    }
}
