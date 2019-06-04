package com.carsuper.coahr.mvp.view.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.Fragment;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.carsuper.coahr.exception.AppExceptionHandler;
import com.carsuper.coahr.umeng.UmengNotificationService;
import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.tinypinyin.lexicons.android.cncity.CnCityDict;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.dagger.components.ApplicationComponent;
import com.carsuper.coahr.dagger.components.DaggerApplicationComponent;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.socks.library.KLog;

import com.tencent.smtt.sdk.QbSdk;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Author： hengzwd on 2017/8/2.
 * Email：hengzwdhengzwd@qq.com
 */

public class BaseApplication extends MultiDexApplication implements HasActivityInjector, HasSupportFragmentInjector {

    private ApplicationComponent applicationComponent;
    public static Context mContext;
    public static final String UPDATE_STATUS_ACTION = "com.cyht.wykc.action.UPDATE_STATUS";

    private String TAG = BaseApplication.class.getSimpleName();

    //内部封装map管理众多activity的component
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidActivityInjector;
    //内部封装map管理众多fragment的component
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidFragmentInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.create().inject(this);
        mContext = getApplicationContext();
        //二维码工具
         ZXingLibrary.initDisplayOpinion(this);
        MultiDex.install(this);
//         程序异常交由AppExceptionHandler来处理
     //   Thread.setDefaultUncaughtExceptionHandler(AppExceptionHandler.getInstance(this));
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        PlatformConfig.setWeixin("wx89f3b1477df1aa39", "b3ad27916ad0fa404f5d1478f3cc0bc2");
        UMConfigure.init(this,
                 UMConfigure.DEVICE_TYPE_PHONE, "593cc124db10a9ca7841fe214380c3f8");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
       // o1stghiyxsf2wh43s78u6bir3hthnyek
        UMConfigure.setLogEnabled(false);
        if (PreferenceUtils.contains(mContext, "token")) {
            Constants.token = PreferenceUtils.getPrefString(mContext, "token", "");
        }
        if (PreferenceUtils.contains(mContext, "phone")) {
            Constants.phone = PreferenceUtils.getPrefString(mContext, "phone", "");
        }
        //拼音字典配置初始化
        Pinyin.init(Pinyin.newConfig().with(CnCityDict.getInstance(mContext)));
        initX5WebView();
        SDKInitializer.initialize(getApplicationContext());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        initPush();

    }

    private void initPush() {

        PushAgent mPushAgent = PushAgent.getInstance(this);
        //sdk开启通知声音
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        /**
         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        /*UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                KLog.e("dealWithCustomAction");
                KLog.d("推送",msg.builder_id,msg.custom);
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };*/
        //使用自定义的NotificationHandler，来结合友盟统计处理消息通知，参考http://bbs.umeng.com/thread-11112-1-1.html
        //CustomNotificationHandler notificationClickHandler = new CustomNotificationHandler();
       //mPushAgent.setNotificationClickHandler(notificationClickHandler);
        //注册推送服务 每次调用register都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                KLog.d(TAG, "device token: " + deviceToken);
                Constants.devicestoken = deviceToken;
                PreferenceUtils.setPrefString(mContext, "devicetoken", Constants.devicestoken);
            }

            @Override
            public void onFailure(String s, String s1) {
                KLog.d(TAG, "register failed: " + s + " " + s1);
            }
        });
        PushAgent.getInstance(mContext).onAppStart();
        mPushAgent.setPushIntentServiceClass(UmengNotificationService.class);
    }

    /**
     * 初始化X5Web
     */
    private void initX5WebView(){
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {

            }
        });

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidActivityInjector;
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidFragmentInjector;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
    private String getAppVersion() {
        String appVersion;
        try {
            appVersion= this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            appVersion = "1.0.0";
        }
        return  appVersion;
    }

}
