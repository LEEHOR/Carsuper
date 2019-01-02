package com.carsuper.coahr.wxapi;


import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.view.shoppingMall.PaymentSuccessFragment;
import com.gyf.barlibrary.ImmersionBar;
import com.socks.library.KLog;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import me.yokeyword.fragmentation.SupportActivity;


public class WXPayEntryActivity extends SupportActivity implements IWXAPIEventHandler {

    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.wx_app_id);
        api.handleIntent(getIntent(), this);
        //手机顶部状态栏颜色适配
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api = WXAPIFactory.createWXAPI(this, Constants.wx_app_id);
        api.handleIntent(intent, this);
    }


    @Override
    public void onResp(BaseResp resp) {

        KLog.d("微信",resp.errCode);

        if (resp.getType()==ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX){
            if (resp.errCode==0){  //分享成功
                Toast.makeText(this, "分享成功", Toast.LENGTH_LONG).show();
                setContentView(R.layout.activity_container);
            } else {                //分享失败
                Toast.makeText(this, "分享失败", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
                setContentView(R.layout.activity_container);
                loadRootFragment(R.id.fl_container, PaymentSuccessFragment.newInstance(Constants.LAST_PAYING_ORDERID,Constants.LAST_PAYING_PAGER));
            } else {
                Log.e("java", "onResp: " + resp.errCode);
                Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}