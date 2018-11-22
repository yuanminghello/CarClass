package com.example.a123.dyt.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a123.dyt.R;
import com.example.a123.dyt.bean.RecycleBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.VHolder> {
    private List<RecycleBean.DataBean> mdata;

    public RecycleAdapter(List<RecycleBean.DataBean> data) {
        mdata = data;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.layout_recycle_list, null);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder vHolder, int i) {
        vHolder.address.setText(mdata.get(i).getAddress());
        ImageLoader.getInstance().displayImage(mdata.get(i).getPic_url(),vHolder.icon);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class VHolder extends RecyclerView.ViewHolder{
        ImageView icon ;
        TextView address;
        public VHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            address = itemView.findViewById(R.id.address);
        }
    }
}
