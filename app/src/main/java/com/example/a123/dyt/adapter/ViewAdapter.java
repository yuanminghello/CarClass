package com.example.a123.dyt.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mlist;

    public ViewAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        return mlist.get(i);
    }

    public void setData(ArrayList<Fragment> list) {
        mlist = list;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }
}
