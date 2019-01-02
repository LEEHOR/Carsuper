package com.carsuper.coahr.dagger.components;

import com.carsuper.coahr.mvp.view.base.BaseFragment;

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
public interface BaseFragmentComponent extends AndroidInjector<BaseFragment> {

    //每一个继承BaseFragment的fragment，都共享同一个SubComponent
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseFragment> {
    }
}
