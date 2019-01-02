package com.carsuper.coahr.dagger.components;

import com.carsuper.coahr.dagger.modules.AllActivityModule;
import com.carsuper.coahr.dagger.modules.AllFragmentModule;
import com.carsuper.coahr.dagger.modules.AppModule;
import com.carsuper.coahr.dagger.modules.LocationModule;
import com.carsuper.coahr.dagger.modules.retrofit.ApiModule;
import com.carsuper.coahr.dagger.modules.retrofit.OkhttpModule;
import com.carsuper.coahr.dagger.modules.retrofit.RetrofitModule;
import com.carsuper.coahr.mvp.view.base.BaseApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Author： hengzwd on 2018/6/19.
 * Email：hengzwdhengzwd@qq.com
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AllActivityModule.class,
        AllFragmentModule.class,
        OkhttpModule.class,
        RetrofitModule.class,
        ApiModule.class,
        AppModule.class,
        LocationModule.class
})
public interface ApplicationComponent {

    void inject(BaseApplication application);
}

