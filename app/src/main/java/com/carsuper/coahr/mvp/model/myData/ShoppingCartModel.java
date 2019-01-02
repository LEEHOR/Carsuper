package com.carsuper.coahr.mvp.model.myData;

import com.carsuper.coahr.mvp.contract.myData.ShoppingCartContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.DeleteCarBean;
import com.carsuper.coahr.mvp.model.bean.ShoppingCartBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ShoppingCartModel extends BaseModel<ShoppingCartContract.Presenter> implements ShoppingCartContract.Model {
    @Inject
    public ShoppingCartModel(){
        super();
    }

    @Override
    public void getShoppingCart(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ShoppingCartBean>(getApiservice().getShoppingCart(map.get("token"),map.get("page"),map.get("length"))))
        .subscribeWith(new SimpleDisposableSubscriber<ShoppingCartBean>() {
            @Override
            public void _onNext(ShoppingCartBean bean) {
                if (getPresenter() != null) {
                    if (bean.getCode()==0) {
                        getPresenter().onGetShoppingCartSuccess(bean);
                    }else {
                        getPresenter().onGetShoppingCartFailure(bean.getMsg());
                    }
                }
            }
        }));
    }

    @Override
    public void onDeleteCar(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<DeleteCarBean>(getApiservice().del_from_shopping_cart(map.get("token"),map.get("c_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<DeleteCarBean>() {
                    @Override
                    public void _onNext(DeleteCarBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().nDeleteCarSuccess(bean);
                            }else {
                                getPresenter().nDeleteCarFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
