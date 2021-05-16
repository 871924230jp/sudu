package com.example.mysudubomb.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysudubomb.R;
import com.example.mysudubomb.activities.ShareXiangActivity;
import com.example.mysudubomb.adapter.AdminAdapter;
import com.example.mysudubomb.adapter.CircleAdapter;
import com.example.mysudubomb.bean.Circle;
import com.example.mysudubomb.bean.MyUser;
import com.example.mysudubomb.utils.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFragment extends BaseLazyLoadFragment implements SwipeRefreshLayout.OnRefreshListener{


    private RecyclerView recycler_admin;
    private SwipeRefreshLayout srl_admin;
    private AdminAdapter adminAdapter;
    private List<Circle> arrayList;

    public AdminFragment() {
        // Required empty public constructor
    }





    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        recycler_admin = (RecyclerView) view.findViewById(R.id.recycler_admin);
        srl_admin = (SwipeRefreshLayout) view.findViewById(R.id.srl_admin);
    }

    @Override
    public void onLazyLoad() {
        arrayList = new ArrayList<>();
        adminAdapter = new AdminAdapter(getActivity());
        adminAdapter.setData(arrayList);
        recycler_admin.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_admin.setAdapter(adminAdapter);
        adminAdapter.setItemClickListener(new AdminAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Circle circle) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ShareXiangActivity.class);
                intent.putExtra("circle",circle);
                startActivity(intent);
            }
        });
        adminAdapter.setOnLoadMoreListener(new AdminAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(AdminAdapter.LoadMoreHolder loadMoreHolder) {
                BmobQuery<Circle> query = new BmobQuery<>();
                query.addWhereEqualTo("user", BmobUser.getCurrentUser(MyUser.class));
                query.order("-updatedAt");
                //包含作者信息
                query.setLimit(5);
                int size = arrayList.size();
                query.setSkip(size);
                query.include("user");
                query.findObjects(new FindListener<Circle>() {
                    @Override
                    public void done(List<Circle> list, BmobException e) {
                        if (list==null || list.size()<1){
                            loadMoreHolder.upData(AdminAdapter.LoadMoreHolder.LOAD_NO_MORE);
                            return;
                        }
                        loadMoreHolder.upData(AdminAdapter.LoadMoreHolder.LOAD_OVER);
                        if (e == null) {
                            arrayList.addAll(list);
                            adminAdapter.setData(arrayList);
                            adminAdapter.notifyItemChanged(size,arrayList.size());
                        } else {
                            Toast.show(getActivity(),"网络异常，请稍后再试");
                        }
                    }

                });
            }
        });
        srl_admin.setEnabled(true);
        srl_admin.setOnRefreshListener(this);
        srl_admin.post(new Runnable() {
            @Override
            public void run() {
                srl_admin.setRefreshing(true);
                onRefresh();
            }
        });


    }

    private void queryMyAdmin() {
        BmobQuery<Circle> query = new BmobQuery<>();
        query.addWhereEqualTo("user", BmobUser.getCurrentUser(MyUser.class));
        query.order("-updatedAt");
        //包含作者信息
        query.setLimit(5);
        query.include("user");
        query.findObjects(new FindListener<Circle>() {
            @Override
            public void done(List<Circle> list, BmobException e) {
                srl_admin.setRefreshing(false);
                if (e == null) {
                    arrayList = list;
                    adminAdapter.setData(arrayList);
                    adminAdapter.notifyDataSetChanged();
                } else {
                    Toast.show(getActivity(),"网络异常，请稍后再试");
                }
            }

        });


    }

    @Override
    public void onRefresh() {
        if (BmobUser.isLogin()) {
            queryMyAdmin();
        }
    }
}
