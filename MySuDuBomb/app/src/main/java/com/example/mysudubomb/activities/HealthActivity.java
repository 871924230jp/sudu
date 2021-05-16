package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mysudubomb.R;
import com.example.mysudubomb.manager.HealthManager;
import com.example.mysudubomb.utils.NoActionBar;

public class HealthActivity extends AppCompatActivity {

    private EditText et_age;
    private EditText et_height;
    private EditText et_weight;
    private RadioGroup rg_xb;
    private HealthManager healthManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        NoActionBar.noActionBar(this);
        initView();
        initData();
    }

    private void initView() {
        et_age = (EditText) findViewById(R.id.et_age);
        et_height = (EditText) findViewById(R.id.et_height);
        et_weight = (EditText) findViewById(R.id.et_weight);
        rg_xb = (RadioGroup) findViewById(R.id.rg_xb);
        et_weight.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        et_height.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        et_age.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    private void initData() {

        healthManager = new HealthManager(HealthActivity.this);
    }

    public void onclick(View view) {
        switch (view.getId()){
            case R.id.btn_finish_health:
                finish();
                break;
            case R.id.btn_compute:
                String shengao = et_height.getText().toString().trim();
                String tizhong = et_weight.getText().toString().trim();
                String nianling = et_age.getText().toString().trim();
                int id = this.rg_xb.getCheckedRadioButtonId();
                setIntentToCompute(id,shengao,tizhong,nianling);
                break;
        }
    }

    private void setIntentToCompute(int id, String shengao, String tizhong, String nianling) {
        if (id == -1){
            Toast.makeText(this, "别忘了选择性别哦", Toast.LENGTH_SHORT).show();
            return ;
        }
        RadioButton rbt = (RadioButton) findViewById(id);
        CharSequence xingbie = rbt.getText();
        Intent intent = healthManager.setintent( ComputeActivity.class,xingbie, shengao, tizhong, nianling);
        if (intent != null) {
            startActivity(intent);
        }
    }
}
