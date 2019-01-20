package com.dc.kouwei20190120.cantract;

import java.util.HashMap;

/**
 * 契约类
 */
public interface Cantract {

    public interface OkhttpCallBack{
        void success(String res);
        void failure(String s);
    }
    public interface IJsonModel{
        void getJson(String url, HashMap<String,String> params,OkhttpCallBack okhttpCallBack);
    }
    public  interface IJsonView{
        void success(String res);
        void failure(String s);
    }
}
