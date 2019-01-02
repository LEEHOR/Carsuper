package com.carsuper.coahr.umeng;

import android.content.Context;
import android.content.Intent;

import com.socks.library.KLog;
import com.umeng.message.UmengMessageService;

import org.android.agoo.common.AgooConstants;

/**
 * Created by mitic_xue on 16/10/26.
 */
public class UmengNotificationService extends UmengMessageService {
    @Override
    public void onMessage(Context context, Intent intent) {
        KLog.d("UmengNotificationService", "onMessage");
        String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
        KLog.d("通知",message);
        Intent intent1 = new Intent();
        intent1.setClass(context, MyNotificationService.class);
        intent1.putExtra("UmengMsg", message);
        context.startService(intent1);
    }
}
