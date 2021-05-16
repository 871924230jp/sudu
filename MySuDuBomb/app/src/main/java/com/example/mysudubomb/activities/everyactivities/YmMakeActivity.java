package com.example.mysudubomb.activities.everyactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mysudubomb.R;
import com.example.mysudubomb.myview.MyImageView;
import com.example.mysudubomb.properties.AppProperties;

public class YmMakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ym_make);
        initView();
    }

    private void initView() {
        MyImageView miv_yumi = (MyImageView) findViewById(R.id.miv_yumi);
        miv_yumi.setImageUrl(AppProperties.TUIJIAN_PATH_YUMI);
    }
}
