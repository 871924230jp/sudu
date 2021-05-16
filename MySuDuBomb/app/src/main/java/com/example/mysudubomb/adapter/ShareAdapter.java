package com.example.mysudubomb.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.Circle;

import com.example.mysudubomb.myview.MyImageView;

import java.util.List;

public class ShareAdapter extends BaseAdapter {
    private Context context;
    private List<Circle> circleList;
    public ShareAdapter(Context context,List<Circle> circleList){
        this.context=context;
        this.circleList=circleList;
    }
    @Override
    public int getCount() {
        return circleList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.share_item,null);
            holder = new ViewHolder();
            holder.imageView=(MyImageView) convertView.findViewById(R.id.iv_share_it);
            holder.shareContent=(TextView) convertView.findViewById(R.id.tv_share_it);
            holder.shareName=(TextView) convertView.findViewById(R.id.tv_share_name);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }



        holder.imageView.setImageUrl(circleList.get(position).getImage());
        holder.shareContent.setText(circleList.get(position).getContent());
        holder.shareName.setText(circleList.get(position).getUser().getName());
        return convertView;
    }
    private  static class  ViewHolder{
        MyImageView imageView;
        TextView shareContent;
        TextView shareName;



    }
}
