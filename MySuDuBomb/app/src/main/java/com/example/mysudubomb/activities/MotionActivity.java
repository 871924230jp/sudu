package com.example.mysudubomb.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.mysudubomb.R;
import com.example.mysudubomb.manager.MotionManager;
import com.example.mysudubomb.utils.NoActionBar;

public class MotionActivity extends AppCompatActivity {

    private PopupWindow popupWindow;

    private EditText et_m_project;

    private MotionManager manager;
    private EditText et_m_kg;
    private EditText et_m_runtime;
    private TextView tv_motion_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);
        NoActionBar.noActionBar(this);
        initView();
        initData();

    }

    private void initData() {

        manager = new MotionManager(this);
    }

    private void initView() {
        et_m_project = (EditText)findViewById(R.id.et_m_project);
        et_m_project.setInputType(InputType.TYPE_NULL);/*禁止输入*/
        et_m_kg = (EditText) findViewById(R.id.et_m_kg);
        et_m_runtime = (EditText) findViewById(R.id.et_m_runtime);
        tv_motion_result = (TextView) findViewById(R.id.tv_motion_result);


    }

    public void onclick(View view) {

        switch (view.getId()){
            case  R.id.et_m_project:
                showShadow();
                break;
            case R.id.btn_motion:
                compute();
                break;
            case R.id.tv_motion_mp:
                et_m_project.setText(R.string.run);
                popupWindow.dismiss();
                break;
            case R.id.tv_motion_yy:
                et_m_project.setText(R.string.swim);
                popupWindow.dismiss();
                break;
            case R.id.tv_motion_kz:
                et_m_project.setText(R.string.go);
                popupWindow.dismiss();
                break;
            case R.id.tv_motion_wd:
                et_m_project.setText(R.string.dance);
                popupWindow.dismiss();
                break;
            case R.id.btn_finish_motion:
                finish();
                break;
        }


    }

    private void compute() {
        String kg = et_m_kg.getText().toString().trim();
        String time = et_m_runtime.getText().toString().trim();
        String project = et_m_project.getText().toString().trim();
        Double result = manager.getResultFromMotion(kg, time, project);
        if (result != null){
            tv_motion_result.setText(result.toString().format("%.2f", result)+R.string.Kcal);
            /*保留两位小数*/
        }
    }

    private void addBackground() {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;//调节透明度
        getWindow().setAttributes(lp);
        //dismiss时恢复原样
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);

            }
        });
    }
    private void showShadow() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.activity_motion_popup, null, false);//引入弹窗布局



        popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);

        //设置背景透明
        addBackground();


        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        //引入依附的布局
        View parentView = LayoutInflater.from(MotionActivity.this).inflate(R.layout.activity_motion, null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
    }
}
