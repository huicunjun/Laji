package com.example.laji.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.laji.R;
import com.example.laji.bean.ResultBean;
import com.google.gson.Gson;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Config {

    //    public static int TIME = 30 * 60;
    public static int TIME = 10;

    public static void setModeSP(Context context, String path) {
        SharedPreferences xx = getSharedPreferences(context);
        xx.edit().putString("mode", path).apply();
    }

    public static String getModeSP(Context context) {
        SharedPreferences xx = getSharedPreferences(context);
        return xx.getString("mode", "");
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("xx", Context.MODE_PRIVATE);
    }

    public static void setLocalMedia(Context context, LocalMedia localMedia) {
        String s = new Gson().toJson(localMedia);
        getSharedPreferences(context).edit().putString("setLocalMedia", s).apply();
    }

    public static LocalMedia getLocalMedia(Context context) {
        String setLocalMedia = getSharedPreferences(context).getString("setLocalMedia", "");
        return new Gson().fromJson(setLocalMedia, LocalMedia.class);
    }

    public static ResultBean getLocalMediaV2(Context context) {
        List<ResultBean> list = new ArrayList<>();
        list.add(new ResultBean("0%", R.drawable.a1));
        list.add(new ResultBean("10%", R.drawable.a2));
        int anInt = new Random().nextInt(list.size());
        return list.get(anInt);
    }

    public static void randomToast(Context context) {
        String[] strings = {
                "垃圾存量20%",
                "垃圾存量40%",
                "垃圾存量80%",
                "垃圾存量100%",
        };
        String sfsaff = "";
        try {
            sfsaff = strings[new Random().nextInt(strings.length)];
        } catch (Exception E) {

        }
        Toast.makeText(context, "处理完成" + sfsaff, Toast.LENGTH_SHORT).show();
    }
}
