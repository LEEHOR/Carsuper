package com.carsuper.coahr.widgets.Banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.socks.library.KLog;

/**
 * Author： hengzwd on 2018/9/4.
 * Email：hengzwdhengzwd@qq.com
 */
public class ChildViewPager extends ViewPager {//解决与scrollview滑动冲突的子viewpager

    public ChildViewPager(@NonNull Context context) {
        super(context);
    }

    public ChildViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                super.onTouchEvent(ev);
                return true;
        }
        return super.onTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                return true;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
