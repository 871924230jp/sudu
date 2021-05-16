package com.example.mysudubomb.activities;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mysudubomb.R;
import com.example.mysudubomb.fragments.CircleFragment;
import com.example.mysudubomb.fragments.MainpageFragment;
import com.example.mysudubomb.fragments.MineFragment;
import com.example.mysudubomb.fragments.ShareFragment;
import com.example.mysudubomb.utils.NoActionBar;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MajorActivity extends AppCompatActivity {

    private MainpageFragment mainpageFragment;
    private CircleFragment circleFragment;
    private MineFragment mineFragment;

    private FragmentManager fragmentManager;
    private final int STATE_HOME =1;
    private final int STATE_MINE =2;
    private final int STATE_SHARE =3;
    private int currentState;
    private TextView tv_na_qz;
    private TextView tv_na_sy;
    private TextView tv_na_wd;
    private Button btn_na_qz;
    private Button btn_na_wd;
    private Button btn_na_sy;
    public static MajorActivity instance = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);
        NoActionBar.noActionBar(this);
        instance=this;
        initView();
        initData(savedInstanceState);
        chageButtonColor(currentState,STATE_HOME);
    }

    private void initData(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState!=null){
            mainpageFragment = (MainpageFragment) fragmentManager.findFragmentByTag(MainpageFragment.class.getName());
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            hideFragment(transaction);
            transaction.show(mainpageFragment);
            transaction.commit();
        }else {
            switchFragment(this.STATE_HOME);
        }
    }

    private void initView() {
        tv_na_sy = (TextView) findViewById(R.id.tv_na_sy);
        tv_na_qz = (TextView) findViewById(R.id.tv_na_qz);
        tv_na_wd = (TextView) findViewById(R.id.tv_na_wd);
        btn_na_qz = (Button) findViewById(R.id.btn_na_qz);
        btn_na_wd = (Button) findViewById(R.id.btn_na_wd);
        btn_na_sy = (Button) findViewById(R.id.btn_na_sy);
    }

    public void onclick(View view) {
        switch (view.getId()){
            case  R.id.ll_na_sy:
              case R.id.btn_na_sy:
                switchFragment(this.STATE_HOME);
                break;
            case R.id.btn_na_qz:
            case  R.id.ll_na_qz:
                switchFragment(this.STATE_SHARE);
                break;
            case R.id.btn_na_wd:
            case  R.id.ll_na_wd:
                switchFragment(this.STATE_MINE);
                break;
        }
    }

    private void switchFragment(int toState) {
        if (toState == currentState)
            return;
        chageButtonColor(currentState,toState);
        this.currentState=toState;
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        Fragment fragment =null;
        switch (toState){
            case STATE_HOME:
                if (mainpageFragment == null){
                    mainpageFragment= new MainpageFragment();
                    transaction.add(R.id.ll_major_content,mainpageFragment,MainpageFragment.class.getName());
                }
                fragment= mainpageFragment;
                break;
            case STATE_SHARE:
                if (circleFragment == null){
                    circleFragment= new CircleFragment();
                    transaction.add(R.id.ll_major_content,circleFragment);
                }
                fragment= circleFragment;
                break;
            case STATE_MINE:
                if (mineFragment == null){
                    mineFragment= new MineFragment();
                    transaction.add(R.id.ll_major_content,mineFragment);
                }
                fragment= mineFragment;
                break;
        }
        hideFragment(transaction);
        transaction.show(fragment);
        transaction.commit();
    }

    private void chageButtonColor(int currentState,int toState){

        switch (currentState){
            case STATE_HOME:
                this.tv_na_sy.setTextColor(getResources().getColor(R.color.color_Muorencolor));
                this.btn_na_sy.setBackgroundResource(R.mipmap.na_shouye);
                break;
            case STATE_SHARE:
                this.tv_na_qz.setTextColor(getResources().getColor(R.color.color_Muorencolor));
                this.btn_na_qz.setBackgroundResource(R.mipmap.na_quanzi);
                break;
            case STATE_MINE:
                this.tv_na_wd.setTextColor(getResources().getColor(R.color.color_Muorencolor));
                this.btn_na_wd.setBackgroundResource(R.mipmap.na_wode);
                break;

        }
        switch (toState){
            case STATE_HOME:
                this.btn_na_sy.setBackgroundResource(R.mipmap.na_shouye_black);
                this.tv_na_sy.setTextColor(Color.BLACK);
                break;
            case STATE_SHARE:
                this.btn_na_qz.setBackgroundResource(R.mipmap.na_quanzi_black);
                this.tv_na_qz.setTextColor(Color.BLACK);
                break;
            case STATE_MINE:
                this.btn_na_wd.setBackgroundResource(R.mipmap.na_wode_black);
                this.tv_na_wd.setTextColor(Color.BLACK);
                break;



        }
    }
    private void hideFragment(FragmentTransaction transaction){
        if(mainpageFragment != null){
            transaction.hide(mainpageFragment);
        }
        if(mineFragment != null){
            transaction.hide(mineFragment);
        }
        if(circleFragment != null){
            transaction.hide(circleFragment);
        }
    }



}
