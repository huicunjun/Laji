package com.example.laji.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.laji.R;
import com.example.laji.ui.Config;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.engine.ImageEngine;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnImageCompleteCallback;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;

import java.io.File;
import java.util.List;

public class ModeSettingActivity extends AppCompatActivity {


    public static Bitmap bitmap;
    ImageView imageView;

    public static String title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_mode_setting);


        imageView = findViewById(R.id.ivssssssssss);

        try {
            if (title != null) {
                setTitle(title);
            }
//            imageView.setImageBitmap(bitmap);
            LocalMedia localMedia = Config.getLocalMedia(getApplicationContext());
            Glide.with(ModeSettingActivity.this).load(localMedia.getPath()).into(imageView);
        } catch (Exception e) {

        }
    }


    @Override
    public void finish() {
        title = null;
        super.finish();
    }

    public void edMode(View view) {
        Toast.makeText(this, "修改模型", Toast.LENGTH_SHORT).show();
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofAll())
                .maxSelectNum(1)
                .imageEngine(gImageEngine())
//                .loadImageEngine(GlideEngine.createGlideEngine())
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        // onResult Callback
                        try {
                            LocalMedia localMedia = result.get(0);
                            File file = new File(localMedia.getPath());

                            String realPath = localMedia.getRealPath();
                            String path = result.get(0).getPath();
                            Config.setModeSP(getApplicationContext(), path);
                            Config.setLocalMedia(getApplicationContext(), localMedia);
                            Glide.with(ModeSettingActivity.this).load(path).into((ImageView) findViewById(R.id.ivssssssssss));
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onCancel() {
                        // onCancel Callback
                    }
                });

    }

    private ImageEngine gImageEngine() {
        return new ImageEngine() {
            @Override
            public void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
                Glide.with(imageView).load(url).into(imageView);
            }

            @Override
            public void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView, SubsamplingScaleImageView longImageView, OnImageCompleteCallback callback) {
                Glide.with(imageView).load(url).into(imageView);
            }

            @Override
            public void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView, SubsamplingScaleImageView longImageView) {
                Glide.with(imageView).load(url).into(imageView);
            }

            @Override
            public void loadFolderImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
                Glide.with(imageView).load(url).into(imageView);
            }

            @Override
            public void loadAsGifImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
                Glide.with(imageView).load(url).into(imageView);
            }

            @Override
            public void loadGridImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
                Glide.with(imageView).load(url).into(imageView);
            }
        };
    }


}