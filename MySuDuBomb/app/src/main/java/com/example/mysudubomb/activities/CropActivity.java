package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.edmodo.cropper.CropImageView;
import com.example.mysudubomb.R;
import com.example.mysudubomb.utils.NoActionBar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CropActivity extends Activity {

    private CropImageView cropImageView;
    private Uri imageUri;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);


        String data = getIntent().getStringExtra("imageUri");
        this.imageUri = Uri.parse(data);

        cropImageView = (CropImageView) findViewById(R.id.CropImageView);
        cropImageView.setImageBitmap(getBitmap(this.imageUri));
        cropImageView.setFixedAspectRatio(true);//设置允许按比例截图，如果不设置就是默认的任意大小截图
        cropImageView.setAspectRatio(1, 1);//设置比例为一比一
        cropImageView.setGuidelines(CropImageView.DEFAULT_GUIDELINES);//设置显示网格的时机，默认为on_touch

        intent = new Intent(CropActivity.this,PublishActivity.class);
        intent.putExtra("imageRatio","1:1");

        TextView crop = (TextView) findViewById(R.id.tet_ok);
        crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = cropImageView.getCroppedImage();//得到裁剪好的图片
                File file = new File(Environment.getExternalStorageDirectory(), "resultImg.jpeg");//相对路径
                qualityCompress(bitmap,file);//压缩
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                startActivity(intent);
                finish();
            }
        });

        TextView tet_no = (TextView) findViewById(R.id.tet_no);
        tet_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tet_11 = (TextView) findViewById(R.id.tet_11);
        tet_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropImageView.setAspectRatio(1,1);
                intent.putExtra("imageRatio","1:1");
            }
        });
        TextView tet_34 = (TextView) findViewById(R.id.tet_34);
        tet_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropImageView.setAspectRatio(3,4);
                intent.putExtra("imageRatio","3:4");
            }
        });
        TextView tet_43 = (TextView) findViewById(R.id.tet_43);
        tet_43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropImageView.setAspectRatio(4,3);
                intent.putExtra("imageRatio","4:3");
            }
        });
    }

    public Bitmap getBitmap(Uri bitmapUri)  {
        WindowManager wm = (WindowManager) getBaseContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        Bitmap bitmap= null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(bitmapUri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        float scaleWidth = 1,scaleHeight = 1;
        if(bitmap.getWidth() < width) {
            scaleWidth = width / bitmap.getWidth();
            scaleHeight = scaleWidth;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),bitmap.getHeight()
                , matrix, true);
        return bitmap;
    }



    public static void qualityCompress(Bitmap bmp, File file) {
        // 0-100 100为不压缩
        int quality = 60;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bmp.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
