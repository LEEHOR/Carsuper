package com.carsuper.coahr.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

import com.socks.library.KLog;

/**
 * Author： hengzwd on 2018/7/31.
 * Email：hengzwdhengzwd@qq.com
 */
public class DispathKeyEventRlLayout extends RelativeLayout {//由于通过WM添加view的时候，回退键无法回调onbackpress，所以通过这个view来获取回退事件
    OnKeyListener mOnKeyListener = null;

    public DispathKeyEventRlLayout(Context context) {
        super(context);
    }

    public DispathKeyEventRlLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispathKeyEventRlLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
// TODO Auto-generated method stub
        KLog.e("dispatchKeyEvent " + event.getKeyCode());
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mOnKeyListener != null) {
                mOnKeyListener.onKey(this, KeyEvent.KEYCODE_BACK, event);
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    public void setOnKeyListener(OnKeyListener l) {
// TODO Auto-generated method stub
        this.mOnKeyListener = l;
    }
}
