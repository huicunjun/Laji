package com.example.laji;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class ImageSeeActivity extends AppCompatActivity {

    public static Bitmap bitmap;
    ImageView imageView;

    public  static String title = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_image_see);



        imageView = findViewById(R.id.image);

        try {
            if (title != null) {
                setTitle(title);
            }
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {

        }
    }


    @Override
    public void finish() {
        title = null;
        super.finish();
    }
}