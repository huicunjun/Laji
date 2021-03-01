package com.example.laji.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.laji.ImageSeeActivity;
import com.example.laji.LoginActivity;
import com.example.laji.R;
import com.example.laji.ui.BHttp;
import com.example.laji.ui.Config;
import com.google.android.material.textfield.TextInputEditText;

//laji 存量监控
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    static boolean ttt = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!ttt) {
            ttt = true;
            LoginActivity.str = "垃圾存量监控平台";
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
//            return;
        }


        TextInputEditText ed = getView().findViewById(R.id.ed);
        ed.setText(Config.TIME + "");
        View viewById = getView().findViewById(R.id.ok);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ed.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(getContext(), "请输入监听间隔时间！", Toast.LENGTH_SHORT).show();
                    return;
                }
                int a = Integer.parseInt(s);
                if (a < 3) {
                    Toast.makeText(getActivity(), "最少3秒", Toast.LENGTH_SHORT).show();
                    return;
                }
                Config.TIME = a;
                Toast.makeText(getContext(), "开始每隔" + Config.TIME + "秒监听垃圾存量", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), CameraActivity.class));
            }
        });


        getView().findViewById(R.id.mx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
//                            Bitmap mode = BHttp.getMode();
//                            ModeSettingActivity.bitmap = mode;
                            ModeSettingActivity.title = "查看图片";
                            startActivity(new Intent(getActivity(), ModeSettingActivity.class));
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}