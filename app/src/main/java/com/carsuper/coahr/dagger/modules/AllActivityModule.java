package com.carsuper.coahr.dagger.modules;

import com.carsuper.coahr.dagger.components.BaseActivityComponent;

import com.carsuper.coahr.dagger.modules.main.MainActivityModule;


import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.LaunchActivity;
import com.carsuper.coahr.mvp.view.MainActivity;
import com.carsuper.coahr.wxapi.WXPayEntryActivity;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Author： hengzwd on 2018/6/19.
 * Email：hengzwdhengzwd@qq.com
 */


@Module(subcomponents = {
        BaseActivityComponent.class
})
public abstract class AllActivityModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivityInjector();


    @ContributesAndroidInjector(modules = LaunchActivityModule.class)
    abstract LaunchActivity contributeLaunchActivityInjector();
    //每个activity都要在这里声明

    @ContributesAndroidInjector(modules = ContainerActivityModule.class)
    abstract ContainerActiivty contributeContainerActiivtyInjector();

    @ContributesAndroidInjector()
    abstract WXPayEntryActivity contributeWXPayEntryActivityInjector();



}
