package com.carsuper.coahr.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.socks.library.KLog;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author： hengzwd on 2018/9/4.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyPtrFrameLayout extends PtrFrameLayout {


    public MyPtrFrameLayout(Context context) {
        super(context);
    }

    public MyPtrFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    float x = 0.0f, y = 0.0f;


    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = e.getX();
                y = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(e.getX() - x) > Math.abs(e.getY() - y)) {//ptr 控件action_move无法持续传递到子view，所以在这里主动传递
                    getChildAt(0).dispatchTouchEvent(e);
                   return false;
                }
                break;
        }
        return super.dispatchTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
