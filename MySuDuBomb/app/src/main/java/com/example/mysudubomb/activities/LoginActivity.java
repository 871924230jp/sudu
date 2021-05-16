package com.example.mysudubomb.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import com.example.mysudubomb.R;
import com.example.mysudubomb.manager.LoginManager;
import com.example.mysudubomb.receiver.NetworkChangeReceiver;
import com.example.mysudubomb.utils.NoActionBar;
import com.example.mysudubomb.utils.Toast;

import cn.bmob.v3.Bmob;

public class LoginActivity extends AppCompatActivity {
    public EditText et_username;
    public EditText et_pw;
    public CheckBox cb_rm;

    public Button btn0, btnRegister;
    private IntentFilter intentFilter;

    private NetworkChangeReceiver networkChangeReceiver;

    private Button btn1;

    private LoginManager loginModel;
    private Button youKe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        NoActionBar.noActionBar(this);
        initView();
        initData();
        receiver();

    }

    private void receiver() {
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//action中的String代表监听的是网络变化的广播
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }

    private void initData() {
        loginModel = new LoginManager(this);

    }


    private void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_pw = (EditText) findViewById(R.id.et_pw);
        btn0 = (Button) findViewById(R.id.btn_go);
        btnRegister = findViewById(R.id.btn_register);
        youKe = (Button) findViewById(R.id.tv_youke);
        youKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginModel.LoginSuccess();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,666);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 88){
            String username = data.getStringExtra("username");
            String pwd= data.getStringExtra("password");
            et_username.setText(username);
            et_pw.setText(pwd);
        }

    }

    public void login(View v) {
         String userName = et_username.getText().toString().trim();
        String pw = et_pw.getText().toString().trim();
        if (userName.isEmpty() || TextUtils.isEmpty(pw)) {
            Toast.show(this, "用户名和密码不能为空");
            return;
        }
        loginModel.login(userName,pw);
    }


}
