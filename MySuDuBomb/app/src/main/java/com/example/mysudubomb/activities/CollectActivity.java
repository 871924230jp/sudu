package com.example.mysudubomb.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mysudubomb.R;
import com.example.mysudubomb.adapter.FoodAdapter;
import com.example.mysudubomb.bean.FoodInfo;
import com.example.mysudubomb.manager.CollectManager;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.utils.NoActionBar;

import java.util.List;

public class CollectActivity extends AppCompatActivity {

    private ListView lv_collect;
    private CollectManager manager;
    private List<FoodInfo> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        NoActionBar.noActionBar(this);
        initView();
        initData();
        show();
    }

    private void initData() {
        manager = new CollectManager();
    }

    private void show() {
        foodList = manager.getFoodList(this, AppProperties.COLLECT_TABLE_NAME);
        FoodAdapter foodAdapter = new FoodAdapter(this, foodList,R.layout.food_item);
        lv_collect.setAdapter(foodAdapter);

        lv_collect.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                FoodInfo foodInfo = foodList.get(position);
                intent.putExtra(AppProperties.INTENT_KEY,foodInfo);
                intent.setClass(CollectActivity.this, XiangXiActivity.class);
                startActivityForResult(intent,AppProperties.RESULT_CODE_CONTENT);
            }
        });
    }

    private void initView() {
        lv_collect = (ListView) findViewById(R.id.lv_collect);
    }

    public void onclick(View view) {
        finish();
    }

    @Override/*finish完马上执行*/
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        show();

    }
}
