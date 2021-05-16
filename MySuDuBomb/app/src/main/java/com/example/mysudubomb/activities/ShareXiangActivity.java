package com.example.mysudubomb.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mysudubomb.R;
import com.example.mysudubomb.adapter.CommentAdapter;
import com.example.mysudubomb.bean.Circle;

import com.example.mysudubomb.bean.Comment;
import com.example.mysudubomb.bean.MyUser;
import com.example.mysudubomb.manager.ShareXiangManager;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.utils.KeyboardUtils;
import com.example.mysudubomb.utils.NoActionBar;
import com.example.mysudubomb.utils.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShareXiangActivity extends AppCompatActivity {

    private TextView tv_sx_content;
    private ImageView iv_sx_image;
    private ShareXiangManager shareXiangManager;
    private TextView tv_share_xiangxi_name;
    private CircleImageView iv_share_touxiang;
    private EditText edit_share_comment;
    private Button btn_share_comment;
    private Circle circle;
    private ProgressDialog dialog;
    private RecyclerView recycler_share;
    private CommentAdapter commentAdapter;
    private List<Comment> comments;
    private ImageView iv_share_xihuan;
    private TextView tv_share_likes;
    private TextView tv_share_time;
    private String objectId;
    private Integer likes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_xiang);
        NoActionBar.noActionBar(this);
        initView();
        initData();
        show();
    }

    private void initData() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("正在加载，请稍后...");
        dialog.setMessage("等待中");
        dialog.setCancelable(false);
        dialog.show();
        comments = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_share.setLayoutManager(linearLayoutManager);

        commentAdapter = new CommentAdapter(this);
        commentAdapter.setData(comments);
        recycler_share.setNestedScrollingEnabled(false);
        recycler_share.setAdapter(commentAdapter);

    }

    private void show() {
        Intent intent = getIntent();
        circle = (Circle) intent.getSerializableExtra("circle");
        if (circle !=null){
            objectId = circle.getObjectId();
            tv_share_xiangxi_name.setText(circle.getAuthor());
            tv_sx_content.setText(circle.getContent());
            tv_share_time.setText(circle.getCreatedAt());
            likes = circle.getLikes();
            tv_share_likes.setText(likes+"");
            getImageRatio(circle);
            if (circle.getAuthorImage()!=null){
                Glide.with(this).load(circle.getAuthorImage().getUrl()).into(iv_share_touxiang);
            }
        }
        queryComment();
        queryLikes();
        iv_share_xihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser user = BmobUser.getCurrentUser(MyUser.class);
                Circle circle = new Circle();
                circle.setObjectId(objectId);
                if (v.getTag().equals(0)){
                    addLike(circle,user);
                }else if (v.getTag().equals(1)){
                    deleteLike(circle,user);
                }
            }
        });
    }

    private void deleteLike(Circle circle, MyUser user) {
        //将当前用户添加到Post表中的likes字段值中，表明当前用户喜欢该帖子
        BmobRelation relation = new BmobRelation();
        //将当前用户添加到多对多关联中
        relation.remove(user);
        //多对多关联指向`post`的`likes`字段
        circle.setLikers(relation);
        circle.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){

                }else{
                    Toast.show(ShareXiangActivity.this,"网络错误，请稍后再试");
                }
            }

        });
        iv_share_xihuan.setTag(0);
        iv_share_xihuan.setImageResource(R.mipmap.dianzan);
        likes = likes - 1;
        tv_share_likes.setText(likes+"");
        Circle data = new Circle();
        data.increment("likes",-1);
        data.update(this.circle.getObjectId(),new UpdateListener() {
            @Override
            public void done(BmobException e) {

            }
        });
    }

    private void addLike(Circle circle, MyUser user) {
        //将当前用户添加到Post表中的likes字段值中，表明当前用户喜欢该帖子
        BmobRelation relation = new BmobRelation();
        //将当前用户添加到多对多关联中
        relation.add(user);
        //多对多关联指向`post`的`likes`字段
        circle.setLikers(relation);
        circle.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){

                }else{
                    Toast.show(ShareXiangActivity.this,"网络错误，请稍后再试");
                }
            }

        });
        iv_share_xihuan.setTag(1);
        iv_share_xihuan.setImageResource(R.mipmap.dianzan_hong);
        likes = likes + 1;
        tv_share_likes.setText(likes+"");
        Circle data = new Circle();
        data.increment("likes");
        data.update(this.circle.getObjectId(),new UpdateListener() {
            @Override
            public void done(BmobException e) {

            }
        });
    }

    private void queryLikes() {
        BmobQuery<MyUser> query  = new BmobQuery<>();
        Circle circle = new Circle();
        circle.setObjectId(objectId);
        query.addWhereRelatedTo("likers",new BmobPointer(circle));
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if (e == null) {
                    iv_share_xihuan.setTag(0);
                    iv_share_xihuan.setImageResource(R.mipmap.dianzan);
                    if (list != null){
                        for (MyUser myUser:list){
                            if (myUser.getObjectId().equals(BmobUser.getCurrentUser(MyUser.class).getObjectId())){
                                iv_share_xihuan.setTag(1);
                                iv_share_xihuan.setImageResource(R.mipmap.dianzan_hong);
                                break;
                            }
                        }
                    }
                } else {
                    Toast.show(ShareXiangActivity.this,"网络错误");
                }
            }
        });


    }

    private void getImageRatio(Circle circle){

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        ViewGroup.LayoutParams layoutParams = iv_sx_image.getLayoutParams();
        layoutParams.width = screenWidth;
        String ratio = circle.getImageRatio();
        if ("1:1".equals(ratio)){
            layoutParams.height = (int) ((screenWidth + 0f) / 1 * 1);
        }else if ("3:4".equals(ratio)){
            layoutParams.height  = (int) ((screenWidth + 0f) / 3 * 4);
        }else if ("4:3".equals(ratio)){
            layoutParams.height  = (int) ((screenWidth + 0f) / 4 * 3);
        }
        Glide.with(this).load(circle.getImage()).into(iv_sx_image);
    }

    private void initView() {

        tv_sx_content = (TextView) findViewById(R.id.tv_sx_content);
        iv_sx_image = (ImageView) findViewById(R.id.iv_sx_image);
        tv_share_xiangxi_name = (TextView) findViewById(R.id.tv_share_xiangxi_name);
        iv_share_touxiang = (CircleImageView) findViewById(R.id.iv_share_touxiang);
        edit_share_comment = (EditText) findViewById(R.id.edit_share_comment);
        btn_share_comment = (Button) findViewById(R.id.btn_share_comment);
        recycler_share = (RecyclerView) findViewById(R.id.recycler_share);
        tv_share_time = (TextView) findViewById(R.id.tv_share_time);
        tv_share_likes = (TextView) findViewById(R.id.tv_share_likes);
        iv_share_xihuan = (ImageView) findViewById(R.id.iv_share_xihuan);

    }

    public void onclick(View view) {

        switch (view.getId()){

            case R.id.btn_sx_finish:
                finish();
                break;
            case R.id.edit_share_comment:

                break;
            case R.id.btn_share_comment:
                publishComment();
                break;

        }

    }

    private void publishComment() {
        String data = edit_share_comment.getText().toString().trim();
        if (data == null || "".equals(data)){
            Toast.show(this,"内容不能为空哦");
            return;
        }
        dialog.show();
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        Circle circle = new Circle();
        circle.setObjectId(this.circle.getObjectId());
        Comment comment = new Comment();
        comment.setContent(data);
        comment.setCircle(circle);
        comment.setUser(user);
        comment.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                edit_share_comment.setText("");
                KeyboardUtils.hideKeyboard(btn_share_comment);
                if(e==null){
                    Toast.show(ShareXiangActivity.this,"发表成功");
                    queryComment();
                }else{
                    Toast.show(ShareXiangActivity.this,"发表失败");
                }
            }
        });
    }

    private  void queryComment(){
        BmobQuery<Comment> query = new BmobQuery<Comment>();
       //用此方式可以构造一个BmobPointer对象。只需要设置objectId就行
        Circle circle = new Circle();
        circle.setObjectId(this.circle.getObjectId());
        query.addWhereEqualTo("circle",new BmobPointer(circle));
        query.order("-createdAt");
       //希望同时查询该评论的发布者的信息，以及该帖子的作者的信息，这里用到上面`include`的并列对象查询和内嵌对象的查询
        query.include("user,circle.user");
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> objects,BmobException e) {
                if (e == null){
                    comments = objects;
                //    commentAdapter = new CommentAdapter(ShareXiangActivity.this);
                    commentAdapter.setData(comments);
                    commentAdapter.notifyDataSetChanged();
                 //   recycler_share.setAdapter(commentAdapter);
                }else {
                    Toast.show(ShareXiangActivity.this,"网络错误，请稍后再试");
                }
                dialog.dismiss();

            }
        });

    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示").setMessage("您确定要删除这条动态吗").setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setCancelable(false).show();
    }
    private void setListViewHeightBasedOnChildren(ListView bbs_listview) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = bbs_listview.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, bbs_listview);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = bbs_listview.getLayoutParams();
        params.height = totalHeight + (bbs_listview.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        bbs_listview.setLayoutParams(params);
    }

}
