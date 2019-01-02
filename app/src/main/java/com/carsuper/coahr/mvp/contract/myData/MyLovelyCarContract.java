package com.carsuper.coahr.mvp.contract.myData;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.MyLovelyCarBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface MyLovelyCarContract {
    interface View extends BaseContract.View {
        void  onGetLovelyCarListSuccess(MyLovelyCarBean bean);
        void onGetLovelyCarListFailure(String failure);

        void onDeleteSuccess(MyLovelyCarBean.JdataBean.MycarBean mycarEntity,ResultBean bean);
        void onDeleteFailure(String failure);
        void onSetPrimarySuccess(MyLovelyCarBean.JdataBean.MycarBean mycarEntity ,ResultBean bean);
        void onSetPrimaryFailure(String failure);

        void onSaveUserCarInfoSuccess(SaveUserCarBean bean);
        void onSaveUserCarInfoFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {

        void  getLovelyCarList(Map<String,String> map );
        void  onGetLovelyCarListSuccess(MyLovelyCarBean bean);
        void onGetLovelyCarListFailure(String failure);

        void deleteLovelyCar(Map<String,String> map , MyLovelyCarBean.JdataBean.MycarBean mycarEntity);

        void onDeleteSuccess(MyLovelyCarBean.JdataBean.MycarBean mycarEntity,ResultBean bean);
        void onDeleteFailure(String failure);

        void setPrimary(Map<String,String> map,MyLovelyCarBean.JdataBean.MycarBean mycarEntity);

        void onSetPrimarySuccess(MyLovelyCarBean.JdataBean.MycarBean mycarEntity ,ResultBean bean);
        void onSetPrimaryFailure(String failure);


        void saveUserCarInfo(Map<String, String> map);

        void onSaveUserCarInfoSuccess(SaveUserCarBean bean);

        void onSaveUserCarInfoFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void  getLovelyCarList(Map<String,String> map );
        void deleteLovelyCar(Map<String,String> map ,MyLovelyCarBean.JdataBean.MycarBean mycarEntity);

        void setPrimary(Map<String,String> map,MyLovelyCarBean.JdataBean.MycarBean mycarEntity);
        void saveUserCarInfo(Map<String, String> map);
    }


}
