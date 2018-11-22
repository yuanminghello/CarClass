package com.example.a123.dyt.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.a123.dyt.R;
import com.example.a123.dyt.adapter.CarAdapter;
import com.example.a123.dyt.bean.CarBean;
import com.example.a123.dyt.util.HttpSingleton;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class CarFragment extends Fragment implements View.OnClickListener {
    private ExpandableListView expand;
    private CheckBox qx_ck;
    private TextView tv;
    private TextView tv_qian;
    private Button js_btn;
    String url = "http://www.zhaoapi.cn/product/getCarts";
    private CarAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.layout_car, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","71");
        HttpSingleton.getInstance().doPost(url, map, new HttpSingleton.UtilListener() {
            @Override
            public void succeed(String json) {
                Gson gson = new Gson();
                CarBean carBean = gson.fromJson(json, CarBean.class);
                if("0".equals(carBean.getCode())){
                    List<CarBean.DataBean> carBeanData = carBean.getData();

                    adapter = new CarAdapter(carBeanData);
                    expand.setAdapter(adapter);

                    adapter.setCarStateListener(new CarAdapter.CarStateListener() {
                        @Override
                        public void onSellerChange(int groupPosition) {
                            boolean currentShopSelect = adapter.isCurrentShopSelect(groupPosition);
                            adapter.groupSelect(groupPosition,!currentShopSelect);
                            adapter.notifyDataSetChanged();
                            refrestSelectAndNum();
                        }

                        @Override
                        public void onProductChange(int groupPosition, int childPosition) {
                                adapter.childSelect(groupPosition,childPosition);
                                adapter.notifyDataSetChanged();
                                refrestSelectAndNum();
                        }

                        @Override
                        public void onNumChange(int groupPosition, int childPosition, int number) {
                                 adapter.subAddSelect(groupPosition,childPosition,number);
                                 adapter.notifyDataSetChanged();
                                 refrestSelectAndNum();
                        }
                    });

                    for(int x=0;x<carBeanData.size();x++){
                        expand.expandGroup(x);
                    }
                }
            }

            @Override
            public void fail(Exception e) {

            }
        });
    }

    private void refrestSelectAndNum(){
        boolean allShopSelect = adapter.allShopSelect();
        qx_ck.setChecked(allShopSelect);

        int shopTotalNum = adapter.shopTotalNum();
        js_btn.setText("去结算"+shopTotalNum);

        float shopTotalPrice = adapter.shopTotalPrice();
        tv_qian.setText("总价"+shopTotalPrice);
    }

    private void initView(View view) {
        expand = (ExpandableListView) view.findViewById(R.id.expand);
        qx_ck = (CheckBox) view.findViewById(R.id.qx_ck);
        tv = (TextView) view.findViewById(R.id.tv);
        tv_qian = (TextView) view.findViewById(R.id.tv_qian);
        js_btn = (Button) view.findViewById(R.id.js_btn);

        qx_ck.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qx_ck:
                boolean select = adapter.allShopSelect();
                adapter.changAllProductState(!select);
                adapter.notifyDataSetChanged();
                refrestSelectAndNum();
                break;
        }
    }
}
