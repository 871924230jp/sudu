package com.example.mysudubomb.activities.everyactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mysudubomb.R;
import com.example.mysudubomb.myview.MyImageView;
import com.example.mysudubomb.properties.AppProperties;

public class ShalaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shala);
        initView();
    }

    private void initView() {
        MyImageView miv_shala = (MyImageView) findViewById(R.id.miv_shala);
        miv_shala.setImageUrl(AppProperties.TUIJIAN_PATH_JIXIONG);
    }
}
