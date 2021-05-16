package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.MyUser;
import com.example.mysudubomb.utils.NoActionBar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UpdateDesActivity extends AppCompatActivity {
    private static final int MAX_NUM = 30;
    private EditText et_updes;
    private TextView tv_updes_length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_des);
        NoActionBar.noActionBar(this);
        initView();
    }

    private void initView() {
        et_updes = (EditText) findViewById(R.id.et_updes);
        tv_updes_length = (TextView) findViewById(R.id.tv_updes_length);
        String object = (String) BmobUser.getObjectByKey("description");
        int length = object.length();
        tv_updes_length.setText(String.valueOf(length));
        et_updes.setText(object);

        et_updes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > MAX_NUM) {
                    s.delete(MAX_NUM, s.length());
                }
                int num = s.length();
                tv_updes_length.setText(String.valueOf(num));

            }
        });
    }
    public void onclick(View view) {

        switch (view.getId()){
            case R.id.btn_finish_up_des:
                finish();
                break;
            case R.id.tv_updes_over:
                String des = et_updes.getText().toString().trim();
                updateDes(des);
                break;
        }
    }

    private void updateDes(String des) {

        if (des.equals("")){
            Toast.makeText(this, "简介不能为空哦", Toast.LENGTH_SHORT).show();
            return;
        }else {
            MyUser myUser = new MyUser();
            myUser.setDescription(des);
            BmobUser bmobUser = BmobUser.getCurrentUser(MyUser.class);
            myUser.update(bmobUser.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e==null){

                        Toast.makeText(UpdateDesActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        setResult(11);
                        finish();

                    }
                    else {
                        Toast.makeText(UpdateDesActivity.this, "修改失败", Toast.LENGTH_SHORT).show();

                    }
                }
            });


        }
    }
}
