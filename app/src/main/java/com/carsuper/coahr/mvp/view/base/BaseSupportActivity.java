package com.carsuper.coahr.mvp.view.base;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.utils.NavigationBarUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.carsuper.coahr.R;
import com.carsuper.coahr.common.ActivityManager;
import com.carsuper.coahr.utils.KeyBoardUtils;
import com.umeng.message.PushAgent;



import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 *
 * 适用与没有构建p  与 m  层的activity来继承（只用来承装fragment）
 */
public abstract class BaseSupportActivity extends SupportActivity {

    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();


    /**
     * 增量更新修复bug
     */
    private AlertDialog mDialog;
    private CountDownTimer timer;
    private Unbinder unbinder;

    public abstract int binLayout();

    public abstract void initView();

    public abstract void initData();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);  //一处声明，处处依赖注入
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(BaseApplication.mContext).onAppStart();
        setContentView(binLayout());
        KeyBoardUtils.UpdateUI(getWindow().getDecorView().getRootView(), this);
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
        getWindow().setBackgroundDrawableResource(R.drawable.bg_fff_background);
        unbinder = ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        if (NavigationBarUtils.hasNavigationBarFun(this)){
            if (NavigationBarUtils.isNavigationBarShow(this)){
                NavigationBarUtils.setNavigationColor(this,getResources().getColor(R.color.material_white));
            }
        }
        initView();
        initData();

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        ActivityManager.getInstance().removeActivity(this);
        ImmersionBar.with(this).destroy();
    }
    private void CreateDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(BaseSupportActivity.this);
        builder.setIcon(R.mipmap.launch_logo);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Constants.isKill=false;
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
        mDialog= builder.create();

    }
    private void showDialog(String msg,int Version){
        mDialog.setTitle("卡速宝"+"修复补丁");
        mDialog.setMessage("当前版本："+getAppVersion()+"\n"+"\t\t"+"补丁批号"+Version+"\n"+"\t\t"+msg);
        mDialog.show();
        setTimes().start();
    }
    private CountDownTimer setTimes(){
        //
        if (timer == null) {
            timer = new CountDownTimer(10000,1000) {
                @Override
                public void onTick(long l) {
                    int time = (int) (l / 1000);
                    if (mDialog != null) {
                        mDialog.getButton(DialogInterface.BUTTON_POSITIVE).setText("重启" + "(" + time + ")");
                    }
                }

                @Override
                public void onFinish() {
                    if(mDialog!=null){
                        // android.os.Process.killProcess(android.os.Process.myPid());
                        Log.d("热更新","点击了确定");
                        Constants.isKill=true  ;
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
            appVersion= this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            appVersion = "1.0.0";
        }
        return  appVersion;
    }

}
