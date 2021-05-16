package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.Circle;
import com.example.mysudubomb.bean.MyUser;

import com.example.mysudubomb.utils.GetRealPath;
import com.example.mysudubomb.utils.NoActionBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class PublishActivity extends AppCompatActivity {

    private EditText et_publish;
    private ImageView iv_publish;


    private Uri data;
    private String ratio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        NoActionBar.noActionBar(this);
        initView();
        initData();
        show();


    }

    private void initData() {

    }

    private void show() {
        Intent intent = getIntent();
        data = intent.getData();
        ratio = intent.getStringExtra("imageRatio");

        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
            iv_publish.setImageBitmap(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void initView() {
        et_publish = (EditText) findViewById(R.id.et_publish);
        iv_publish = (ImageView) findViewById(R.id.iv_publish);


    }

    public void onclick(View view) {
        switch (view.getId()){
            case  R.id.btn_publish_over:
                String content = et_publish.getText().toString().trim();
                if (!content.isEmpty()){
                    publish(content);
                }
                else {
                    Toast.makeText(this, "添加点描述吧", Toast.LENGTH_SHORT).show();
                }
                break;
            case  R.id.btn_publish_fs:
                finish();
                break;
        }
    }

    private void publish(String content) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在发表");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        BmobFile bmobFile = new BmobFile(new File(GetRealPath.getRealPathFromUri(this,data)));
        bmobFile.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    MyUser user = BmobUser.getCurrentUser(MyUser.class);
                    Circle circle = new Circle();
                    circle.setLikes(0);
                    circle.setImageRatio(ratio);
                    circle.setContent(content);
                    circle.setImage(bmobFile.getUrl());
                    circle.setAuthor(user.getName());
                    circle.setAuthorImage(user.getHeadImage());
                    circle.setUser(user);
                    circle.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e==null){
                                Toast.makeText(PublishActivity.this, "发表成功", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                finish();
                            }else {
                                Toast.makeText(PublishActivity.this, "发表失败", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(PublishActivity.this, "发表失败", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });
    }
}
