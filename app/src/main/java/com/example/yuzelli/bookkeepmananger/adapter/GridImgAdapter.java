package com.example.yuzelli.bookkeepmananger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuzelli.bookkeepmananger.R;
import com.example.yuzelli.bookkeepmananger.bean.TypeBean;
import java.util.ArrayList;

/**
 * Created by 51644 on 2017/9/4.
 */

public class GridImgAdapter extends BaseAdapter {
    //上下文对象
    private Context context;
    private ArrayList<TypeBean> typeBeenaArr;
    private LayoutInflater inflater;
    public GridImgAdapter(Context context, ArrayList<TypeBean> typeBeenaArr) {
        this.context = context;
        this.typeBeenaArr = typeBeenaArr;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return typeBeenaArr.size();
    }

    @Override
    public Object getItem(int position) {
        return typeBeenaArr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_gridview, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.text);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(typeBeenaArr.get(position).getName());
        viewHolder.image.setImageResource(typeBeenaArr.get(position).getTypeRESID());
        return convertView;
    }

    class ViewHolder
    {
        public TextView title;
        public ImageView image;
    }
}
