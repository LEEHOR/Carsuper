package com.carsuper.coahr.dagger.modules.store;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

import com.carsuper.coahr.mvp.contract.myData.MyDataContract;
import com.carsuper.coahr.mvp.contract.store.StoreContract;
import com.carsuper.coahr.mvp.model.myData.MyDataModel;
import com.carsuper.coahr.mvp.model.store.StoreModel;
import com.carsuper.coahr.mvp.presenter.myData.MyDataPresenter;
import com.carsuper.coahr.mvp.presenter.store.StorePresenter;
import com.carsuper.coahr.mvp.view.myData.MyDataFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.ShoppingMallFragment;
import com.carsuper.coahr.mvp.view.store.StoreFragment;

import java.util.concurrent.RecursiveTask;

import dagger.Module;
import dagger.Provides;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */

@Module
public class StoreFragmentModule {

    @Provides
    static String provideName() {
        return StoreFragment.class.getName();
    }


    @Provides
    static Activity providerActivity(StoreFragment storeFragment){
        return storeFragment.getActivity();
    }
    @Provides
    static WindowManager provideWindowManager(StoreFragment storeFragment) {
        return (WindowManager) storeFragment.getActivity().getSystemService(Context.WINDOW_SERVICE);
    }



}
