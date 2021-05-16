package com.example.mysudubomb.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseLazyLoadFragment extends Fragment {

    boolean mIsPrepare = false;		//视图还没准备好
    boolean mIsVisible= false;		//不可见
    boolean mIsFirstLoad = true;	//第一次加载
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = initView(inflater, container);

        mIsPrepare=true;
        startLoad();
        return view;
    }

    private void startLoad() {

        if (!mIsPrepare || !mIsVisible||!mIsFirstLoad) {
            return;
        }
        onLazyLoad();
        //数据加载完毕,恢复标记,防止重复加载
        mIsFirstLoad = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见

        if (isVisibleToUser) {
            mIsVisible = true;
            startLoad();
        } else {
            mIsVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroyView() {
        mIsFirstLoad=true;
        mIsPrepare=false;
        mIsVisible = false;
        super.onDestroyView();
    }

    //数据加载接口，留给子类实现
    public abstract void onLazyLoad();

    //初始化视图接口，子类必须实现
    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container);

}



