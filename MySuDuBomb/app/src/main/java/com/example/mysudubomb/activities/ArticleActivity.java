package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.ArticleInfo;
import com.example.mysudubomb.enumeration.SourceType;
import com.example.mysudubomb.manager.ArticleManager;
import com.example.mysudubomb.myview.MyImageView;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.utils.NoActionBar;

public class ArticleActivity extends AppCompatActivity {

    private MyImageView miv_artile;
    private TextView tv_ar_content_one;
    private TextView tv_ar_content_three;
    private TextView tv_ar_content_two;
    private TextView tv_ar_ds_one;
    private TextView tv_ar_ds_two;
    private TextView tv_ar_ds_three;
    private TextView tv_ar_author;
    private TextView tv_ar_link;
    private TextView tv_ar_title;
    private TextView tv_ar_pubDate;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {

            articleInfo = (ArticleInfo) msg.obj;
            miv_artile.setImageUrl(articleInfo.getImage());
            tv_ar_content_one.setText(articleInfo.getContentone());
            tv_ar_content_two.setText(articleInfo.getContenttwo());
            tv_ar_content_three.setText(articleInfo.getContentthree());
            tv_ar_ds_one.setText(articleInfo.getDescriptionone());
            tv_ar_ds_two.setText(articleInfo.getDescriptiontwo());
            tv_ar_ds_three.setText(articleInfo.getDescriptionthree());
            tv_ar_pubDate.setText(articleInfo.getPubDate());
            tv_ar_title.setText(articleInfo.getTitle());
            tv_ar_author.setText(articleInfo.getAuthor());


        }
    };
    private ArticleManager articleManager;
    private SourceType type;
    private ArticleInfo articleInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        NoActionBar.noActionBar(this);
        initView();
        initData();
        show(AppProperties.ASSETS_FILE_PATH,type);
    }

    private void initData() {
        articleManager = new ArticleManager(this);
        Intent intent = getIntent();
        type = (SourceType) intent.getSerializableExtra(AppProperties.INTENT_ENUM_KEY);
    }

    private void show(final String path, final SourceType sourceType) {
        new Thread(){
            @Override
            public void run() {
                ArticleInfo articleInfo = articleManager.getInfoFromSource(path,sourceType);
                Message msg = Message.obtain();
                    msg.obj = articleInfo;
                    handler.sendMessage(msg);
            }
        }.start();
    }

    private void initView() {
        miv_artile = (MyImageView) findViewById(R.id.miv_artile);
        tv_ar_content_one = (TextView) findViewById(R.id.tv_ar_content_one);
        tv_ar_content_two = (TextView) findViewById(R.id.tv_ar_content_two);
        tv_ar_content_three = (TextView) findViewById(R.id.tv_ar_content_three);
        tv_ar_ds_one = (TextView) findViewById(R.id.tv_ar_ds_one);
        tv_ar_ds_two = (TextView) findViewById(R.id.tv_ar_ds_two);
        tv_ar_ds_three = (TextView) findViewById(R.id.tv_ar_ds_three);
        tv_ar_author = (TextView) findViewById(R.id.tv_ar_author);
        tv_ar_link = (TextView) findViewById(R.id.tv_ar_link);
        tv_ar_title = (TextView) findViewById(R.id.tv_ar_title);
        tv_ar_pubDate = (TextView) findViewById(R.id.tv_ar_pubDate);
    }

    public void onclick(View view) {


        switch (view.getId()){

            case R.id.tv_ar_link:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(articleInfo.getLink());
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.btn_finish_article:
                finish();
                break;



        }

    }

}
