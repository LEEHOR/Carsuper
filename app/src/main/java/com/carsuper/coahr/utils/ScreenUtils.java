package com.carsuper.coahr.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.carsuper.coahr.mvp.view.base.BaseApplication;

//获得屏幕相关的辅助类
public class ScreenUtils {
    private static OnInputActionListener listeners;
    private ScreenUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
    /**
     * @return 底部的虚拟栏的高度
     */
    public static int getBottomKeyboardHeight(Activity activity, Context context) {
        int screenHeight = getScreenHeight(context);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);//去除底部虚拟栏之后的metric
        int heightDifference = screenHeight - dm.heightPixels;
        return heightDifference;
    }
    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取状态栏高度——方法1
     */
    public static int getStatusBarHeight(Context context) {

        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }

        return statusBarHeight;
    }


    /**
     * 监听某个页面上输入法键盘打开动作
     *
     * @param view     要实现监听的页面上的任意一个view都可以
     * @param listener 监听接口
     */
    public static  void observeInputlayout(final View view, final Activity activity, OnInputActionListener listener) {
        listeners = listener;
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                observe(activity,view);
            }
        }, 500);
    }


    private static void observe(final Activity activity, View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    //当键盘弹出隐藏的时候会 调用此方法。
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        //获取当前界面可视部分
                        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                        //获取屏幕的高度
                        int screenHeight = activity.getWindow().getDecorView().getRootView().getHeight();
                        //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                        int heightDifference = screenHeight - r.bottom;
                        if (heightDifference > getBottomKeyboardHeight(activity,BaseApplication.mContext)) {//有些手机用的是底部虚拟键,所以要大于虚拟键的高度
                            listeners.onOpen();
                        } else {
                            listeners.onClose();
                        }

                    }

                });
    }

    public interface OnInputActionListener {
        void onOpen();

        void onClose();

    }
}
