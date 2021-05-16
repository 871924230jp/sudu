package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mysudubomb.R;
import com.example.mysudubomb.fragments.JiaFragment;
import com.example.mysudubomb.fragments.WanFragment;
import com.example.mysudubomb.fragments.WuFragment;
import com.example.mysudubomb.fragments.ZaoFragment;
import com.example.mysudubomb.utils.NoActionBar;

public class EveryActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private ZaoFragment zaoFragment;
    private WuFragment wuFragment;
    private WanFragment wanFragment;
    private JiaFragment jiaFragment;
    private  final int STATE_ZAO =1;
    private  final int STATE_WU =2;
    private  final int STATE_WAN =3;
    private  final int STATE_JIA =4;
    private int currentState;
    private Button btn_zaocan;
    private Button btn_wucan;
    private Button btn_wancan;
    private Button btn_jiacan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every);
        NoActionBar.noActionBar(this);
        initView();
        initData();
    }
    private void initData() {
        fragmentManager = getFragmentManager();
        switchFragment(STATE_ZAO);
    }

    private void initView() {
        btn_zaocan = (Button) findViewById(R.id.btn_zaocan);
        btn_wucan = (Button) findViewById(R.id.btn_wucan);
        btn_wancan = (Button) findViewById(R.id.btn_wancan);
        btn_jiacan = (Button) findViewById(R.id.btn_jiacan);
    }

    public void click(View view) {

        switch (view.getId()){
            case R.id.btn_zaocan:
                switchFragment (this.STATE_ZAO);

                break;
            case R.id.btn_wucan:
                switchFragment (this.STATE_WU);
                break;
            case R.id.btn_wancan:
                switchFragment (this.STATE_WAN);

                break;
            case R.id.btn_jiacan:
                switchFragment (this.STATE_JIA);

                break;
        }
    }

    private void switchFragment(int toState) {
        if (toState == currentState)
            return;
        changeButtonColor(currentState,toState);
        this.currentState = toState;

        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        Fragment fragment= zaoFragment;
        switch (toState){
            case STATE_ZAO:

                if (zaoFragment == null)
                    zaoFragment = new ZaoFragment();
                fragment =zaoFragment;
                break;
            case  STATE_WU:
                if (wuFragment == null)
                    wuFragment = new WuFragment();
                fragment =wuFragment;
                break;
            case  STATE_WAN:
                if (wanFragment == null)
                    wanFragment = new WanFragment();
                fragment =wanFragment;
                break;
            case STATE_JIA:
                if (jiaFragment == null)
                    jiaFragment = new JiaFragment();
                fragment =jiaFragment;
                break;
        }
        transaction.replace(R.id.ll_content_part,fragment);
        transaction.commit();
    }

    private void changeButtonColor(int currentState,int toState){//现在是哪个按钮，之前是哪个按钮
        switch (currentState){
            case STATE_ZAO:
                this.btn_zaocan.setTextColor(Color.BLACK);
                break;
            case STATE_WU:
                this.btn_wucan.setTextColor(Color.BLACK);
                break;
            case STATE_WAN:
                this.btn_wancan.setTextColor(Color.BLACK);
                break;
            case STATE_JIA:
                this.btn_jiacan.setTextColor(Color.BLACK);
                break;
        }
        switch (toState) {
            case STATE_ZAO:
                this.btn_zaocan.setTextColor(Color.DKGRAY);
                break;
            case STATE_WU:
                this.btn_wucan.setTextColor(Color.DKGRAY);
                break;
            case STATE_WAN:
                this.btn_wancan.setTextColor(Color.DKGRAY);
                break;
            case STATE_JIA:
                this.btn_jiacan.setTextColor(Color.DKGRAY);
                break;
        }

    }
}
