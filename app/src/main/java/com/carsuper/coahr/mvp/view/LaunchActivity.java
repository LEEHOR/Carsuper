package com.carsuper.coahr.mvp.view;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.view.base.BaseActivity;
import com.carsuper.coahr.mvp.view.base.BaseSupportActivity;
import com.carsuper.coahr.umeng.RecieveUmengPushService;
import com.carsuper.coahr.utils.NavigationBarUtils;
import com.carsuper.coahr.utils.Permission.OnRequestPermissionListener;
import com.carsuper.coahr.utils.Permission.RequestPermissionUtils;
import com.socks.library.KLog;

import java.util.List;

/**
 * Author： hengzwd on 2018/6/5.
 * Email：hengzwdhengzwd@qq.com
 */
public class LaunchActivity extends BaseSupportActivity {

    private long time;
    private static final long WAIT_TIME = 2 * 1000;
    @Override
    public int binLayout() {
        return R.layout.activity_launch;
    }

    @Override
    public void initView() {
        //启动服务
        Intent intent = new Intent(this, RecieveUmengPushService.class);
        startService(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (NavigationBarUtils.hasNavigationBarFun(this)){
            if (NavigationBarUtils.isNavigationBarShow(this)){
                NavigationBarUtils.hideBottomUIMenu(this);
            }
        }
    }

    @Override
    public void initData() {
        time = System.currentTimeMillis();
        noAdJump();
    }

    private void noAdJump() {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... Void) {
                long currentTime = System.currentTimeMillis();
                long distanceTime = currentTime - time;
                if (distanceTime < WAIT_TIME) {
                    try {
                        Thread.sleep(WAIT_TIME - distanceTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                jumpWhenCanClick();
            }
        }.execute();
    }

    private void jumpWhenCanClick() {
        Intent intent;
        intent = new Intent(LaunchActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
