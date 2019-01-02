package com.carsuper.coahr.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.socks.library.KLog;


/**
 * Author： hengzwd on 2018/9/3.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyNestedScrollView extends NestedScrollView {

    float  x=0.0f,y=0.0f;

    public MyNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public MyNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x=ev.getX();

                y=ev.getY();
                break;
            case MotionEvent.ACTION_MOVE://解决滑动方向不同造成的滑动冲突

                if (Math.abs(ev.getX()-x)>Math.abs(ev.getY()-y)) {

                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
