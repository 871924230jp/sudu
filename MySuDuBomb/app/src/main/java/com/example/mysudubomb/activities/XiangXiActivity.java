package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.FoodInfo;
import com.example.mysudubomb.manager.XiangXiManager;
import com.example.mysudubomb.myview.MyImageView;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.utils.NoActionBar;

public class XiangXiActivity extends AppCompatActivity {

    private TextView tv_shicai_xiangxi;
    private TextView tv_tanshui_xiangxi;
    private TextView tv_danbai_xiangxi;
    private TextView tv_zhifang_xiangxi;
    private TextView tv_kcal_xiangxi;
    private TextView tv_description_xiangxi;
    private MyImageView iv_image_xiangxi;
    private FoodInfo foodInfo;
    private XiangXiManager manager;
    private TextView tv_collect_xiangxi;
    private TextView tv_title_xiangxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_xiangxi);
        NoActionBar.noActionBar(this);
        initView();
        initData();
        show();
    }

    private void show() {
        Intent intent = getIntent();
        foodInfo = (FoodInfo) intent.getSerializableExtra(AppProperties.INTENT_KEY);
        tv_title_xiangxi.setText(foodInfo.getShicai());
        tv_zhifang_xiangxi.setText(foodInfo.getZhifang());
        tv_danbai_xiangxi.setText(foodInfo.getDanbai());
        tv_tanshui_xiangxi.setText(foodInfo.getTanshui());
        tv_shicai_xiangxi.setText(foodInfo.getShicai());
        tv_description_xiangxi.setText(foodInfo.getDescription());
        iv_image_xiangxi.setImageUrl(foodInfo.getFoodImagePath());
        tv_kcal_xiangxi.setText(foodInfo.getKcal());
        boolean result = manager.queryFromFood(foodInfo);

        if (result){
            tv_collect_xiangxi.setText("已收藏");
            return;
        }
        tv_collect_xiangxi.setText("收藏");
        return;
    }

    private void initData() {
        manager = new XiangXiManager(this);

    }

    private void initView() {

        tv_title_xiangxi = (TextView) findViewById(R.id.tv_title_xiangxi);
        tv_collect_xiangxi = (TextView) findViewById(R.id.tv_collect_xiangxi);
        tv_shicai_xiangxi = (TextView) findViewById(R.id.tv_shicai_xiangxi);
        tv_tanshui_xiangxi = (TextView) findViewById(R.id.tv_tanshui_xiangxi);
        tv_danbai_xiangxi = (TextView) findViewById(R.id.tv_danbai_xiangxi);
        tv_zhifang_xiangxi = (TextView) findViewById(R.id.tv_zhifang_xiangxi);
        tv_kcal_xiangxi = (TextView) findViewById(R.id.tv_kcal_xiangxi);
        tv_description_xiangxi = (TextView) findViewById(R.id.tv_description_xiangxi);
        iv_image_xiangxi = (MyImageView) findViewById(R.id.iv_image_xiangxi);
    }

    public void onclick(View view) {
        switch (view.getId()){
            case R.id.btn_finish_xiangxi:
                setResult(AppProperties.RESULT_CODE_CONTENT);
                finish();
                break;
            case R.id.tv_collect_xiangxi:
                collect();
                break;
        }

    }

    private void collect() {
        if(this.tv_collect_xiangxi.getText().toString().equals("收藏")) {
            manager.addFoodInfo(foodInfo);
            tv_collect_xiangxi.setText("已收藏");
            return;
        }
        manager.deleteFoodInfo(foodInfo);
        tv_collect_xiangxi.setText("收藏");
        return;
    }
}
