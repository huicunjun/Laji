package com.example.laji.ui.gallery;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.laji.R;
import com.example.laji.bean.LajiStrBean;
import com.example.laji.ui.BHttp;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

//垃圾分类识别
public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);


        return root;
    }

    TextInputEditText editText;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editText = getView().findViewById(R.id.ed);
        getView().findViewById(R.id.oklll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                if (s.equals("")) {
                    Toast.makeText(getActivity(), "请输入！", Toast.LENGTH_SHORT).show();
                    return;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

//                    JsonObject jsonObject = new JsonObject();
//                    JSONObject jsonObject1 = new JSONObject(s1);
//                    JSONArray kw_list = jsonObject1.getJSONArray("kw_list");

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog = new ProgressDialog(getActivity());
                                    progressDialog.setTitle("查询中...");
                                    progressDialog.show();
                                }
                            });

                            String s1 = BHttp.checkLaji(s);
                            LajiStrBean lajiStrBean = new Gson().fromJson(s1, LajiStrBean.class);
                            List<String> kw_list = lajiStrBean.kw_list;
                            List<LajiStrBean.WarpObj> kw_arr = lajiStrBean.kw_arr;
                            StringBuffer xb = new StringBuffer();
                            for (LajiStrBean.WarpObj warpObj : kw_arr) {
                                xb.append(warpObj.Name + "--" + warpObj.TypeKey + "--、\n" + warpObj.Note);
                                xb.append("\n\n");
                            }

//                    String sssss = "" + kw_list.toString() + "\n" +;
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        progressDialog.dismiss();
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle("识别结果")
                                                .setMessage(xb.toString())
                                                .show();
                                    } catch (Exception e) {

                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }).start();

            }
        });
    }

    ProgressDialog progressDialog ;
}