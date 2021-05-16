package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mysudubomb.R;
import com.example.mysudubomb.manager.LoginManager;
import com.example.mysudubomb.utils.NoActionBar;
import com.example.mysudubomb.utils.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText edit_re_username;
    private EditText edit_re_password;
    private TextView tv_register;
    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        NoActionBar.noActionBar(this);
        initView();
        initData();
    }

    private void initData() {
        loginManager = new LoginManager(this);
        loginManager.setRegisterSuccess(new LoginManager.RegisterSuccess() {
            @Override
            public void success(String name, String pwd) {
                Intent intent = new Intent();
                intent.putExtra("username",name);
                intent.putExtra("password",pwd);
                setResult(88,intent);
                finish();
            }


        });
    }

    private void initView() {
        tv_register = (TextView) findViewById(R.id.tv_register);
        edit_re_username = (EditText) findViewById(R.id.edit_re_username);
        edit_re_password = (EditText) findViewById(R.id.edit_re_password);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(v);
            }
        });

    }
    public void register(View v) {
        String userName = edit_re_username.getText().toString().trim();
        String pw = edit_re_password.getText().toString().trim();
        if (userName.isEmpty() || TextUtils.isEmpty(pw)) {
            Toast.show(this, "用户的密码不能为空");
            return;
        }
        loginManager.register(userName,pw);

    }
}
