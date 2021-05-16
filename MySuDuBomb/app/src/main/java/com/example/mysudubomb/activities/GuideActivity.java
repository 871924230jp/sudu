package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mysudubomb.R;
import com.example.mysudubomb.adapter.GuideAdapter;
import com.example.mysudubomb.bean.GuideInfo;
import com.example.mysudubomb.manager.GuideManager;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.utils.NoActionBar;

import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private GuideManager manager;
    private ListView lv_guide;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initData();
        initView();
        NoActionBar.noActionBar(this);
        showList();

    }

    private void showList() {
        List<GuideInfo> list = manager.getListFromAssets(AppProperties.ASSETS_GUIDE_PATH);

        GuideAdapter adapter = new GuideAdapter(this,R.layout.guide_item,list);
        lv_guide.setAdapter(adapter);
    }

    private void initData() {
        manager = new GuideManager(this);

    }

    private void initView() {
        lv_guide = (ListView) findViewById(R.id.lv_guide);

    }

    public void onclick(View view) {
        switch (view.getId()){
            case R.id.btn_finish_guide:
                finish();
                break;
            case R.id.btn_guide_daka:
                boolean result = manager.DakaTimeSP();
                if (result){
                    Toast.makeText(this, "打卡成功", Toast.LENGTH_SHORT).show();}
                else {
                    Toast.makeText(this, "打卡失败", Toast.LENGTH_SHORT).show();
                }

        }
    }
}
