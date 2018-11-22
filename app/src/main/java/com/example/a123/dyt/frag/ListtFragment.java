package com.example.a123.dyt.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a123.dyt.R;
import com.example.a123.dyt.adapter.ChildAdapter;
import com.example.a123.dyt.adapter.GroupAdapter;
import com.example.a123.dyt.bean.ChildBean;
import com.example.a123.dyt.bean.GroupBean;
import com.example.a123.dyt.util.HttpSingleton;
import com.google.gson.Gson;

import java.util.List;

public class ListtFragment extends Fragment {
    private RecyclerView recycle_left;
    private RecyclerView recycle_right;
    int post=1;
    String groupUrl="http://www.zhaoapi.cn/product/getCatagory";
    String childUrl="http://www.zhaoapi.cn/product/getProductCatagory?cid="+post;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.layout_list, null);
        initView(view);
        initGroupData();
        initChildData();
        return view;
    }

    private void initGroupData() {
        HttpSingleton.getInstance().doGet(groupUrl, new HttpSingleton.UtilListener() {
            @Override
            public void succeed(String json) {
                Gson gson = new Gson();
                GroupBean groupBean = gson.fromJson(json, GroupBean.class);
                if("0".equals(groupBean.getCode())){
                    Log.e("Yml","分页右边成功");
                    List<GroupBean.DataBean> data = groupBean.getData();
                    GroupAdapter adapter = new GroupAdapter(data);
                    recycle_left.setAdapter(adapter);

                    adapter.setItemListener(new GroupAdapter.ItemListener() {
                        @Override
                        public void onChange(int position) {
                            Toast.makeText(getContext(),""+position,Toast.LENGTH_LONG).show();
                             int page=position+1;
                            String childFenUrl="http://www.zhaoapi.cn/product/getProductCatagory?cid="+page;
                            HttpSingleton.getInstance().doGet(childFenUrl, new HttpSingleton.UtilListener() {
                                @Override
                                public void succeed(String json) {
                                    Gson gson = new Gson();
                                    ChildBean childBean = gson.fromJson(json, ChildBean.class);
                                    if("0".equals(childBean.getCode())){
                                        Log.e("Yml","双分页左边成功");
                                        List<ChildBean.DataBean> beanData = childBean.getData();
                                        ChildAdapter adapter = new ChildAdapter(beanData);
                                        recycle_right.setAdapter(adapter);

                                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                                        recycle_right.setLayoutManager(manager);
                                        GridLayoutManager mmanager = new GridLayoutManager(getContext(),3);
                                        recycle_right.setLayoutManager(mmanager);
                                    }
                                }

                                @Override
                                public void fail(Exception e) {

                                }
                            });
                        }
                    });
                    recycle_left.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }

            @Override
            public void fail(Exception e) {

            }
        });
    }

    private void initChildData() {
        HttpSingleton.getInstance().doGet(childUrl, new HttpSingleton.UtilListener() {
            @Override
            public void succeed(String json) {
                Gson gson = new Gson();
                ChildBean childBean = gson.fromJson(json, ChildBean.class);
                if("0".equals(childBean.getCode())){
                    Log.e("Yml","分页左边成功");
                    List<ChildBean.DataBean> beanData = childBean.getData();
                    ChildAdapter adapter = new ChildAdapter(beanData);
                    recycle_right.setAdapter(adapter);

                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    recycle_right.setLayoutManager(manager);
                    GridLayoutManager mmanager = new GridLayoutManager(getContext(),3);
                    recycle_right.setLayoutManager(mmanager);
                }
            }

            @Override
            public void fail(Exception e) {

            }
        });
    }

    private void initView(View view) {
        recycle_left = (RecyclerView) view.findViewById(R.id.recycle_left);
        recycle_right = (RecyclerView) view.findViewById(R.id.recycle_right);
    }
}
