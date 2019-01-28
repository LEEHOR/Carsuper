package com.carsuper.coahr.widgets.x5web;

import android.content.Context;
import android.content.res.Resources;

import com.carsuper.coahr.R;

/**
 * @author Leehor
 * 版本：
 * 创建日期：2019/1/28
 * 描述：
 */
public class ADFilterTool {
    /**
     * 屏蔽广告的NoAdWebViewClient类
     *
     * @param context
     * @param url
     * @return true 为广告链接，false 为正常连接
     */
    public static boolean hasAd(Context context, String url) {
        Resources res = context.getResources();
        String[] adUrls = res.getStringArray(R.array.adBlockUrl);
        for (String adUrl : adUrls) {
            if (url.contains(adUrl)) {
                return true;
            }
        }
        return false;
    }
}
