package com.example.a123.dyt.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a123.dyt.R;
import com.example.a123.dyt.bean.ChildBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {
    private List<ChildBean.DataBean> mbeanData;

    public ChildAdapter(List<ChildBean.DataBean> beanData) {
        mbeanData = beanData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.layout_child_text, null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        int size = mbeanData.get(i).getList().size();
        for(int x=0;x<size;x++){
            if(x==0){
                viewHolder.txt_title1.setText(mbeanData.get(i).getList().get(0).getName());
                ImageLoader.getInstance().displayImage(mbeanData.get(i).getList().get(0).getIcon(),viewHolder.img1);
            }
            if(x==1){
                viewHolder.txt_title2.setText(mbeanData.get(i).getList().get(1).getName());
                ImageLoader.getInstance().displayImage(mbeanData.get(i).getList().get(1).getIcon(),viewHolder.img2);
            }
            if(x==2){
                viewHolder.txt_title3.setText(mbeanData.get(i).getList().get(2).getName());
                ImageLoader.getInstance().displayImage(mbeanData.get(i).getList().get(2).getIcon(),viewHolder.img3);
            }
            if(x==3){
                viewHolder.txt_title4.setText(mbeanData.get(i).getList().get(3).getName());
                ImageLoader.getInstance().displayImage(mbeanData.get(i).getList().get(3).getIcon(),viewHolder.img4);
            }
            if(x==4){
                viewHolder.txt_title5.setText(mbeanData.get(i).getList().get(4).getName());
                ImageLoader.getInstance().displayImage(mbeanData.get(i).getList().get(4).getIcon(),viewHolder.img5);
            }
            if(x==5){
                viewHolder.txt_title6.setText(mbeanData.get(i).getList().get(5).getName());
                ImageLoader.getInstance().displayImage(mbeanData.get(i).getList().get(5).getIcon(),viewHolder.img6);
            }
            if(x==6){
                viewHolder.txt_title7.setText(mbeanData.get(i).getList().get(6).getName());
                ImageLoader.getInstance().displayImage(mbeanData.get(i).getList().get(6).getIcon(),viewHolder.img7);
            }


        }
       // ImageLoader.getInstance().displayImage(mbeanData.get(i).getList().get(i).getIcon(),viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return mbeanData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_title1;
        TextView txt_title2;
        TextView txt_title3;
        TextView txt_title4;
        TextView txt_title5;
        TextView txt_title6;
        TextView txt_title7;
        ImageView img1;
        ImageView img2;
        ImageView img3;
        ImageView img4;
        ImageView img5;
        ImageView img6;
        ImageView img7;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            img4 = itemView.findViewById(R.id.img4);
            img5= itemView.findViewById(R.id.img5);
            img6= itemView.findViewById(R.id.img6);
            img7= itemView.findViewById(R.id.img7);
            txt_title1 = itemView.findViewById(R.id.txt_title1);
            txt_title2 = itemView.findViewById(R.id.txt_title2);
            txt_title3 = itemView.findViewById(R.id.txt_title3);
            txt_title4 = itemView.findViewById(R.id.txt_title4);
            txt_title5 = itemView.findViewById(R.id.txt_title5);
            txt_title6 = itemView.findViewById(R.id.txt_title6);
            txt_title7 = itemView.findViewById(R.id.txt_title7);
        }
    }
}
