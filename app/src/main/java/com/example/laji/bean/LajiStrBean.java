package com.example.laji.bean;

import java.util.List;

public class LajiStrBean {

    public List<String> kw_list;
    public  List<WarpObj> kw_arr;


    public static class WarpObj{
        public    String TargetId;
        public    String TypeKey;
        public    String CssName;
        public    String Name;
        public    String Note;
        public    String QueryCount;
    }
}
