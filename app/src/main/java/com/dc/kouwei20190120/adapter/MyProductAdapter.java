package com.dc.kouwei20190120.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dc.kouwei20190120.R;
import com.dc.kouwei20190120.bean.CartBean;

import java.util.List;

/**
 * 子条目数据
 */
public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.MyViewHolder> {
    Context context;
    List<CartBean.DataBean.ListBean> list;

    public MyProductAdapter(Context context, List<CartBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    /**
     * 子条目赋值
     * @param myViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        CartBean.DataBean.ListBean listBean = list.get(i);
        myViewHolder.price.setText("￥"+listBean.price);
        myViewHolder.name.setText(listBean.title);
        myViewHolder.checkBox.setChecked(listBean.isProductchecked);
        String[] split = listBean.images.split("\\!");
        Glide.with(context).load(split[0]).into(myViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    /**
     * 获取资源Id
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name,price;
        CheckBox checkBox;
         public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name= itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
