package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.HealthInfo;
import com.example.mysudubomb.manager.ComputeManager;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.sourceop.ComputeBMIop;
import com.example.mysudubomb.sourceop.ComputeBMRop;
import com.example.mysudubomb.sourceop.ComputeKGop;
import com.example.mysudubomb.utils.NoActionBar;

public class ComputeActivity extends AppCompatActivity {


    private TextView tv_compute_age;
    private TextView tv_compute_bmi;
    private TextView tv_compute_bmr;
    private TextView tv_compute_lxkg;
    private TextView tv_compute_sjkg;
    private ComputeManager computeManager;
    private HealthInfo healthInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute);
        NoActionBar.noActionBar(this);
        initView();
        initData();
        show();
    }
    private void show() {
        String bmi = computeManager.getStringFromComput(new ComputeBMIop());
        tv_compute_bmi.setText(bmi);
        String bmr = computeManager.getStringFromComput(new ComputeBMRop());
        tv_compute_bmr.setText(bmr);
        String kg = computeManager.getStringFromComput(new ComputeKGop());
        tv_compute_lxkg.setText(kg);
        tv_compute_sjkg.setText(healthInfo.getTizhong());
        tv_compute_age.setText(healthInfo.getNianling());
    }

    private void initData() {
        Intent intent = getIntent();
        healthInfo = (HealthInfo)intent.getParcelableExtra(AppProperties.HEALTH_INTENT_COMPUTE);
        computeManager = new ComputeManager(ComputeActivity.this, healthInfo);

    }

    private void initView() {
        tv_compute_age = (TextView) findViewById(R.id.tv_compute_age);
        tv_compute_bmi = (TextView) findViewById(R.id.tv_compute_bmi);
        tv_compute_bmr = (TextView) findViewById(R.id.tv_compute_bmr);
        tv_compute_lxkg = (TextView) findViewById(R.id.tv_compute_lxkg);
        tv_compute_sjkg = (TextView) findViewById(R.id.tv_compute_sjkg);
    }

    public void onclick(View view) {
        finish();
    }
}
