package com.carsuper.coahr.dagger.components;

import com.carsuper.coahr.mvp.view.base.BaseActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Author： hengzwd on 2018/6/19.
 * Email：hengzwdhengzwd@qq.com
 */


@Subcomponent(modules = {
        AndroidInjectionModule.class,
})
public interface BaseActivityComponent extends AndroidInjector<BaseActivity> {
    //每一个继承BaseActivity的Activity，都共享同一个SubComponent
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseActivity> {
    }

}

