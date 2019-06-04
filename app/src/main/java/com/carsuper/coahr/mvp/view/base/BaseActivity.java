package com.carsuper.coahr.mvp.view.base;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.NavigationBarUtils;
import com.carsuper.coahr.utils.Permission.OnRequestPermissionListener;
import com.carsuper.coahr.utils.Permission.RequestPermissionUtils;
import com.carsuper.coahr.utils.ScreenUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.carsuper.coahr.R;
import com.carsuper.coahr.common.ActivityManager;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.utils.KeyBoardUtils;
import com.umeng.message.PushAgent;

import java.util.List;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author： hengzwd on 2018/6/5.
 * Email：hengzwdhengzwd@qq.com
 */
public abstract class BaseActivity<P extends BaseContract.Presenter> extends SupportActivity {

    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 增量更新修复bug
     */
    private AlertDialog mDialog;
    private CountDownTimer timer;
    private   Dialog dialog;

    private Unbinder unbinder;

    public abstract P getPresenter();

    public abstract int binLayout();

    public abstract void initView();

    public abstract void initData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);  //一处声明，处处依赖注入
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(BaseApplication.mContext).onAppStart();
//        mPresenter = createPresenter();
        setContentView(binLayout());
        KeyBoardUtils.UpdateUI(getWindow().getDecorView().getRootView(), this);
        //手机顶部状态栏颜色适配
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
        unbinder = ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        initView();
        initData();

        //设置虚拟键
        if (NavigationBarUtils.hasNavigationBarFun(this)){
            if (NavigationBarUtils.isNavigationBarShow(this)){
                NavigationBarUtils.setNavigationColor(this,getResources().getColor(R.color.material_white));
            }
        }

        /*Home键是系统事件，只能通过广播监听*/
        IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(receiver, filter);
        getLocationPermission();
       // CreateDialog();
        int screenWidth = ScreenUtils.getScreenWidth(BaseApplication.mContext);
        Constants.screenWidth=screenWidth;
    }



    public void showLoading() {
        //创建Dialog并传递style文件
        if (dialog == null) {
//            dialog = new AlertDialog.Builder(this,R.style.dialog_loading).create();
//            dialog.setContentView(R.layout.dialog_loading_layout);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable());//解决5.0以上阴影问题
//            dialog.show();
            dialog = new Dialog(this, R.style.dialog_loading);
            dialog.setContentView(R.layout.dialog_loading_layout);
        }
        // 设置它的ContentView
        if (!dialog.isShowing()) {
            dialog.show();//显示dialog
        }
    }


    public void dismissLoading() {
        if (dialog!=null&&dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (!(this instanceof LaunchActivity)) {
//            if (!PermissionUtils.isPermissionsGranted(this, new int[]{
//                    PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE,
//                    PermissionUtils.CODE_READ_EXTERNAL_STORAGE})) {
//                Intent intent = new Intent(this, LaunchActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                this.startActivity(intent);
//                ActivityManager.getInstance().appExit();
//                KLog.e("onResume","00000000000");
//            }
//        }
    }

    public void showError(@Nullable Throwable e) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        ActivityManager.getInstance().removeActivity(this);
        ImmersionBar.with(this).destroy();
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
    }



    BroadcastReceiver receiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    private void CreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.launch_logo);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Constants.isKill = false;
                setTimes().cancel();
                mDialog.dismiss();
            }
        });
        builder.setPositiveButton("重启", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setTimes().onFinish();
            }
        });
        mDialog = builder.create();

    }

    private void showDialog(String msg, int Version) {
        mDialog.setTitle("卡速宝" + "修复补丁");
        mDialog.setMessage("当前版本：" + getAppVersion() + "\n" + "\t\t" + "补丁批号" + Version + "\n" + "\t\t" + msg);
        mDialog.show();
        setTimes().start();
    }

    private CountDownTimer setTimes() {
        //
        if (timer == null) {
            timer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long l) {
                    int time = (int) (l / 1000);
                    if (mDialog != null) {
                        mDialog.getButton(DialogInterface.BUTTON_POSITIVE).setText("重启" + "(" + time + ")");
                    }
                }

                @Override
                public void onFinish() {
                    if (mDialog != null) {
                        // android.os.Process.killProcess(android.os.Process.myPid());
                        Log.d("热更新", "点击了确定");
                        Constants.isKill = true;
                        mDialog.dismiss();
                        timer.cancel();
                        android.os.Process.killProcess(android.os.Process.myPid());

                    }
                }
            };
        } else {
            return timer;
        }
        return timer;
    }

    private String getAppVersion() {
        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            appVersion = "1.0.0";
        }
        return appVersion;
    }

    /**
     * 动态获取存储权限
     */
    private void getLocationPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RequestPermissionUtils.requestPermission(BaseActivity.this, new OnRequestPermissionListener() {
                @Override
                public void PermissionSuccess(List<String> permissions) {
                }

                @Override
                public void PermissionFail(List<String> permissions) {
                    Toast.makeText(BaseActivity.this, "获取权限失败", Toast.LENGTH_LONG).show();
                }

                @Override
                public void PermissionHave() {

                }
            }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE);

        } else {
        }
    }
}
