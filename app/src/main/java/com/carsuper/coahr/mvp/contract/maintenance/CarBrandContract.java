package com.carsuper.coahr.mvp.contract.maintenance;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.CarBrandBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface CarBrandContract {
    interface View extends BaseContract.View {
        void onGetCarBrandSuccess(CarBrandBean brandBean);

        void onGetCarBrandFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getCarBrand(Map<String,String > map);

        void onGetCarBrandSuccess(CarBrandBean brandBean);

        void onGetCarBrandFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void getCarBrand(Map<String,String > map);
    }

}
