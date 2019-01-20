package com.dc.kouwei20190120.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.dc.kouwei20190120.presenter.JsonPresenter;
import com.dc.kouwei20190120.adapter.MyCartAdapter;
import com.dc.kouwei20190120.R;
import com.dc.kouwei20190120.api.UserApi;
import com.dc.kouwei20190120.bean.CartBean;
import com.dc.kouwei20190120.cantract.Cantract;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class HomeFragMent extends Fragment implements Cantract.IJsonView {

    private RecyclerView rec;
    private HashMap<String, String> params;
    private JsonPresenter jsonPresenter;
    private MyCartAdapter myCartAdapter;
    private CheckBox checkbox;
    private List<CartBean.DataBean> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homefrag,container,false);
    }

    /**
     * 获取资源Id
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rec = view.findViewById(R.id.rec);
        checkbox = view.findViewById(R.id.checkbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    for (CartBean.DataBean datum : data) {
                        datum.ischecked = true;
                        for (CartBean.DataBean.ListBean listBean : datum.list) {
                            listBean.isProductchecked = true;
                        }
                    }
                }else{
                    for (CartBean.DataBean datum : data) {
                        datum.ischecked = false;
                        for (CartBean.DataBean.ListBean listBean : datum.list) {
                            listBean.isProductchecked = false;
                        }
                    }
                }
                myCartAdapter.notifyDataSetChanged();
                setToTalPrice();
            }
        });
        initData();
    }

    private void initData() {
        params = new HashMap<>();
        jsonPresenter = new JsonPresenter(this);
        params.put("uid","71");
        jsonPresenter.setJson(UserApi.USER_CART,params);
    }

    /**
     * json获取成功
     * @param res
     */
    @Override
    public void success(String res) {
        CartBean cartBean = new Gson().fromJson(res, CartBean.class);
        data = cartBean.data;
        myCartAdapter = new MyCartAdapter(getActivity(), data);
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        rec.setAdapter(myCartAdapter);
    }

    @Override
    public void failure(String s) {

    }

    /**
     * 价格
     */
    public void setToTalPrice(){
        int totalprice = 0;
        for (CartBean.DataBean datum : data) {
            for (CartBean.DataBean.ListBean listBean : datum.list) {
                if (listBean.isProductchecked){
                    totalprice += listBean.price;
                }
            }
        }
        checkbox.setText(totalprice+"");
    }
}
