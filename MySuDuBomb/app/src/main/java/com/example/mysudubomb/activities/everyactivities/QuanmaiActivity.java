package com.example.mysudubomb.activities.everyactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mysudubomb.R;
import com.example.mysudubomb.myview.MyImageView;
import com.example.mysudubomb.properties.AppProperties;

public class QuanmaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanmai);
        initView();
    }

    private void initView() {
        MyImageView miv_quanmai = (MyImageView) findViewById(R.id.miv_quanmai);
        miv_quanmai.setImageUrl(AppProperties.TUIJIAN_PATH_QUANMAI);
    }
}
