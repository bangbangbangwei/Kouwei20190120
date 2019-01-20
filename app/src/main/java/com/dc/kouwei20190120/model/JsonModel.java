package com.dc.kouwei20190120.model;

import com.dc.kouwei20190120.net.OkHttpUtils;
import com.dc.kouwei20190120.cantract.Cantract;

import java.util.HashMap;

public class JsonModel implements Cantract.IJsonModel {
    @Override
    public void getJson(String url, HashMap<String, String> params, Cantract.OkhttpCallBack okhttpCallBack) {
        OkHttpUtils.getmInstance().doPost(url,params,okhttpCallBack);
    }
}
