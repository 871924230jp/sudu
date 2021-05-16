package com.example.mysudubomb.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mysudubomb.R;
import com.example.mysudubomb.activities.everyactivities.QuanmaiActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WanFragment extends Fragment {


    private Button btn_QMmake;
    private Intent intent;

    public WanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wan, container, false);

        initData();
        initView(view);
        return view;
    }

    private void initData() {
        intent = new Intent();
    }

    private void initView(View view) {


        btn_QMmake= (Button) view.findViewById(R.id.btn_QMmake);
        btn_QMmake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), QuanmaiActivity.class);
                startActivity(intent);
            }
        });

    }

}
