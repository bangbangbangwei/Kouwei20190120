package com.dc.kouwei20190120.presenter;

import com.dc.kouwei20190120.cantract.Cantract;
import com.dc.kouwei20190120.model.JsonModel;

import java.util.HashMap;

public class JsonPresenter {
    Cantract.IJsonView iJsonView;
    JsonModel jsonModel;

    public JsonPresenter(Cantract.IJsonView iJsonView) {
        this.iJsonView = iJsonView;
        jsonModel = new JsonModel();
    }
    public void setJson(String url, HashMap<String,String> params){
        jsonModel.getJson(url, params, new Cantract.OkhttpCallBack() {
            @Override
            public void success(String res) {
                iJsonView.success(res);
            }

            @Override
            public void failure(String s) {
                iJsonView.failure(s);
            }
        });
    }
}
