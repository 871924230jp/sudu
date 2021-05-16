package com.example.mysudubomb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.GuideInfo;
import com.example.mysudubomb.myview.MyImageView;

import java.util.List;

public class GuideAdapter extends BaseAdapter {

    private List<GuideInfo> guideInfos;
    private Context context;

    private int resLayout;


    public GuideAdapter(Context context,int resLayout,List guideInfos){

        this.guideInfos=guideInfos;
        this.context=context;

        this.resLayout = resLayout;


    }
    @Override
    public int getCount() {
        return guideInfos.size();
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
        if (convertView == null){
            convertView=View.inflate(this.context, resLayout,null);
            holder = new ViewHolder();
            holder.tv_guide_name= (TextView) convertView.findViewById(R.id.tv_guide_name);
            holder.tv_guide_text = (TextView) convertView.findViewById(R.id.tv_guide_text);
            holder.iv_guide_image = (MyImageView) convertView.findViewById(R.id.iv_guide_image);
            holder.iv_guide_image.setTag(resLayout,guideInfos.get(position).getFoodImage());
            convertView.setTag(holder);
        }else {
            holder =(ViewHolder) convertView.getTag();
        }
        holder.tv_guide_name.setText(guideInfos.get(position).getFood());
        holder.tv_guide_text.setText(guideInfos.get(position).getCount());
        Glide.with(context).load(guideInfos.get(position).getFoodImage()).into(holder.iv_guide_image);
        return convertView;
    }
    private static class ViewHolder {
        TextView tv_guide_name;
        TextView tv_guide_text;
        MyImageView iv_guide_image;
    }
}

