package com.example.mysudubomb.manager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.mysudubomb.activities.MajorActivity;
import com.example.mysudubomb.bean.MyUser;
import com.example.mysudubomb.properties.AppProperties;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class LoginManager {
    private Context context;
   private RegisterSuccess registerSuccess;
    public LoginManager(Context context) {
        this.context = context;
    }

    /**
     * 登录
     *
     * @param name
     * @param pwd
     */
    public void login( String name, String pwd) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("正在加载，请稍后...");
        dialog.setMessage("等待中");
        dialog.setCancelable(false);
        dialog.show();
        MyUser myUser = new MyUser();
        myUser.setUsername(name);
        myUser.setPassword(pwd);
        myUser.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser o, BmobException e) {
                dialog.dismiss();
                if (e==null){
                    LoginSuccess();
                }else {
                    Toast.makeText(context, "用户名密码不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void LoginSuccess(){
        Intent intent = new Intent(context, MajorActivity.class);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    /**
     * 注册
     *
     * @param name
     * @param pwd
     */
    public interface RegisterSuccess{
        void success(String name, String pwd);
    }
    public void setRegisterSuccess(RegisterSuccess registerSuccess){
        this.registerSuccess = registerSuccess;
    }
    public void register(String name, String pwd) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("正在加载，请稍后...");
        dialog.setMessage("等待中");
        dialog.setCancelable(false);
        dialog.show();
        MyUser myUser = new MyUser();
        myUser.setUsername(name);
        myUser.setPassword(pwd);
        myUser.setDescription("暂无描述");
        myUser.setName("匿名网友");
        myUser.setHeadImage(null);
        myUser.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser o, BmobException e) {
                dialog.dismiss();
                if (e==null){
                    registerSuccess.success(name,pwd);
                  Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "该用户已被注册", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
