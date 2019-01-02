package com.carsuper.coahr.mvp.model.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MHaveBeenCompleteContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;
import com.socks.library.KLog;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/14.
 * Email：hengzwdhengzwd@qq.com
 */
public class MHaveBeenCompleteModel extends BaseModel<MHaveBeenCompleteContract.Presenter> implements MHaveBeenCompleteContract.Model {

    @Inject
    public MHaveBeenCompleteModel(){
        super();
    }


    @Override
    public void getMaintanceOrderDetail(Map<String, String> map) {
        KLog.d(map.get("order_id"));
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MaintanceOrderDetailBean>(getApiservice().getMaintanceOrderDetail(map.get("token"),map.get("order_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<MaintanceOrderDetailBean>() {
                    @Override
                    public void _onNext(MaintanceOrderDetailBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onGetMaintanceOrderDetialSuccess(bean);
                            }else {
                                getPresenter().onGetMaintanceOrderDetailFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
