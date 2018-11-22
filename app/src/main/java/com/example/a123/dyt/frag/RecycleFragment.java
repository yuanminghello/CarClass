package com.example.a123.dyt.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a123.dyt.R;
import com.example.a123.dyt.adapter.ChildAdapter;
import com.example.a123.dyt.adapter.RecycleAdapter;
import com.example.a123.dyt.bean.ChildBean;
import com.example.a123.dyt.bean.RecycleBean;
import com.example.a123.dyt.util.HttpSingleton;
import com.google.gson.Gson;

import java.util.List;

public class RecycleFragment extends Fragment {
    private RecyclerView recycle_second;
    String url="http://www.wanandroid.com/tools/mockapi/6523/restaurants_offset_0_limit_4 ";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.layout_recycle, null);

        initView(view);
        initDate();
        return view;
    }

    private void initDate() {
        HttpSingleton.getInstance().doGet(url, new HttpSingleton.UtilListener() {
            @Override
            public void succeed(String json) {
                Gson gson = new Gson();
                RecycleBean recycleBean = gson.fromJson(json, RecycleBean.class);
                if("200".equals(recycleBean.getStatus())){
                    Log.e("Yml","成功");
                    List<RecycleBean.DataBean> data = recycleBean.getData();
                    RecycleAdapter adapter = new RecycleAdapter(data);
                    recycle_second.setAdapter(adapter);
                    recycle_second.setLayoutManager
                            (new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));
                }
            }

            @Override
            public void fail(Exception e) {

            }
        });
    }

    private void initView(View view) {
        recycle_second = (RecyclerView) view.findViewById(R.id.recycle_second);
    }
}
