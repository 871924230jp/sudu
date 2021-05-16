package com.example.mysudubomb.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.MyUser;
import com.example.mysudubomb.manager.UpUserManager;
import com.example.mysudubomb.myview.MyImageView;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.utils.GetRealPath;
import com.example.mysudubomb.utils.NoActionBar;
import com.longsh.optionframelibrary.OptionBottomDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class UpdataUserActivity extends AppCompatActivity {


    private UpUserManager manager;
    private TextView tv_up_name;
    private TextView tv_up_des;
    private TextView tv_up_gender;
    private ArrayList<String> stringList;
    private CircleImageView civ_up_headimage;

    private Uri imageuri;
    private Uri data1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_user);
        NoActionBar.noActionBar(this);
        initView();
        initData();
    }

    private void initData() {


        manager = new UpUserManager(this);
        stringList = new ArrayList<>();
        stringList.add("男");
        stringList.add("女");

        String name = (String) BmobUser.getObjectByKey("name");
        String gender = (String) BmobUser.getObjectByKey("gender");
        String description = (String) BmobUser.getObjectByKey("description");

        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        BmobFile image = user.getHeadImage();

        if (image==null){
            civ_up_headimage.setBackgroundResource(R.mipmap.mine_touxiang);
        }else {
            String url = image.getUrl();
            Glide.with(UpdataUserActivity.this).load(url).into(civ_up_headimage);

            /*String url = image.getUrl();
            String fileUrl = image.getFileUrl();

            String path = imageuri.getPath();
            Bitmap bitmap = BitmapFactory.decodeFile(path);*/

            //  civ_up_headimage.setImageUrl(url);


        }
        tv_up_name.setText(name);
        tv_up_gender.setText(gender);
        if (description.length()>8){
            tv_up_des.setText(description.substring(0,7)+"...");
        }else {
            tv_up_des.setText(description);
        }
    }

    private void initView() {
        tv_up_name = (TextView) findViewById(R.id.tv_up_name);
        tv_up_gender = (TextView) findViewById(R.id.tv_up_gender);
        tv_up_des = (TextView) findViewById(R.id.tv_up_des);
        civ_up_headimage = (CircleImageView) findViewById(R.id.civ_up_headimage);

    }

    public void onclick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_updata_image:
                getPhotoFromPhotos(87);
                break;
            case R.id.rl_updata_name:
                intent.setClass(this,UpdateNameActivity.class);
                startActivityForResult(intent,5);
                break;
            case R.id.rl_updata_gender:
                updateGender();


                break;
            case R.id.rl_updata_des:

                intent.setClass(this,UpdateDesActivity.class);

                startActivityForResult(intent,6);
                break;
            case R.id.btn_finish_up:
                finish();

                break;

            case R.id.btn_up_tuichu:
                BackToLogin(intent);

                break;
        }
    }

    private void BackToLogin(Intent intent) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示").setMessage("您确定要退出登陆吗").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BmobUser.logOut();
                intent.setClass(UpdataUserActivity.this, LoginActivity.class);
                startActivity(intent);
                UpdataUserActivity.this.finish();
                MajorActivity.instance.finish();
            }
        }).setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setCancelable(false).show();


    }


    private void updateGender() {
        OptionBottomDialog dialog = new OptionBottomDialog(this, stringList);
        dialog.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        updateGenderToBmob("男");
                        break;
                    case 1:
                        updateGenderToBmob("女");
                        break;
                    default:
                        break;
                }
                dialog.dismiss();
            }
        });
    }

    private void updateGenderToBmob(String gender) {

        MyUser myUser = new MyUser();
        myUser.setGender(gender);
        BmobUser bmobUser = BmobUser.getCurrentUser(MyUser.class);
        myUser.update(bmobUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Toast.makeText(UpdataUserActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    tv_up_gender.setText(gender);
                }else {

                    Toast.makeText(UpdataUserActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        switch (resultCode){


            case 10:
                String name = (String) BmobUser.getObjectByKey("name");
                tv_up_name.setText(name);
                setResult(AppProperties.RESULT_CODE_USERUPDATE);
                break;
            case 11:
                String des = (String) BmobUser.getObjectByKey("description");
                if (des.length()>8){
                    tv_up_des.setText(des.substring(0,7)+"...");
                }else {
                    tv_up_des.setText(des);
                }
                setResult(AppProperties.RESULT_CODE_USERUPDATE);
                break;

            case RESULT_OK:

                if (requestCode==87){
                    data1 = data.getData();

                    cropImageByUri(88, data1,200,200);


                }else if (requestCode==88){

                    String realPathFromUri = GetRealPath.getRealPathFromUri(this, imageuri);
                    upHeadImage(realPathFromUri);
                }

                break;
        }

    }
    /**
     * 用给定的uri,调用 裁剪程序
     */
    public  void cropImageByUri(int requestCode, Uri from, int outputX, int outputY) {
        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(from, "image/*");
        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);//剪切框宽高比例
        intent.putExtra("aspectY", 1);//剪切框宽高比例
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat","JPEG");
        File file = new File(Environment.getExternalStorageDirectory()+"/" + "headImage.jpeg");
        imageuri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
         // no face detection
        startActivityForResult(intent, requestCode);


    }




    /**
     * 相册选择照片, 需要特殊处理才能获取到图片路径
     */
    public  void getPhotoFromPhotos(int requestCode) {
        Intent intent = new Intent();
  //      intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, requestCode);
    }

    private  void upHeadImage(String realPathFromUri){
        BmobFile bmobFile = new BmobFile(new File(realPathFromUri));
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在上传。。。");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        bmobFile.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    MyUser myUser = new MyUser();
                    myUser.setHeadImage(bmobFile);
                    BmobUser bmobUser = BmobUser.getCurrentUser(MyUser.class);
                    myUser.update(bmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
                                setResult(AppProperties.RESULT_CODE_USERUPDATE);
                                Toast.makeText(UpdataUserActivity.this, "上传头像成功", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                String url = bmobFile.getUrl();
                                System.out.println("------url+"+url);
                                Glide.with(UpdataUserActivity.this).load(url).into(civ_up_headimage);
                            }
                            else {
                                Toast.makeText(UpdataUserActivity.this, "上传头像失败", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }else {
                    Toast.makeText(UpdataUserActivity.this, "没反应", Toast.LENGTH_SHORT).show();

                }
            }
        });




    }


}
