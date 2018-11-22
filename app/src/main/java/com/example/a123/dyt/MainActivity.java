package com.example.a123.dyt;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.a123.dyt.adapter.ViewAdapter;
import com.example.a123.dyt.frag.CarFragment;
import com.example.a123.dyt.frag.ListtFragment;
import com.example.a123.dyt.frag.RecycleFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager view;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initCreate();


    }

    private void initCreate() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new ListtFragment());
        list.add(new RecycleFragment());
        list.add(new CarFragment());

         ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager());
         adapter.setData(list);
         view.setAdapter(adapter);

        tab.setupWithViewPager(view);

        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());
        tab.addTab(tab.newTab());

        tab.getTabAt(0).setText("分类");
        tab.getTabAt(1).setText("瀑布");
        tab.getTabAt(2).setText("购物车");
    }

    private void initView() {
        view = (ViewPager) findViewById(R.id.view);
        tab = (TabLayout) findViewById(R.id.tab);
    }
}
