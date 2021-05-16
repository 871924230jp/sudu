package com.example.mysudubomb.myview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.mysudubomb.R;
import com.example.mysudubomb.cache.ImageCache;

public class MyImageView extends ImageView {


    private Context context;
    public MyImageView(Context context) {
        super(context);
        this.context= context;
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context= context;
    }

    public MyImageView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context= context;
    }

    public  void setImageUrl(final String url){

        setImageResource(R.mipmap.loading_default);
        if (url == null)
            return;

        new Thread(){
            @Override
            public void run() {
                final Bitmap bitmap = new ImageCache(context).getBitmapFromUrl(url);
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (bitmap != null)
                            setImageBitmap(bitmap);
                        else
                            setImageResource(R.mipmap.load_failure);
                    }
                });
            }
        }.start();

    }
}