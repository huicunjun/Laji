package com.example.laji.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.laji.R;

public class ModeSettingActivity extends AppCompatActivity {


    public static Bitmap bitmap;
    ImageView imageView;

    public static String title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_mode_setting);


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

    public void edMode(View view) {
        Toast.makeText(this, "修改模型", Toast.LENGTH_SHORT).show();
    }
}