package com.carsuper.coahr.mvp.model.base;

import android.support.annotation.UiThread;

import com.carsuper.coahr.common.RxManager;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.ApiService;
import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Author： hengzwd on 2018/6/5.
 * Email：hengzwdhengzwd@qq.com
 */
public abstract class BaseModel<P extends BaseContract.Presenter> implements BaseContract.Model {


    @Inject
    Retrofit retrofit;

    protected WeakReference<P> mPresenter;

    protected  boolean  isloading = false;//正在网络访问

    //每一套mvp应该拥有一个独立的RxManager
    public RxManager mRxManager = new RxManager();

    public BaseModel() {

     //   KLog.e("BaseModel", this.getClass().toString());

    }

    public ApiService getApiservice() {
        return retrofit.create(ApiService.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    @UiThread
    public void AttachPresenter(BaseContract.Presenter presenter) {
        mPresenter = new WeakReference<P>((P) presenter);
    }


    @UiThread
    public boolean isPresenterAttached() {
        return mPresenter != null && mPresenter.get() != null;
    }

    public P getPresenter() {
        return mPresenter == null ? null : mPresenter.get();
    }

    @UiThread
    public void detachPresenter() {
        mRxManager.clear();
        if (getPresenter() != null) {
            getPresenter().dismissLoading();
        }
        if (mPresenter != null) {
            mPresenter.clear();
            mPresenter = null;
        }
    }



    //由于rxjava2.0  回调太多，本项目部分回调不与使用或处理趋于雷同，所以以下代码做简易化封装
    public <T> Flowable<T> createFlowable(SimpleFlowableOnSubscribe<T> simpleFlowableOnSubscribe ){
       return  Flowable.create(simpleFlowableOnSubscribe, BackpressureStrategy.BUFFER);
    }


    public class SimpleFlowableOnSubscribe<T> implements FlowableOnSubscribe<T> {

        private Call<T> call;

        public SimpleFlowableOnSubscribe(Call<T> call) {
            this.call = call;
        }

        @Override
        public void subscribe(final FlowableEmitter<T> e) {
            if (!isloading) {
                isloading = true;
                if (getPresenter() != null) {
                    getPresenter().showLoading();
                }
                call.enqueue(new Callback<T>() {
                    @Override
                    public void onResponse(Call<T> call, Response<T> response) {
                        isloading = false;
                        if (getPresenter() != null) {
                            getPresenter().dismissLoading();
                        }
                        e.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<T> call, Throwable t) {
                        isloading = false;
                        if (getPresenter() != null) {
                            getPresenter().dismissLoading();

                        }
                        e.onError(t);
                    }
                });
            }
        }
    }



    public abstract class SimpleDisposableSubscriber<T> extends DisposableSubscriber<T> {

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable t) {
            t.printStackTrace();
            getPresenter().showError(t);

        }

        @Override
        public void onNext(T t) {
            _onNext(t);
        }


        public abstract void _onNext(T t);



//        public abstract void _onError(Throwable t);
    }

}
