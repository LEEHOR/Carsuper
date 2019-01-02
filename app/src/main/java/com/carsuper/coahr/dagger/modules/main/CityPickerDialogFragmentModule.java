package com.carsuper.coahr.dagger.modules.main;

import android.support.v7.widget.LinearLayoutManager;

import com.carsuper.coahr.mvp.view.main.CityPickerDialogFragment;
import com.carsuper.coahr.mvp.view.main.MainFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Author： hengzwd on 2018/7/19.
 * Email：hengzwdhengzwd@qq.com
 */

@Module
public class CityPickerDialogFragmentModule {


    @Provides
    public  LinearLayoutManager provideLinearlayoutManager(CityPickerDialogFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity(), LinearLayoutManager.VERTICAL, false);
    }



}
