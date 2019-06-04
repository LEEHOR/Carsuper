package com.carsuper.coahr.mvp.view.base;

import android.Manifest;
import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.baidu.location.BDLocation;
import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.BaiduLocationHelper;
import com.carsuper.coahr.mvp.model.bean.AlyPayResult;
import com.carsuper.coahr.mvp.model.bean.OrderJsonEntity;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.MainActivity;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.DisplayUtils;
import com.carsuper.coahr.utils.KeyBoardUtils;
import com.carsuper.coahr.utils.MD5;
import com.carsuper.coahr.utils.NavigationBarUtils;
import com.carsuper.coahr.utils.Permission.OnRequestPermissionListener;
import com.carsuper.coahr.utils.Permission.RequestPermissionUtils;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.carsuper.coahr.utils.ScreenUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.socks.library.KLog;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import java.security.MessageDigest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author： hengzwd on 2017/6/1.
 * Email：hengzwdhengzwd@qq.com
 */

public abstract class BaseFragment<P extends BaseContract.Presenter> extends SupportFragment implements BaseContract.View {

    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    protected boolean hasStarted = false;


    //通过windowManger建立覆盖在屏幕指定部位的阴影view，由于activity+fragment架构方式，有时候
    //通过布局的方式无法在activity与fragment上用一个view显示阴影

    private View mDarkView;

    private boolean mIsDarkInvoked = false;
    Unbinder unbinder;

    private Dialog dialog;

    private static final int SDK_PAY_FLAG = 1;//支付宝支付

    //    private static final int SDK_AUTH_FLAG = 2;//支付宝验证
    public abstract P getPresenter();

    public abstract int bindLayout();

    public abstract void initView();

    public abstract void initData();

    protected View addFooterView;

    private  BaiduLocationHelper baiduLocationHelper;
    private  BaiduLocationHelper.OnLocationCallBack locationCallBack;
    private FragmentBaiduListener fragmentBaiduListener;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this); //一处声明，处处依赖注入

        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(bindLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);

        //由于沉浸式要留白 标题栏，在这里统一设置，支出statusbar的空间，之后每个fragment的头顶第一个子view，都要
        //以一个viewgroup包含要显示tittle的子view形式进行布局，则此代码正确有效
//        view.getRootView().setBackgroundColor(getResources().getColor(R.color.app_background_color));
        View tittleView = ((ViewGroup) view.getRootView()).getChildAt(0);
        if (tittleView != null) {
            tittleView.setPadding(tittleView.getPaddingLeft(), ScreenUtils.getStatusBarHeight(BaseApplication.mContext), tittleView.getPaddingRight(), tittleView.getPaddingBottom());
        }
        addFooterView = inflater.inflate(R.layout.recyclerview_item_foot, container, false);
        UpdateUI(view.getRootView());
        initView();
        initData();
//        EventBus.getDefault().register(this);
        //设置虚拟键
        if (NavigationBarUtils.hasNavigationBarFun(getActivity())){
            if (NavigationBarUtils.isNavigationBarShow(getActivity())){
                NavigationBarUtils.setNavigationColor(getActivity(),getActivity().getResources().getColor(R.color.material_white));
            }
        }
        return view;
    }

    @Override
    public void showLoading() {
        //创建Dialog并传递style文件
        if (dialog == null) {
//            dialog = new AlertDialog.Builder(_mActivity,R.style.dialog_loading).create();
//            dialog.setContentView(R.layout.dialog_loading_layout);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable());//解决5.0以上阴影问题
//            dialog.show();
            dialog = new Dialog(_mActivity, R.style.dialog_loading);
            dialog.setContentView(R.layout.dialog_loading_layout);
        }
        // 设置它的ContentView
        if (!dialog.isShowing()) {
            dialog.show();//显示dialog
        }
    }

    @Override
    public void dismissLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    public void showError(@Nullable Throwable e) {
    }


    public void recieveData(Bundle bundle) {

    }

    //有ptr功能的fragment页面调用此方法
    public void initPtrFrameLayout(final PtrFrameLayout mPtrFrameLayout) {
        if (null == mPtrFrameLayout) return;
        final ImageView view = (ImageView) _mActivity.getLayoutInflater().inflate(R.layout.layout_ptr_head, mPtrFrameLayout, false);
        Imageloader.loadGif(R.mipmap.carcarcar, view);
        mPtrFrameLayout.addPtrUIHandler(new PtrUIHandler() {
            private int mLoadTime = 0;

            @Override
            public void onUIReset(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
//                KLog.e("onUIPositionChange:" + ptrIndicator.getCurrentPosY());

            }
        });
        mPtrFrameLayout.setHeaderView(view);
        mPtrFrameLayout.setOffsetToKeepHeaderWhileLoading(DensityUtils.dp2px(_mActivity, 100));
        mPtrFrameLayout.setOffsetToRefresh(DensityUtils.dp2px(_mActivity, 100));
        mPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrameLayout.autoRefresh(true);
            }
        }, 100);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return isCanDoRefresh();
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                RefreshBegin();
                frame.refreshComplete();
            }
        });
    }

    //开始刷新的时候做什么操作，有刷新需求的fragment复写
    public void RefreshBegin() {

    }

    //判断是否能开始刷新
    public boolean isCanDoRefresh() {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
//        EventBus.getDefault().unregister(this);
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
    }

    public void dispatchTouchEvent(MotionEvent ev) {

    }

    public void UpdateUI(View view) {//解决所有页面   touch所有edittext以外view，自动隐藏键盘  通过decorview可以获取所有子view，循环判断设置touch事件
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View arg0, MotionEvent arg1) {
                    // TODO Auto-generated method stub
                    KeyBoardUtils.hideKeybord(arg0, getActivity());
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                UpdateUI(innerView);
            }
        }
    }

    /**
     * 支付宝
     */
    @SuppressLint("HandlerLeak")
    private Handler mAlyHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    AlyPayResult payResult = new AlyPayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Intent intent = new Intent(_mActivity, ContainerActiivty.class);
                        intent.putExtra("tofragment",Constants.PAYMENTSUCCESSFRAGMENT);
                        startActivity(intent);
                        Toast.makeText(_mActivity, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(_mActivity, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    /**
     * 阿里支付
     * @param orderString
     */
    public void toAliPay(final String orderString) {
        //拿到订单编号，开始支付
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(_mActivity);
                Map<String, String> result = alipay.payV2(orderString, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;

                mAlyHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    /**
     * 调起微信支付的方法
     **/
    public void toWXPay(final OrderJsonEntity entity) {
        final IWXAPI iwxapi = WXAPIFactory.createWXAPI(_mActivity, null); //初始化微信api
//        iwxapi.registerApp(entity.getAppid()); //注册appid  appid可以在开发平台获取

        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
                //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = entity.getAppid();
                request.partnerId = entity.getMch_id();
                request.prepayId = entity.getPrepay_id();
                request.packageValue = "Sign=WXPay";
                request.nonceStr = entity.getNonce_str();
                request.timeStamp = String.valueOf(System.currentTimeMillis());
                request.sign = entity.getSign();

                //签名
                LinkedHashMap<String, String> signParams = new LinkedHashMap<>();
                signParams.put("appid", request.appId);
                signParams.put("noncestr", request.nonceStr);
                signParams.put("package", request.packageValue);
                signParams.put("partnerid", request.partnerId);
                signParams.put("prepayid", request.prepayId);
                signParams.put("timestamp", request.timeStamp);
                request.sign = genPackageSign(signParams);
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * 调起微信APP支付，签名
     * 生成签名
     */
    private String genPackageSign(LinkedHashMap<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);


        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        return packageSign;
    }

    //在某个view下面所有界面显示阴影
    public void showDarkViewBelow(View view, WindowManager windowManager, View.OnClickListener onTouchListener) {
        mDarkView = new View(_mActivity);
        mDarkView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        mDarkView.setBackgroundColor(Color.parseColor("#a0000000"));
        WindowManager.LayoutParams p = new WindowManager.LayoutParams();
        p.gravity = Gravity.START | Gravity.TOP;
        p.width = DisplayUtils.getScreenWidth(_mActivity);
        p.height = DisplayUtils.getScreenHeight(_mActivity) - getDarkViewY(view);
        p.format = PixelFormat.TRANSLUCENT;
        p.flags = computeFlags(p.flags);
        p.type = WindowManager.LayoutParams.LAST_SUB_WINDOW;
        p.token = view.getWindowToken();
        p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED;
        p.x = 0;
        p.y = getDarkViewY(view);
        p.windowAnimations = R.anim.anim_slow_show;
        mDarkView.setOnClickListener(onTouchListener);
        windowManager.addView(mDarkView, p);
        mIsDarkInvoked = true;

    }

    private int getDarkViewY(View view) {
        int[] mWindowPosition = new int[2];
        view.getLocationInWindow(mWindowPosition);
        KLog.e(mWindowPosition[1] + view.getMeasuredHeight());
        return mWindowPosition[1] + view.getMeasuredHeight();

    }

    private int computeFlags(int curFlags) {
        curFlags = (//不影响window 范围之外的其他view的事件获取
//                WindowManager.LayoutParams.FLAG_SPLIT_TOUCH
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        );
        return curFlags;
    }

    //收回阴影view

    public void removeDarkView(WindowManager windowManager) {
        if (mDarkView != null && mIsDarkInvoked) {
            windowManager.removeViewImmediate(mDarkView);
            mIsDarkInvoked = false;
        }
    }


    public void call(String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _mActivity.startActivity(intent);
    }


    public boolean haslogin() {
        if (PreferenceUtils.contains(BaseApplication.mContext, "token")) {
            if (Constants.token.equals("")) {
                Constants.token = PreferenceUtils.getPrefString(_mActivity, "token", "");
                Constants.uid = PreferenceUtils.getPrefString(_mActivity, "uid", "");
            }
            return true;
        }
        return false;
    }

    private void getBaiduLocation(){
        locationCallBack=new BaiduLocationHelper.OnLocationCallBack() {
            @Override
            public void onLocationSuccess(BDLocation location) {
                fragmentBaiduListener.baiduLocationSuccess(location);
                baiduLocationHelper.stopLocation();
            }

            @Override
            public void onLocationFailure(int locType) {
                fragmentBaiduListener.baiduLocationFail(locType);
            }
        };
    }

    /**
     * 开始定位
     */
    public void FragmentLocationStart(){
        baiduLocationHelper=new BaiduLocationHelper();
        getBaiduLocation();
        registerLocation();
        baiduLocationHelper.startLocation();
    }

    private void registerLocation() {
        baiduLocationHelper.registerLocationCallback(locationCallBack);
    }

    public void unregisterLocation(){
        baiduLocationHelper.unRegisterLocationCallback(locationCallBack);
    }

    /**
     * 回调
     * @param fragmentBaiduListener
     */
    public void setFragmentBaidu(FragmentBaiduListener fragmentBaiduListener){
        this.fragmentBaiduListener=fragmentBaiduListener;
    }



}
