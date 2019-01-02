package com.carsuper.coahr.dagger.modules.retrofit;

import com.carsuper.coahr.mvp.model.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Author： hengzwd on 2018/6/19.
 * Email：hengzwdhengzwd@qq.com
 */

@Module
public class ApiModule {

    @Singleton
    @Provides
    public ApiService providerApiservice(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
