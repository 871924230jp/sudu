package com.example.mysudubomb.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.Circle;
import com.example.mysudubomb.bean.MyUser;
import com.example.mysudubomb.utils.NetUtils;
import com.example.mysudubomb.utils.ScreenUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.mysudubomb.R.drawable.bg_button_normal;
import static com.example.mysudubomb.R.drawable.bg_button_pressed;
import static com.example.mysudubomb.R.drawable.design_bottom_navigation_item_background;

public class CircleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Circle> circleList;
    private final float data;
    private LinearLayout.LayoutParams layoutParams;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_MORE = 1;
    private  OnLoadMoreListener onLoadMoreListener;

    public CircleAdapter(Context context){
        this.context=context;
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        data = (screenWidth - 8) / 2;
    }
    public void setData(List<Circle> circleList) {
        this.circleList=circleList;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener){
        this.onLoadMoreListener=listener;

    }
    public interface OnLoadMoreListener{
        void onLoadMore(LoadMoreHolder loadMoreHolder);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_NORMAL){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_process, parent, false);
             LoadMoreHolder moreHolderholder = new LoadMoreHolder(view);
            return moreHolderholder;
        }
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (circleList==null || circleList.size() < 1)
            return;
        if (getItemViewType(position)==TYPE_NORMAL && holder instanceof ViewHolder){
            ViewHolder viewHolder = (ViewHolder) holder;
            LoadData(viewHolder,position);
        }else if (getItemViewType(position)==TYPE_MORE && holder instanceof LoadMoreHolder){
            LoadMoreHolder loadMoreHolder = (LoadMoreHolder) holder;
            loadMoreHolder.upData(LoadMoreHolder.LOAD_BEGIN);
        }
    }

    private void LoadData(ViewHolder holder,int position) {
        Circle circle = circleList.get(position);
        holder.fruitName.setText(circle.getContent());
        holder.userName.setText(circle.getAuthor());
        holder.likes.setText(circle.getLikes()+"");
        holder.fruitImage.setTag(R.id.circle_image_item, position);
        holder.userHeadImage.setTag(R.id.civ_circle_head,position);
        holder.circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(circle);

            }
        });


        layoutParams = (LinearLayout.LayoutParams) holder.fruitImage.getLayoutParams();
        layoutParams.width=(int)data;
        compute(circle);
        holder.fruitImage.setLayoutParams(layoutParams);

        if (circle.getAuthorImage() == null){
            initHeadImage(holder,position,R.mipmap.mine_touxiang);
        }else {
            initHeadImage(holder,position,circle.getAuthorImage().getUrl());
        }
        Glide.with(context).load(circle.getImage()).override(layoutParams.width, layoutParams.height)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override//当图片加载完成的时候就会回调
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        if (position != (Integer) holder.fruitImage.getTag(R.id.circle_image_item)){
                            return;
                        }
                        if(resource != null){
                            holder.fruitImage.setAdjustViewBounds(true);
                            holder.fruitImage.setImageDrawable(resource);
                        }
                        //    holder.fruitImage.setImageDrawable(resource);
                    }
                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        /*holder.fruitImage.setImageResource(bg_button_normal);*/
                        holder.fruitImage.setImageDrawable(placeholder);
                        super.onLoadStarted(placeholder);
                    }
                });

    }

    private void initHeadImage(ViewHolder holder, int position,Object path) {
        Glide.with(context).load(path).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                if (position != (Integer) holder.userHeadImage.getTag(R.id.civ_circle_head)){
                    return;
                }
                if(resource != null){
                    holder.userHeadImage.setImageDrawable(resource);
                }
            }
        });
    }


    private void compute(Circle circle){
        String ratio = circle.getImageRatio();
        if ("1:1".equals(ratio)){
            layoutParams.height = (int) ((data + 0f) / 1 * 1);
        }else if ("3:4".equals(ratio)){
            layoutParams.height = (int) ((data + 0f) / 3 * 4);
        }else if ("4:3".equals(ratio)){
            layoutParams.height = (int) ((data + 0f) / 4 * 3);
        }else {
            layoutParams.height = (int) ((data + 0f) / 1 * 1);
        }
    }

    @Override
    public int getItemCount() {
        return circleList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == getItemCount()-1){
            return TYPE_MORE;
        }else {
            return TYPE_NORMAL;
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;
        TextView userName;
        TextView likes;
        CircleImageView userHeadImage;
        LinearLayout circle;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.circle_image_item);
            fruitName = (TextView) view.findViewById(R.id.circle_name_item);
            userName = (TextView) view.findViewById(R.id.tv_circle_name);
            userHeadImage = (CircleImageView) view.findViewById(R.id.civ_circle_head);
            circle = (LinearLayout) view.findViewById(R.id.ll_circle);
            likes = (TextView) view.findViewById(R.id.tv_circle_likes);

        }
    }

   public  class LoadMoreHolder extends RecyclerView.ViewHolder {

        View moreView;
       ProgressBar progressBar;
       TextView moreLoad;
       public LoadMoreHolder(@NonNull View itemView) {
           super(itemView);
           moreView = itemView;
           progressBar = (ProgressBar) itemView.findViewById(R.id.progress_circle);
           moreLoad = (TextView) itemView.findViewById(R.id.tv_more_load);
       }

       public static final int LOAD_BEGIN = 0;
       public static final int LOAD_OVER = 1;
       public static final int LOAD_NO_MORE = 2;

       public void upData(int state){
           progressBar.setVisibility(View.GONE);
           moreLoad.setVisibility(View.GONE);
           if (state==LOAD_BEGIN){
               progressBar.setVisibility(View.VISIBLE);
               LoadMoreData(this);
           }else if (state == LOAD_OVER){
               progressBar.setVisibility(View.GONE);
           }else if (state ==LOAD_NO_MORE){
               progressBar.setVisibility(View.GONE);
               moreLoad.setVisibility(View.VISIBLE);
           }
       }

       private void LoadMoreData(LoadMoreHolder loadMoreHolder) {
           if (onLoadMoreListener != null){
               onLoadMoreListener.onLoadMore(loadMoreHolder);
           }

       }
   }
   public OnItemClickListener onItemClickListener;
   public OnLongItemClickListener onLongItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(Circle circle);
    }
    public interface OnLongItemClickListener{
        void onLongItemClick(Circle circle);
    }
    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public void setLongItemClickListener(OnLongItemClickListener onLongItemClickListener){
        this.onLongItemClickListener = onLongItemClickListener;
    }



}
