package com.carsuper.coahr.dagger.modules.main;

import com.carsuper.coahr.mvp.view.main.MainFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Author： hengzwd on 2018/6/19.
 * Email：hengzwdhengzwd@qq.com
 */

@Module
public class MainFragmentModule {


    @Provides
    public  String provideName() {
        return MainFragment.class.getName();
    }







   //其他对象的提供
}