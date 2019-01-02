package com.carsuper.coahr.dagger.modules.main;

import com.carsuper.coahr.mvp.view.Exchange_mall.Fragment_exchange_shopping_detail;

import dagger.Module;
import dagger.Provides;

/**
 * Author： hengzwd on 2018/6/19.
 * Email：hengzwdhengzwd@qq.com
 */

@Module
public class Fragment_Exchange_shopping_detail_Module {
    @Provides
    public  String provideName() {
        return Fragment_exchange_shopping_detail.class.getName();
    }







   //其他对象的提供
}