package com.example.laji.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.laji.LoginActivity;
import com.example.laji.R;
import com.example.laji.XData;
import com.example.laji.bean.ResultBean;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    static boolean ttt = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!ttt) {
            ttt = true;
            LoginActivity.str = "垃圾存量管理";
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }

        ListView lv = getActivity().findViewById(R.id.lv);

        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return XData.resultList.size();
            }

            @Override
            public Object getItem(int position) {
                return XData.resultList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.iteasdasds, parent, false);
                TextView tv = convertView.findViewById(R.id.t1);

                ResultBean resultBean = XData.resultList.get(position);
                tv.setText("" + resultBean.str);
                tv.setText(""+resultBean.ctime);

                return convertView;
            }
        });
    }
}