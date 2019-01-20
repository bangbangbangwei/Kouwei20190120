package com.dc.kouwei20190120.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.dc.kouwei20190120.R;
import com.dc.kouwei20190120.bean.CartBean;

import java.util.List;

/**
 * 商家条目
 */
public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {
    Context context;
    List<CartBean.DataBean> data;

    public MyCartAdapter(Context context, List<CartBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    /**
     * 赋值
     * @param myViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.checkbox.setText(data.get(i).sellerName);
        myViewHolder.checkbox.setChecked(data.get(i).ischecked);
        myViewHolder.rec.setLayoutManager(new LinearLayoutManager(context));
        MyProductAdapter myProductAdapter = new MyProductAdapter(context, data.get(i).list);
        myViewHolder.rec.setAdapter(myProductAdapter);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    /**
     * 获取资源Id
     */
    class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkbox;
        RecyclerView rec;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);
            rec = itemView.findViewById(R.id.rec);
        }
    }
}
