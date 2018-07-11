package com.example.lvtha.bitcoin;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvtha on 10/05/2018.
 */

public class adapter_listview extends ArrayAdapter<TienAo> {
    Context context;
    int res;
    ArrayList<TienAo> listTien;
    public adapter_listview(@NonNull Context context, int resource, @NonNull ArrayList<TienAo> objects) {
        super(context, resource, objects);
        this.context=context;
        this.res=resource;
        this.listTien=objects;
    }
    class viewHolder{
        TextView tvViettat;
        TextView tvTenrieng;
        TextView tvPrice;
        TextView tvMarket;
        TextView tvVolume;
        TextView tvChangeone;
        TextView tvChangeday;
        TextView tvChangeweek;
    }
    public void checkColor(String s){
        Float value=Float.parseFloat(s);
        if (value>=0){

        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        viewHolder viewholder;
        if(convertView==null)
        {
            viewholder=new viewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_listview,parent,false);
            viewholder.tvChangeday=convertView.findViewById(R.id.tv_change_24h);
            viewholder.tvChangeone=convertView.findViewById(R.id.tv_change_1h);
            viewholder.tvChangeweek=convertView.findViewById(R.id.tv_change_7day);
            viewholder.tvMarket=convertView.findViewById(R.id.tv_market);
            viewholder.tvPrice=convertView.findViewById(R.id.tv_price);
            viewholder.tvVolume=convertView.findViewById(R.id.tv_volume);
            viewholder.tvTenrieng=convertView.findViewById(R.id.tv_tenrieng);
            viewholder.tvViettat=convertView.findViewById(R.id.tv_viettat);
            convertView.setTag(viewholder);
        }else viewholder= (viewHolder) convertView.getTag();

        TienAo tienAo=listTien.get(position);
        viewholder.tvViettat.setText(tienAo.getmViettat());
        viewholder.tvTenrieng.setText(tienAo.getmTenrieng());
        viewholder.tvPrice.setText(tienAo.getmPrice());
        viewholder.tvVolume.setText(tienAo.getmVolume());
        viewholder.tvMarket.setText(tienAo.getmMarket());
        viewholder.tvChangeone.setText(tienAo.getmChangeone());
        viewholder.tvChangeday.setText(tienAo.getmChangeday());
        viewholder.tvChangeweek.setText(tienAo.getmChangeweek());
        return  convertView;
    }
}
