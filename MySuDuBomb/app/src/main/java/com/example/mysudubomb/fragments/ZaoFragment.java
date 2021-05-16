package com.example.mysudubomb.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mysudubomb.R;
import com.example.mysudubomb.activities.everyactivities.BingActivity;
import com.example.mysudubomb.activities.everyactivities.DoujiangActivity;
import com.example.mysudubomb.activities.everyactivities.YmMakeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZaoFragment extends Fragment {

    private Button btn_DJmake;
    private Button btn_BingMake;
    private Button btn_YumiMake;
    private Intent intent;
    public ZaoFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zao, container, false);

        initData();
        initView(view);
        return view;
    }

    private void initData() {
        intent = new Intent();
    }

    private void initView(View view) {


        btn_DJmake= (Button) view.findViewById(R.id.btn_DJmake);
        btn_DJmake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), DoujiangActivity.class);
                startActivity(intent);
            }
        });
        btn_BingMake = (Button) view.findViewById(R.id.btn_BingMake);
        btn_BingMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), BingActivity.class);
                startActivity(intent);
            }
        });
        btn_YumiMake = (Button) view.findViewById(R.id.btn_YumiMake);
        btn_YumiMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), YmMakeActivity.class);
                startActivity(intent);
            }
        });

    }

}
