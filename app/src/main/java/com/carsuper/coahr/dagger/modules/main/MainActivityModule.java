package com.carsuper.coahr.dagger.modules.main;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;

import com.carsuper.coahr.mvp.view.MainActivity;
import com.carsuper.coahr.mvp.view.base.BaseApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Author： hengzwd on 2018/6/19.
 * Email：hengzwdhengzwd@qq.com
 */

@Module
public class MainActivityModule {

    @Provides
    static String provideName() {
        return MainActivity.class.getName();
    }

    @Provides
    static SharedPreferences provideSp(MainActivity activity) {
        return activity.getSharedPreferences("def", Context.MODE_PRIVATE);
    }
    //其他对象的提供


}

