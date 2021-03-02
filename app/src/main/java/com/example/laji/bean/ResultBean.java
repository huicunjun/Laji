package com.example.laji.bean;

import com.example.laji.R;

import java.util.ArrayList;
import java.util.List;

public class ResultBean {
    public String str;
    public int ids;
    public String ctime;

    public static List<Integer> modeList = new ArrayList<>();

    static {
        modeList.add(R.drawable.a1);
        modeList.add(R.drawable.a2);
        modeList.add(R.drawable.a3);
        modeList.add(R.drawable.a4);
        modeList.add(R.drawable.a5);
        modeList.add(R.drawable.a6);
        modeList.add(R.drawable.a7);
        modeList.add(R.drawable.a8);
        modeList.add(R.drawable.a9);
        modeList.add(R.drawable.a10);
        modeList.add(R.drawable.a11);
        modeList.add(R.drawable.a12);
    }
    public ResultBean(String str, int ids) {
        this.str = str;
        this.ids = ids;
    }
}
