package com.example.a123.dyt.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a123.dyt.R;
import com.example.a123.dyt.bean.CarBean;
import com.example.a123.dyt.view.AddSubView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class CarAdapter extends BaseExpandableListAdapter {
    private List<CarBean.DataBean> mcarBeanData;



    public CarAdapter(List<CarBean.DataBean> carBeanData) {
        mcarBeanData = carBeanData;
    }

    @Override
    public int getGroupCount() {
        return mcarBeanData==null?0:mcarBeanData.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mcarBeanData.get(i).getList()==null ? 0 :mcarBeanData.get(i).getList().size();
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        CarBean.DataBean bean = mcarBeanData.get(i);
        GroupViewHolder gh;
        if(view==null){
            view = View.inflate(viewGroup.getContext(), R.layout.layout_car_group,null);
            gh = new GroupViewHolder();
            gh.group_cb = view.findViewById(R.id.group_cb);
            gh.group_tv = view.findViewById(R.id.group_tv);
            view.setTag(gh);
        }else {
            gh = (GroupViewHolder) view.getTag();
        }
         gh.group_tv.setText(bean.getSellerName());


        //点击监听
        gh.group_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CarStateListener!=null){
                    CarStateListener.onSellerChange(i);
                }

            }
        });


        return view;
    }
    class GroupViewHolder{
        CheckBox group_cb;
        TextView group_tv;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        CarBean.DataBean bean = mcarBeanData.get(i);
        List<CarBean.DataBean.ListBean> list = bean.getList();
        CarBean.DataBean.ListBean listBean = list.get(i1);
        ChildViewHolder ch;
        if(view==null){
            view = View.inflate(viewGroup.getContext(), R.layout.layout_car_child, null);
            ch = new ChildViewHolder();
            ch.child_cb = view.findViewById(R.id.child_cb);
            ch.child_iv = view.findViewById(R.id.child_iv);
            ch.child_tv= view.findViewById(R.id.child_tv);
            ch.child_tv2 = view.findViewById(R.id.child_tv2);
            ch.child_view = view.findViewById(R.id.child_view);

            view.setTag(ch);
        }else {
            ch = (ChildViewHolder) view.getTag();
        }

        ImageLoader.getInstance().displayImage(listBean.getImages(),ch.child_iv);

        ch.child_tv.setText(listBean.getTitle());
        ch.child_tv2.setText(listBean.getPrice()+"");
        //必须要加的  关系到选中状态
        ch.child_view.setNum(listBean.getNum());
        ch.child_cb.setChecked(listBean.getSelected()==1);
        //组合控件的接口
        ch.child_view.setNumberListener(new AddSubView.NumberListener() {
            @Override
            public void onNum(int num) {
                 if(CarStateListener!=null){
                     CarStateListener.onNumChange(i,i1,num);
                 }
            }
        });
        ch.child_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CarStateListener!=null){
                    CarStateListener.onProductChange(i,i1);
                }

            }
        });
        return view;
    }

    class ChildViewHolder{
        CheckBox child_cb;
        TextView child_tv;
        TextView child_tv2;
        ImageView child_iv;
        AddSubView child_view;
    }



    //A1  当前商品
    public boolean isCurrentShopSelect(int groupPosition){
        CarBean.DataBean bean = mcarBeanData.get(groupPosition);
        List<CarBean.DataBean.ListBean> list = bean.getList();
        for(CarBean.DataBean.ListBean listBean :list){
            if(listBean.getSelected()==0){
                return false;
            }
        }
        return true;
    }

    //A2  所有商品
    public boolean allShopSelect(){
        for(int i=0;i<mcarBeanData.size();i++){
            CarBean.DataBean dataBean = mcarBeanData.get(i);
            List<CarBean.DataBean.ListBean> list = dataBean.getList();
            for(int j=0;j<list.size();j++){
                if(list.get(j).getSelected() == 0){
                    return false;
                }
            }
        }
        return  true;
    }
    //A3 数量
    public int shopTotalNum(){
        int toltalNum=0;
        for(int i=0;i<mcarBeanData.size();i++){
            CarBean.DataBean dataBean = mcarBeanData.get(i);
            List<CarBean.DataBean.ListBean> beanList = dataBean.getList();
            for(int j=0;j<beanList.size();j++){
                if(beanList.get(j).getSelected()==1){
                    int num = beanList.get(j).getNum();
                    toltalNum+=num;
                }
            }
        }
        return toltalNum;
    }
    //A4 价钱
    public float shopTotalPrice(){
        float toltalPrice=0;
        for(int i=0;i<mcarBeanData.size();i++){
            CarBean.DataBean dataBean = mcarBeanData.get(i);
            List<CarBean.DataBean.ListBean> beanList = dataBean.getList();
            for(int j=0;j<beanList.size();j++){
                if(beanList.get(j).getSelected()==1){
                    int num = beanList.get(j).getNum();
                    float price = beanList.get(j).getPrice();
                    toltalPrice+=num*price;
                }
            }
        }
        return toltalPrice;
    }
    //A5  商家
    public void groupSelect(int groupPosition,boolean isCheck){
        CarBean.DataBean dataBean = mcarBeanData.get(groupPosition);
        List<CarBean.DataBean.ListBean> list = dataBean.getList();
        for(int i=0;i<list.size();i++){
            list.get(i).setSelected(isCheck ? 1:0);
        }
    }
    //A6  商品
    public void childSelect(int groupPosition,int childPosition){
        CarBean.DataBean dataBean = mcarBeanData.get(groupPosition);
        List<CarBean.DataBean.ListBean> list = dataBean.getList();
        CarBean.DataBean.ListBean bean = list.get(childPosition);
        bean.setSelected(bean.getSelected()==0?1:0);
    }
    //A7 状态
    public  void changAllProductState(boolean isSelect){
        for(int i=0;i<mcarBeanData.size();i++){
            CarBean.DataBean dataBean = mcarBeanData.get(i);
            List<CarBean.DataBean.ListBean> beanList = dataBean.getList();
            for(int j=0;j<beanList.size();j++){
                beanList.get(j).setSelected(isSelect?1:0);
            }
        }
    }
    //A8  加减
    public void subAddSelect(int groupPosition,int childPosition,int number){
        CarBean.DataBean dataBean = mcarBeanData.get(groupPosition);
        List<CarBean.DataBean.ListBean> list = dataBean.getList();
        CarBean.DataBean.ListBean bean = list.get(childPosition);
        bean.setNum(number);
    }


/////接口回调

    public interface CarStateListener{
        void onSellerChange(int groupPosition);
        void onProductChange(int groupPosition,int childPosition);
        void onNumChange(int groupPosition,int childPosition,int number);
    }

    private CarStateListener CarStateListener;

    public void setCarStateListener(CarStateListener CarStateListener) {
        this.CarStateListener = CarStateListener;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
