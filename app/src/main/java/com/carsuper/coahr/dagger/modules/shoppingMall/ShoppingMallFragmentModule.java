package com.carsuper.coahr.dagger.modules.shoppingMall;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

import com.carsuper.coahr.mvp.contract.shoppingMall.ShoppingMalContract;
import com.carsuper.coahr.mvp.contract.store.StoreContract;
import com.carsuper.coahr.mvp.model.shoppingMall.ShoppingMalModel;
import com.carsuper.coahr.mvp.model.store.StoreModel;
import com.carsuper.coahr.mvp.presenter.shoppingMall.ShoppingMalPresenter;
import com.carsuper.coahr.mvp.presenter.store.StorePresenter;
import com.carsuper.coahr.mvp.view.main.MainFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.ShoppingMallFragment;
import com.carsuper.coahr.mvp.view.store.StoreFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */
@Module
public class ShoppingMallFragmentModule {

    @Provides
    static String provideName() {
        return ShoppingMallFragment.class.getName();
    }


    @Provides
    static Activity provideActivity(ShoppingMallFragment shoppingMallFragment){
        return shoppingMallFragment.getActivity();

    }

    @Provides
    static WindowManager provideWindowManager(ShoppingMallFragment shoppingMallFragment) {
        return (WindowManager) shoppingMallFragment.getActivity().getSystemService(Context.WINDOW_SERVICE);
    }

}
