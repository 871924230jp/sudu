package com.example.mysudubomb.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mysudubomb.R;
import com.example.mysudubomb.activities.everyactivities.ShalaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WuFragment extends Fragment {
    private Button btn_DJmake;
    private Button btn_JXmake;
    private Button btn_YumiMake;
    private Intent intent;

    public WuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wu, container, false);

        initData();
        initView(view);
        return view;
    }
    private void initData() {
        intent = new Intent();
    }

    private void initView(View view) {


        btn_JXmake = (Button) view.findViewById(R.id.btn_JXmake);
        btn_JXmake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), ShalaActivity.class);
                startActivity(intent);
            }
        });
    }

}
