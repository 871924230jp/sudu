package com.example.mysudubomb.fragments;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.mysudubomb.R;
import com.example.mysudubomb.activities.CollectActivity;
import com.example.mysudubomb.activities.GuanyuActivity;
import com.example.mysudubomb.activities.HealthActivity;
import com.example.mysudubomb.activities.LoginActivity;
import com.example.mysudubomb.activities.MotionActivity;
import com.example.mysudubomb.activities.UpdataUserActivity;
import com.example.mysudubomb.bean.MyUser;
import com.example.mysudubomb.manager.MineManager;
import com.example.mysudubomb.myview.MyDialog;
import com.example.mysudubomb.properties.AppProperties;

import java.io.FileNotFoundException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {


    private MyDialog dialog;
    private TextView tv_dakatime;
    private LinearLayout ll_yjfk;
    private LinearLayout ll_daka;
    private LinearLayout ll_guanyu;
    private LinearLayout ll_mine_collect;
    private LinearLayout ll_mine_health;
    private LinearLayout ll_mine_exe;
    private Intent intent;
    private MineManager manager;
    private TextView tv_mine_name;
    private TextView tv_mine_desc;
    private LinearLayout ll_mine_user;
    private CircleImageView iv_mine_image;
    private FrameLayout frameLayout;
    private FrameLayout fl_mine;


    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(view);
        initData();
        return view;
    }

    private void showUser() {
        String name = (String) BmobUser.getObjectByKey("name");
        String description = (String) BmobUser.getObjectByKey("description");
        tv_mine_name.setText(name);
        if (description.length()>20){
            tv_mine_desc.setText(description.substring(0,19)+"...");
        }else {
            tv_mine_desc.setText(description);
        }
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        BmobFile image = user.getHeadImage();
     //   BmobFile headImage = (BmobFile) BmobUser.getObjectByKey("headImage");
        if (image==null){
            iv_mine_image.setImageResource(R.mipmap.mine_touxiang);
        }else {
            Glide.with(getActivity()).load(image.getUrl()).into(iv_mine_image);
        }
    }

    private void initData() {
        dialog = new MyDialog(getActivity());
        intent = new Intent();
        manager = new MineManager(getActivity());
        if (BmobUser.isLogin()){
            showUser();
        }else {
            frameLayout.setVisibility(View.VISIBLE);
            fl_mine.setVisibility(View.GONE);
        }

    }

    private void initView(View view) {
        tv_dakatime = (TextView) view.findViewById(R.id.tv_dakatime);
        ll_yjfk = (LinearLayout) view.findViewById(R.id.ll_yjfk);
        ll_daka = (LinearLayout) view.findViewById(R.id.ll_daka);
        ll_guanyu = (LinearLayout) view.findViewById(R.id.ll_guanyu);
        ll_mine_collect = (LinearLayout) view.findViewById(R.id.ll_mine_collect);
        ll_mine_health = (LinearLayout) view.findViewById(R.id.ll_mine_health);
        ll_mine_exe = (LinearLayout) view.findViewById(R.id.ll_mine_exe);
        tv_mine_name = (TextView) view.findViewById(R.id.tv_mine_name);
        tv_mine_desc = (TextView) view.findViewById(R.id.tv_mine_desc);
        ll_mine_user = (LinearLayout) view.findViewById(R.id.ll_mine_user);
        iv_mine_image = (CircleImageView) view.findViewById(R.id.iv_mine_image);
        frameLayout = (FrameLayout)view.findViewById(R.id.fl_mine_login);
        fl_mine = (FrameLayout)view.findViewById(R.id.fl_mine);

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        ll_daka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getActivity().getSharedPreferences(AppProperties.SP_NAME, MODE_PRIVATE);
                String time = sp.getString(AppProperties.SP_DAKA_KEY, "您还没有打卡哦");/*第二个是默认值*/
                dialog.setMessage(time);
                dialog.show();
            }
        });
        ll_guanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), GuanyuActivity.class);
                startActivity(intent);
            }
        });
        ll_mine_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), CollectActivity.class);
                startActivity(intent);
            }
        });
        ll_mine_health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), HealthActivity.class);
                startActivity(intent);
            }
        });
        ll_mine_exe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), MotionActivity.class);
                startActivity(intent);
            }
        });
        ll_mine_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), UpdataUserActivity.class);
                startActivityForResult(intent,99);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AppProperties.RESULT_CODE_USERUPDATE)

            showUser();


    }
}
