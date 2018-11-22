package com.example.a123.dyt.app;

import android.app.Application;

import com.example.a123.dyt.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * date:2018/11/19
 * author:袁明磊(123)
 * function:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)

                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)

                .build();
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(10 * 50 * 50)
                .memoryCacheSizePercentage(13)

                .defaultDisplayImageOptions(options)

                .build();

        ImageLoader.getInstance().init(build);



    }
}
