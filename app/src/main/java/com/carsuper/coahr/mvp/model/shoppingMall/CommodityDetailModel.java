package com.carsuper.coahr.mvp.model.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityDetailContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CommodityDetailBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityDetailModel extends BaseModel<CommodityDetailContract.Presenter> implements CommodityDetailContract.Model {
    @Inject
    public CommodityDetailModel(){
        super();
    }

    @Override
    public void getCommodityDetail(Map<String, String> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CommodityDetailBean>(getApiservice().getCommodityDetail(map.get("c_id"),map.get("token"))))
            .subscribeWith(new SimpleDisposableSubscriber<CommodityDetailBean>() {
                @Override
                public void _onNext(CommodityDetailBean commodityDetailBean) {
                    if (getPresenter() != null) {
                        if (commodityDetailBean.getCode()==0) {
                            getPresenter().onGetCommodityDetailSuccess(commodityDetailBean);
                        }else {
                            getPresenter().onGetCommodityDetailFailure(commodityDetailBean.getMsg());
                        }
                    }
                }
            }));
    }

    @Override
    public void addToShoppingCart(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().addToShoppingCart(map.get("c_id"),map.get("c_num"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onAddSuccess(bean);
                            }else {
                                getPresenter().onAddFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
