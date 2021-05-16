package com.example.mysudubomb.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.mysudubomb.R;
import com.example.mysudubomb.activities.LoginActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleFragment extends Fragment {

    private LinearLayout fl_share;
    private FrameLayout fl_share_login;
    private ViewPager viewpager_circle;
    private TabLayout tl_circle;

    public CircleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        // Inflate the layout for this fragment
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        if (!BmobUser.isLogin()){
            fl_share.setVisibility(View.GONE);
            fl_share_login.setVisibility(View.VISIBLE);
            return;
        }

        List<String> titles = new ArrayList<>();
        titles.add("热门推荐");
        titles.add("个人发表");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShareFragment());
        fragments.add(new AdminFragment());

        FragmentViewpager fragmentViewpager = new FragmentViewpager(getFragmentManager(), fragments, titles);
        viewpager_circle.setAdapter(fragmentViewpager);
        tl_circle.setupWithViewPager(viewpager_circle);
        viewpager_circle.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initView(View view) {

        viewpager_circle = (ViewPager) view.findViewById(R.id.viewpager_circle);
        tl_circle = (TabLayout) view.findViewById(R.id.tl_circle);

        fl_share = (LinearLayout) view.findViewById(R.id.fl_share);
        fl_share_login = (FrameLayout) view.findViewById(R.id.fl_share_login);


        fl_share_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });
    }

}
