package com.carsuper.coahr.utils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * Created by Leehor
 * on 2018/10/20
 * on 0:12
 * 重新计算试图高度
 */
public class ComputeUsableHeight {

    /**
     * 关联要监听的视图
     *
     * @param viewObserving
     */
    public static void assistActivity(View viewObserving) {
        new ComputeUsableHeight(viewObserving);
    }

    private View mViewObserved;//被监听的视图
    private int usableHeightPrevious;//视图变化前的可用高度
    private ViewGroup.LayoutParams frameLayoutParams;

    private ComputeUsableHeight(View viewObserving) {
        mViewObserved = viewObserving;
        //给View添加全局的布局监听器
        mViewObserved.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                resetLayoutByUsableHeight(computeUsableHeights());
            }
        });
        frameLayoutParams = mViewObserved.getLayoutParams();
    }

    private void resetLayoutByUsableHeight(int usableHeightNow) {
        //比较布局变化前后的View的可用高度
        if (usableHeightNow != usableHeightPrevious) {
            //如果两次高度不一致
            //将当前的View的可用高度设置成View的实际高度
            frameLayoutParams.height = usableHeightNow;
            mViewObserved.requestLayout();//请求重新布局
            usableHeightPrevious = usableHeightNow;
        }
    }

    /**
     * 计算视图可视高度
     *
     * @return
     */
    private int computeUsableHeights() {
        Rect r = new Rect();
        mViewObserved.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);
    }

}
