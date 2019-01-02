package com.carsuper.coahr.dagger.modules;

import com.carsuper.coahr.mvp.model.BaiduLocationHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author： hengzwd on 2018/8/8.
 * Email：hengzwdhengzwd@qq.com
 */

@Module
public class LocationModule {

    @Singleton
    @Provides
    public BaiduLocationHelper providerBaiduLocationHelper(){
        return new BaiduLocationHelper();
    }
}
