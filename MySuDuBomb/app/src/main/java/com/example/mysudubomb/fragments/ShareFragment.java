package com.example.mysudubomb.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;


import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mysudubomb.R;
import com.example.mysudubomb.activities.CropActivity;
import com.example.mysudubomb.activities.LoginActivity;
import com.example.mysudubomb.activities.PublishActivity;
import com.example.mysudubomb.activities.ShareXiangActivity;
import com.example.mysudubomb.adapter.CircleAdapter;
import com.example.mysudubomb.adapter.ShareAdapter;
import com.example.mysudubomb.bean.Circle;

import com.example.mysudubomb.manager.ShareManager;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.utils.GetRealPath;
import com.longsh.optionframelibrary.OptionBottomDialog;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends BaseLazyLoadFragment implements SwipeRefreshLayout.OnRefreshListener{

    private List<String> stringList;
    private ShareManager shareManager;


    private Button btn_share;
    private Uri cricleUri;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private CircleAdapter circleAdapter;
    private List<Circle> circleArrayList;


    public ShareFragment() {
        // Required empty public constructor
    }






    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onLazyLoad() {
        initData();
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onRefresh() {
        showListView();
    }

    private void initView(View view) {
        /*tv_share_it = (TextView) view.findViewById(R.id.tv_share_it);
        iv_share_it = (ImageView)view. findViewById(R.id.iv_share_it);
        lv_share = (ListView) view.findViewById(R.id.lv_share);*/

        btn_share = (Button) view.findViewById(R.id.btn_share);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);


        recyclerView = (RecyclerView) view.findViewById(R.id.circle_recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);





         btn_share.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final OptionBottomDialog optionBottomDialog = new OptionBottomDialog(getActivity(), stringList);
                 optionBottomDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                         switch (position){
                             case 0:
                              getXiangJi();
                                 break;
                             case 1:
                                 getXiangCe();
                                 break;
                             default:
                                 break;
                         }
                         optionBottomDialog.dismiss();
                     }
                 });
             }
         });
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initData() {

        shareManager = new ShareManager(getActivity());
        stringList = new ArrayList<String>();
        stringList.add(AppProperties.STRING_XIANGJI_NAME);
        stringList.add(AppProperties.STRING_XIANGCE_NAME);
        circleArrayList = new ArrayList<>();
        circleAdapter = new CircleAdapter(getActivity());
        circleAdapter.setData(circleArrayList);
        recyclerView.setAdapter(circleAdapter);

        circleAdapter.setOnLoadMoreListener(new CircleAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(CircleAdapter.LoadMoreHolder loadMoreHolder) {
                BmobQuery<Circle> query = new BmobQuery<>();
                query.setLimit(10);
                int size = circleArrayList.size();
                query.setSkip(size);
                query.order("-likes");
                query.findObjects(new FindListener<Circle>() {
                    @Override
                    public void done(List<Circle> list, BmobException e) {
                        if (list==null || list.size()<1){
                            loadMoreHolder.upData(CircleAdapter.LoadMoreHolder.LOAD_NO_MORE);
                            return;
                        }
                        loadMoreHolder.upData(CircleAdapter.LoadMoreHolder.LOAD_OVER);
                        if (e==null){
                            circleArrayList.addAll(list);
                           // circleAdapter.setData(circleArrayList);
                           // circleAdapter.notifyItemChanged(10);
                           // circleAdapter.notifyDataSetChanged();
                            circleAdapter.setData(circleArrayList);
                            circleAdapter.notifyItemChanged(size,circleArrayList.size());
                        }else {
                            Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

        });




        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        handlerDownPullUpdata();
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                onRefresh();
            }
        });


        circleAdapter.setItemClickListener(new CircleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Circle circle) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),ShareXiangActivity.class);
                intent.putExtra("circle",circle);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== AppProperties.REQUEST_CODE_XIANGCE &&resultCode==RESULT_OK){
            Intent intent = new Intent(getActivity(), CropActivity.class);
            intent.putExtra("imageUri",data.getData().toString());
            startActivity(intent);
        } else if (requestCode== AppProperties.REQUEST_CODE_XIANGJI&&resultCode==RESULT_OK){
            Intent intent = new Intent(getActivity(), CropActivity.class);
            intent.putExtra("imageUri",shareManager.getUriFromFile().toString());
            startActivity(intent);
        }else if (resultCode==AppProperties.RESULT_CODE_UPDATE){
        //    showListView();
        }

    }
    private void showListView() {
        BmobQuery<Circle> query = new BmobQuery<>();
        query.setLimit(10);
        query.order("-likes");
        query.findObjects(new FindListener<Circle>() {
            @Override
            public void done(List<Circle> list, BmobException e) {
                refreshLayout.setRefreshing(false);
                if (e==null){
                    circleArrayList = list;
                    circleAdapter.setData(circleArrayList);
                    circleAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void handlerDownPullUpdata(){
        refreshLayout.setEnabled(true);
        refreshLayout.setOnRefreshListener(this);

    }

    private void getXiangJi() {
        Intent intentxj = new Intent();
        intentxj.putExtra(MediaStore.EXTRA_OUTPUT,shareManager.getUriFromFile());
        intentxj.setAction("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intentxj, AppProperties.REQUEST_CODE_XIANGJI);
    }
    private void getXiangCe() {
        Intent intentxc = new Intent();
        // 设置文件类型
        intentxc.setType("image/*");
        intentxc.setAction(Intent.ACTION_PICK);
        startActivityForResult(intentxc, AppProperties.REQUEST_CODE_XIANGCE);

    }
    private void cut(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX , outputY : 裁剪图片宽高
       // intent.putExtra("outputX", 360);
       // intent.putExtra("outputY", 360);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("return-data", false);   // 设置是否返回数据
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + "circleImage.jpeg");
        cricleUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cricleUri);
        startActivityForResult(intent, AppProperties.REQUEST_CODE_CUT);

    }





}
