package com.example.mysudubomb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.Circle;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Circle> circleList;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_MORE = 1;
        private OnLoadMoreListener onLoadMoreListener;

    public AdminAdapter(Context context){
        this.context=context;
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
    public OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(Circle circle);
    }
    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_item, parent, false);
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
        if (circleList==null || circleList.size() < 1){
            return;
        }

        if (getItemViewType(position)==TYPE_NORMAL && holder instanceof ViewHolder){

            ViewHolder viewHolder = (ViewHolder) holder;
            LoadData(viewHolder,position);
        }else if (getItemViewType(position)==TYPE_MORE && holder instanceof LoadMoreHolder){

            LoadMoreHolder loadMoreHolder = (LoadMoreHolder) holder;
            loadMoreHolder.upData(LoadMoreHolder.LOAD_BEGIN);
        }
    }

    private void LoadData(ViewHolder viewHolder, int position) {

        Circle circle = circleList.get(position);
        viewHolder.tv_admin_name.setText(circle.getAuthor());
        viewHolder.tv_admin_time.setText(circle.getCreatedAt());
        Glide.with(context).load(circle.getImage()).into(viewHolder.iv_admin);
        if (circle.getAuthorImage() != null){
            Glide.with(context).load(circle.getAuthorImage().getUrl()).into(viewHolder.civ_admin);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(circle);
            }
        });
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


          ImageView iv_admin;
          TextView tv_admin_time;
          TextView tv_admin_name;
          CircleImageView civ_admin;

        public ViewHolder(View view) {
            super(view);
            civ_admin = (CircleImageView) view.findViewById(R.id.civ_admin);
            tv_admin_name = (TextView) view.findViewById(R.id.tv_admin_name);
            tv_admin_time = (TextView) view.findViewById(R.id.tv_admin_time);
            iv_admin = (ImageView) view.findViewById(R.id.iv_admin);


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
}
