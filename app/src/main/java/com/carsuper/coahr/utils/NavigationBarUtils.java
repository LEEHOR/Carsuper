package com.carsuper.coahr.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import java.lang.reflect.Method;

/**
 * Created by Leehor
 * on 2018/10/19
 * on 19:52
 */
public class NavigationBarUtils {
    /**
     * 是否有虚拟按键
     * @param activity
     * @return
     */
    public static boolean hasNavigationBarFun(Activity activity) {
        Resources rs = activity.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        boolean hasNavBarFun = false;
        if (id > 0) {
            hasNavBarFun = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String)m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavBarFun = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavBarFun = true;
            }
        } catch (Exception e) {
            hasNavBarFun = false;
        }
        return hasNavBarFun;
    }

    /**
     * 虚拟按键是否展示
     * @param activity
     * @return
     */
    public static boolean isNavigationBarShow(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        Display display = activity.getWindowManager().getDefaultDisplay();
        display.getMetrics(dm);
        int screenHeight = dm.heightPixels;

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealMetrics(realDisplayMetrics);
        } else {
            Class c;
            try {
                c = Class.forName("android.view.Display");
                Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
                method.invoke(display, realDisplayMetrics);
            } catch (Exception e) {
                realDisplayMetrics.setToDefaults();
                e.printStackTrace();
            }
        }

        int screenRealHeight = realDisplayMetrics.heightPixels;
        return (screenRealHeight - screenHeight) > 0;
    }

    /**
     * 隐藏虚拟键并且全屏
     * @param activity
     */
    public static void hideBottomUIMenu(Activity activity){
        if (Build.VERSION.SDK_INT > 16 && Build.VERSION.SDK_INT < 19) {
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    /**
     * 设置虚拟键颜色
     * @param activity
     * @param color
     */
    public static void setNavigationColor(Activity activity,int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setNavigationBarColor(color);
        }
    }

}
