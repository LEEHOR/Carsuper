package com.carsuper.coahr.dagger.modules.myData;

import com.carsuper.coahr.mvp.contract.myData.MyDataContract;
import com.carsuper.coahr.mvp.model.myData.MyDataModel;
import com.carsuper.coahr.mvp.presenter.myData.MyDataPresenter;
import com.carsuper.coahr.mvp.view.main.MainFragment;
import com.carsuper.coahr.mvp.view.myData.MyDataFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */

@Module
public class MyDataFragmentModule {


    @Provides
    static String provideName() {
        return MyDataFragment.class.getName();
    }


}
