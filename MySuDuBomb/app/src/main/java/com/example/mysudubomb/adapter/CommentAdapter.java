package com.example.mysudubomb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {


    private List<Comment> commentList;
    private Context context;
    public CommentAdapter(Context context) {
        this.context = context;

    }
    public void setData(List<Comment> commentList){
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.tv_comment_name.setText(comment.getUser().getName());
        holder.tv_comment_data.setText(comment.getContent());
        holder.tv_comment_time.setText(comment.getCreatedAt());
        if (comment.getUser().getHeadImage() ==null){
            holder.civ_comment.setImageResource(R.mipmap.mine_touxiang);
        }else {
            Glide.with(context).load(comment.getUser().getHeadImage().getUrl()).into(holder.civ_comment);

        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

         TextView tv_comment_data;
         TextView tv_comment_time;
         TextView tv_comment_name;
        CircleImageView civ_comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_comment_data = (TextView) itemView.findViewById(R.id.tv_comment_data);
            tv_comment_time = (TextView) itemView.findViewById(R.id.tv_comment_time);
            tv_comment_name = (TextView) itemView.findViewById(R.id.tv_comment_name);
            civ_comment = (CircleImageView) itemView.findViewById(R.id.civ_comment);

        }
    }
}
