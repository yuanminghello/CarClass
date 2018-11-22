package com.example.a123.dyt.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a123.dyt.R;
import com.example.a123.dyt.bean.GroupBean;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.VHolder> {
    private List<GroupBean.DataBean> mdata;

    public GroupAdapter(List<GroupBean.DataBean> data) {
        mdata = data;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.layout_group_text,null);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder vHolder, final int i) {
        vHolder.title.setText(mdata.get(i).getName());
        vHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onChange(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class VHolder extends RecyclerView.ViewHolder{
        TextView title;
        public VHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }

    public interface ItemListener{
        void onChange(int position);

    }
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
}
