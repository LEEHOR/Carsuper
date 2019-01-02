package com.carsuper.coahr.mvp.presenter.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MaintanceOrderContract;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.ServiceOrderCopyBean;
import com.carsuper.coahr.mvp.model.myData.maintanceOrder.MaintanceOrderModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MaintanceOrderFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class MaintanceOrderPresenter extends BasePresenter<MaintanceOrderContract.View, MaintanceOrderContract.Model> implements MaintanceOrderContract.Presenter {
    @Inject
    public MaintanceOrderPresenter(MaintanceOrderFragment mview, MaintanceOrderModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getMaintanceOrderList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getMaintanceOrderList(map);
        }
    }

    @Override
    public void onGetMaintanceOrderListSuccess(MaintanceOrderBean bean) {
        if (getView() != null) {
            getView().onGetMaintanceOrderListSuccess(bean);
        }
    }

    @Override
    public void onGetMaintanceOrderListFailure(String failure) {
        if (getView() != null) {
            getView().onGetMaintanceOrderListFailure(failure);
        }
    }

    @Override
    public void loadMore(Map<String, String> map) {
        if (mModle != null) {
            mModle.loadMore(map);
        }
    }

    @Override
    public void loadMoreSuccess(MaintanceOrderBean bean) {
        if (getView() != null) {
            getView().loadMoreSuccess(bean);
        }
    }

    @Override
    public void loadMoreFailure(String failure) {
        if (getView() != null) {
            getView().loadMoreFailure(failure);
        }
    }

    @Override
    public void confirmServiceFinish(Map<String, String> map) {
        if (mModle != null) {
            mModle.confirmServiceFinish(map);
        }
    }

    @Override
    public void onConfirmSuccess(ResultBean bean) {
        if (getView() != null) {
            getView().onConfirmSuccess(bean);
        }
    }

    @Override
    public void onConfirmFailure(String failure) {
        if (getView() != null) {
            getView().onConfirmFailure(failure);
        }
    }

    @Override
    public void quickPay(Map<String, String> map) {
        if (mModle != null) {
            mModle.quickPay(map);
        }
    }

    @Override
    public void onQuickPaySuccess(QuickPayBean bean) {
        if (getView() != null) {
            getView().onQuickPaySuccess(bean);
        }
    }

    @Override
    public void onQuickPayFailure(String failure) {
        if (getView() != null) {
            getView().onQuickPayFailure(failure);
        }
    }

    @Override
    public void ServiceOrderCopy(Map<String, String> map) {
        if (mModle != null) {
            mModle.ServiceOrderCopy(map);
        }
    }

    @Override
    public void onCopySuccess(ServiceOrderCopyBean bean) {
        if (getView() != null) {
            getView().onCopySuccess(bean);
        }
    }

    @Override
    public void onCopyFailure(String failure) {
        if (getView() != null) {
            getView().onCopyFailure(failure);
        }
    }

    @Override
    public void onCancelOrderSuccess(ResultBean resultBean) {
        if (getView() != null) {
            getView().onCancelOrderSuccess(resultBean);
        }
    }

    @Override
    public void onCancelOrderFailure(String failure) {
        if (getView() != null) {
            getView().onCancelOrderFailure(failure);
        }
    }

    @Override
    public void onCancelOder(Map<String, String> map) {
        if (mModle != null) {
            mModle.onCancelOder(map);
        }
    }
}
