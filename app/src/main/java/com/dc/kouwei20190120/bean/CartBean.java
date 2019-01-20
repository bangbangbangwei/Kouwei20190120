package com.dc.kouwei20190120.bean;

import java.util.ArrayList;
import java.util.List;

public class CartBean {
   public List<DataBean> data;
   public class DataBean{
       public boolean ischecked;
       public String sellerName;
       public List<ListBean> list;
       public class ListBean{
           public boolean isProductchecked;
           public String images;
           public Double price;
           public String title;
       }
   }
}
