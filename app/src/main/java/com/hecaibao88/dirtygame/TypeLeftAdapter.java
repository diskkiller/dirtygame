/*
package com.hecaibao88.dirtygame;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


*/
/**
 * Created by Administrator on 2016/10/3.
 *//*

public class TypeLeftAdapter extends BaseAdapter {
    private Context mContext;
    private int mSelect = 0;//选中项
    private String[] titles = new String[]{"入门", "调情", "浪漫", "前戏", "性爱"};

    public TypeLeftAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_type, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(titles[position]);

        if (mSelect == position) {
            holder.tv_name.setBackgroundResource(R.mipmap.btn_sel);  //选中项背景
            holder.tv_name.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.tv_name.setBackgroundResource(R.mipmap.btn_nomal);  //其他项背景
            holder.tv_name.setTextColor(Color.parseColor("#ffffff"));
        }
        return convertView;
    }

    public void changeSelected(int positon) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        private TextView tv_name;
    }
}
*/
