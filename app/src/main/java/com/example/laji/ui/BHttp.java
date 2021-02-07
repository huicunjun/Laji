package com.example.laji.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class BHttp {
    public static OkHttpClient okHttpClient = new OkHttpClient();

    public static Bitmap getImg(String url) {
        try {
            byte[] bytes = okHttpClient.newCall(new Request.Builder().url(url).build()).execute().body().bytes();
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Bitmap getMode() {
        return getImg(modeUrl);
    }


    public static String modeUrl = "https://kou.gzzmedu.com/api/v1/image/01490d11aef643e38c2374b98371084d.jpg";

    public static void setModeImage(ImageView viewById) {
        Glide.with(viewById.getContext()).load(modeUrl).into(viewById);
    }
}
