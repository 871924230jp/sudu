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

public class UpdateNameActivity extends AppCompatActivity {
    private static final int MAX_NUM = 10;
    private EditText et_upname;
    private TextView tv_upname_length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);
        NoActionBar.noActionBar(this);
        initView();
    }

    private void initView() {
        et_upname = (EditText) findViewById(R.id.et_upname);
        tv_upname_length = (TextView) findViewById(R.id.tv_upname_length);
        String object = (String) BmobUser.getObjectByKey("name");
        int length = object.length();
        tv_upname_length.setText(String.valueOf(length));
        et_upname.setText(object);

        et_upname.addTextChangedListener(new TextWatcher() {
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
                tv_upname_length.setText(String.valueOf(num));

            }
        });
    }

    public void onclick(View view) {

        switch (view.getId()){
            case R.id.btn_finish_up_name:
                finish();
                break;
            case R.id.tv_upname_over:
                String name = et_upname.getText().toString().trim();
                updateName(name);
                break;
        }
    }

    public void updateName(String name) {
        if (name.equals("")){
            Toast.makeText(this, "昵称不能为空哦", Toast.LENGTH_SHORT).show();
            return;
        }else {
            MyUser myUser = new MyUser();
            myUser.setName(name);
            BmobUser bmobUser = BmobUser.getCurrentUser(MyUser.class);
            myUser.update(bmobUser.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e==null){
                        Toast.makeText(UpdateNameActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        setResult(10);
                        finish();
                    }
                    else {
                        Toast.makeText(UpdateNameActivity.this, "修改失败", Toast.LENGTH_SHORT).show();

                    }
                }
            });


        }
    }
}
