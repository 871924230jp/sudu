package com.example.mysudubomb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mysudubomb.R;
import com.example.mysudubomb.bean.FoodInfo;

import java.util.List;


public class FoodAdapter extends BaseAdapter {
    private Context context;
    private List<FoodInfo> foodlist;
    private int resLayout;

    public  FoodAdapter(Context context,List<FoodInfo>foodlist,int resLayout){

        this.context=context;
        this.foodlist=foodlist;
        this.resLayout=resLayout;

    }
    @Override
    public int getCount() {
        return foodlist.size();
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
        if (convertView== null){
            convertView=View.inflate(context,resLayout,null);
            holder = new ViewHolder();
            holder.tv_it_shicai=convertView.findViewById(R.id.tv_it_shicai);
            holder.tv_it_kcal=convertView.findViewById(R.id.tv_it_kcal);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        holder.tv_it_shicai.setText(foodlist.get(position).getShicai());
        holder.tv_it_kcal.setText(foodlist.get(position).getKcal());

        return convertView;
    }
    private static class  ViewHolder{
        TextView tv_it_shicai;
        TextView tv_it_kcal;


    }
    }

