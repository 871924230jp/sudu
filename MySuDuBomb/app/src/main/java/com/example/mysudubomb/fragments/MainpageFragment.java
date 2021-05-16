package com.example.mysudubomb.fragments;



import android.content.Intent;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;


import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.mysudubomb.R;
import com.example.mysudubomb.activities.ArticleActivity;
import com.example.mysudubomb.activities.AudioActivity;
import com.example.mysudubomb.activities.AwareActivity;
import com.example.mysudubomb.activities.BannerArticleActivity;
import com.example.mysudubomb.activities.EveryActivity;
import com.example.mysudubomb.activities.GuideActivity;
import com.example.mysudubomb.activities.SearchActivity;
import com.example.mysudubomb.cache.GlideImageLoader;
import com.example.mysudubomb.enumeration.SourceType;

import com.example.mysudubomb.properties.AppProperties;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainpageFragment extends Fragment {


    private EditText et_mainpage;
    private ImageView riv_one;
    private ImageView riv_two;
    private ImageView riv_three;
    private Banner banner;
    private ArrayList<String> images;
    private Button btn_swck;
    private Button btn_mrtj;
    private Button btn_yszn;
    private Intent intent;

    public MainpageFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mainpage, container, false);
        initData();
        initView(view);
        return view;
    }
    private void initData() {
        images = new ArrayList<>();
        images.add(AppProperties.BANNER_PATH_ONE);
        images.add(AppProperties.BANNER_PATH_TWO);
        intent = new Intent();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView(View view) {
        et_mainpage = (EditText) view.findViewById(R.id.et_mainpage);
        et_mainpage.setInputType(InputType.TYPE_NULL);
        btn_yszn = (Button) view.findViewById(R.id.btn_yszn);
        btn_mrtj = (Button) view.findViewById(R.id.btn_mrtj);
        btn_swck = (Button) view.findViewById(R.id.btn_swck);

        riv_one = (ImageView) view.findViewById(R.id.riv_one);
        riv_two = (ImageView) view.findViewById(R.id.riv_two);
        riv_three = (ImageView) view.findViewById(R.id.riv_three);


        banner = (Banner) view.findViewById(R.id.banner);
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR   显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置标题集合（当banner样式有显示title时）
        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置是否允许手动滑动轮播图
        banner.setViewPagerIsScroll(true);
        //设置是否自动轮播（不设置则默认自动）
        banner.isAutoPlay(true);
        //设置轮播图片间隔时间（不设置默认为2000）
        banner.setDelayTime(2500);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
            }
        });
        banner.setClipToOutline(true);
        banner.setImages(images)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent();
                        switch (position)
                        {
                            case 0:
                                intent.setClass(getActivity(), BannerArticleActivity.class);
                                startActivity(intent);
                                break;
                            case 1:
                                intent.setClass(getActivity(), AudioActivity.class);
                                startActivity(intent);
                                break;
                        }
                    }
                })
                .start();


        btn_yszn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), GuideActivity.class);
                startActivity(intent);
            }
        });
        btn_mrtj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), EveryActivity.class);
                startActivity(intent);
            }
        });
        btn_swck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), AwareActivity.class);
                startActivity(intent);
            }
        });
        riv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(AppProperties.INTENT_ENUM_KEY, SourceType.SOURCE_ONE);
                intent.setClass(getActivity(), ArticleActivity.class);
                startActivity(intent);
            }
        });
        riv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(AppProperties.INTENT_ENUM_KEY, SourceType.SOURCE_TWO);
                intent.setClass(getActivity(), ArticleActivity.class);
                startActivity(intent);
            }
        });
        riv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(AppProperties.INTENT_ENUM_KEY, SourceType.SOURCE_THREE);
                intent.setClass(getActivity(), ArticleActivity.class);
                startActivity(intent);
            }
        });
        et_mainpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.start();
    }
}
