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

    public static ResultBean getRandomLocalMediaV2(Context context) {
        List<ResultBean> list = new ArrayList<>();
        list.add(new ResultBean("0%", R.drawable.a1));
        list.add(new ResultBean("10%", R.drawable.a2));
        list.add(new ResultBean("20%", R.drawable.a3));
        list.add(new ResultBean("30%", R.drawable.a4));
        list.add(new ResultBean("40%", R.drawable.a5));
        list.add(new ResultBean("50%", R.drawable.a6));
        list.add(new ResultBean("60%", R.drawable.a7));
        list.add(new ResultBean("70%", R.drawable.a8));
        list.add(new ResultBean("80%", R.drawable.a9));
        list.add(new ResultBean("90%", R.drawable.a10));
        list.add(new ResultBean("100%", R.drawable.a11));
        list.add(new ResultBean("满出！", R.drawable.a12));
        int anInt = new Random().nextInt(list.size());
        return list.get(anInt);
    }
    public static ResultBean getRandomV3(Context context) {
        ResultBean resultBean = new ResultBean("0%", R.drawable.b0);

        return resultBean;
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
