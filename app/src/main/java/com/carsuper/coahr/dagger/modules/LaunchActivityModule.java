package com.carsuper.coahr.dagger.modules;

import com.carsuper.coahr.mvp.view.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */
@Module
public class LaunchActivityModule {


    @Provides
    static String provideName() {
        return MainActivity.class.getName();
    }
}
