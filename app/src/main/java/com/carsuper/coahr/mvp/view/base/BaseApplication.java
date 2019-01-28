package com.carsuper.coahr.mvp.view.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
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

    public interface MsgDisplayListener {
        void CODE_DOWNLOAD_SUCCESS(String Info,int HandlePatchVersion );
        void CODE_LOAD_RELAUNCH(String Info,int HandlePatchVersion);
        void CODE_LOAD_MFITEM(String Info,int HandlePatchVersion);
        void Other(int code,String Info,int HandlePatchVersion);
    }
    private static MsgDisplayListener listeners;
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
      /*  SophixManager.getInstance().setContext(this)
                .setAppVersion(getAppVersion())
                .setAesKey(null)
                .setSecretMetaData("25263471-1","cf9f85bcca87c81018166bdbc4e50760","MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCEaP5AszlMSCAbTkAYYyl33caMb6+RS/WyIWIzFmbnphVcajrKU8GqAwmZ3ZT8GosT/PWaHwqeGrjDgiHZUkQp8sCtLbRcf+yJ2MV6lL4gAcKmbgAzCUDSgO2ZFAjzwL79Jl7vALzrwsSHkIJhW5lcTMEs1Gn15F3bN/w7N4/u9EsRr1Ud+YD0blgkNBlpMSeoiQzNgXSxfpfXf2Oh9JkhB+QmXgZz1tZz+44ZDeiThPVH8VBMCzWTLwoMEeg4O6NObhRs6pBYGWB47YE7BxkS+suPRH3zv4haeM7EftU5DqIkzOfdGVSjKTTMmHy7nmt4Q3h0wN+ZL6LiDQJZxSHVAgMBAAECggEAegobbZ7CzOAdV42D3fZnnic2fPHjq9OLHSdPzy5yokedoJP4o8sW0JAEJWDplIMSdNXNuMOrkZWnfetPL3J3iQJJPFTHzAnMBjFZsmWt5uo4chUo8eExcdOLKnAg4Cli3wiirz7K4bGeWiSDRyCZ9Ux5L8yH7S8Sop6zSBEqBF6bD23K9RVMgTb0xCSNJUXPx850H963JnefATFXeW9FuLIbp4Z17d4dkyYy4gdl55QG2Ovi07xqJnp72F9njFT3PM/46fkN9KjeCo6h4Dq4gcHbnofRz3u/421HTSdi/UGPpdXyMZatXvlvm28WWYH0+xwTJ1wb6dKqXhpDgnREgQKBgQD3bnq0oqoHjqVx2BU+G1tWipfVfaFfrCBU9uoC86KGtnMCX+IzEjhSwmbTBwZUNBU+x7XnP1UMMpPKHdtLqnVT7p1QKPRPgO98IYIfEbq20vZAumMJEN1bv0c/aNNebc9wmnDXLZqZyE/50zcCajklEBnurINQTT81mURzpuljWQKBgQCI/tSQVKhZe3gld1xfNDDDPjGEa081xKpn3T4NalRQMQL0AmKLGCnq2Cp2t4cDD2U1gVKI3wLDFy6xYbJzNS245WvxVX/bcLDOPOLLj1mah8jhbGNCtbLLa8FoUrUcAq3jv3cSBtkBqJbiXBEh23BM8i9pQLD1aRHSkoYyy8eO3QKBgQDA/k17g703s83cJH09Oj1eHTAc96bdZva5DvasYriMdtbyLTPUPgvskXF+fGY1W4KOu1tpCf8eSnz4E52vQT/Ovbm6gpc9RAaIPeKutTd7zsUA6+C/e4YNP/8kuHDVoLVC0VPeotWJybKH3HV1zsDP5eU0Qx8RjY8WEXxQ8Qnt0QKBgFZJpBtwp0UNZuS6ZL02g0xG3SuZ559nSyn2iYIrqgWEiWamJemUg7p/A2gZOvnrdYUY+qJpTTUSYf+qGJtGMJFYOAAfKVRPi52rbfJnTTnvMtEB0xeOmJpWOdD630ZQTbS4wUfidkb8KGVVNhlMPVTJ6qDx9UPuNSKb1dkiz4QlAoGASOJyEyhvqu1ktXlTTUJQsyipUbechzBlYnVJhOPF6XuCPmQr/vgy3vHQxF5vYlUCrnnu4ZKB5kKzeirijVMmiPc0gGopfSMrnWs3tZ2WBtOCmhRifvlIcsQh/wd4CmGT/nGYzdLH4dZ3TeJmking2mYJzTHpAT/eApGVlTXoR3k=")
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {

                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_DOWNLOAD_SUCCESS) {
                            // 表明补丁加载成功
                            if (listeners != null) {
                                listeners.CODE_DOWNLOAD_SUCCESS(info,handlePatchVersion);
                            }
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                            if (listeners != null) {
                                listeners.CODE_LOAD_RELAUNCH(info,handlePatchVersion);
                            }
                        } else if (code == PatchStatus.CODE_LOAD_MFITEM) {////补丁SOPHIX.MF文件解析异常
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            //SophixManager.getInstance().cleanPatches();
                            if (listeners != null) {
                                listeners.CODE_LOAD_MFITEM(info,handlePatchVersion);
                            }
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                            if (listeners != null) {
                                listeners.Other(code,info,handlePatchVersion);
                            }
                        }
                    }
                }).initialize();*/

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

    public static void setListener(MsgDisplayListener listener) {
        listeners = listener;
    }
}
