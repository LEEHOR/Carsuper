package com.carsuper.coahr.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.DisplayUtils;
import com.socks.library.KLog;

/**
 * Author： hengzwd on 2018/7/31.
 * Email：hengzwdhengzwd@qq.com
 */
public class TipView {


    protected Builder builder;
    WindowManager.LayoutParams p;
    protected int x;
    protected int y;

    private boolean isContentShow = false;

    protected TipView(Builder builder) {
            this.builder = builder;
    }


    public void show() {
        builder.content.setBackgroundColor(builder.mcontext.getResources().getColor(builder.resourseColor));
        builder.content.setAlpha(builder.malpha);
        p = new WindowManager.LayoutParams();
        p.gravity = Gravity.START | Gravity.TOP;
        p.width = DisplayUtils.getScreenWidth(builder.mcontext)- DensityUtils.dp2px(builder.mcontext,25.0f);
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        p.format = PixelFormat.TRANSLUCENT;
        p.flags = computeFlags(p.flags);
        p.type = WindowManager.LayoutParams.LAST_SUB_WINDOW;
        p.token = builder.anchor.getWindowToken();
        p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED;
        x=  DensityUtils.dp2px(builder.mcontext,12.5f);
        y= getViewXY(builder.anchor)[1]-DensityUtils.dp2px(builder.mcontext,30.0f);
        p.x = x;
        p.y = y;

        p.windowAnimations = R.anim.anim_slow_show;
        builder.windowManager.addView(builder.content, p);
        isContentShow=true;
        builder.content.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    builder.mcontext.onBackPressed();
                }
                KLog.e("onkey;"+event.getAction());
                return false;
            }
        });
    }

    public void scrllTo(int x,int y){
        this.x = x;
        this.y= y;
        p.x = x;
        p.y = y;
        builder.windowManager.updateViewLayout(builder.content,p);

    }
    public int getContentX(){
        return  x;

    }
    public int getContentY(){
        return  y;
    }

    public View getContentView(){
        return builder.content;
    }
    public  boolean isShowing(){
        return isContentShow;
    }
    public void dismiss() {
        if (builder.content != null && isContentShow) {
            builder.windowManager.removeViewImmediate(builder.content);
            isContentShow = false;
        }
    }

    private int computeFlags(int curFlags) {
        curFlags = (//不影响window 范围之外的其他view的事件获取
                WindowManager.LayoutParams.FLAG_SPLIT_TOUCH|
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        |WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                |WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        return curFlags;
    }

    private int[] getViewXY(View view) {
        int[] mWindowPosition = new int[2];
        view.getLocationInWindow(mWindowPosition);
        KLog.e(mWindowPosition[1] + view.getMeasuredHeight());
        return mWindowPosition;

    }


    public static class Builder {

        protected Activity mcontext;
        protected WindowManager windowManager;

        protected View content;


        protected float malpha;

        protected int resourseColor;

        protected View anchor;

        public Builder(Activity context) {
            mcontext = context;
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }

        public Builder backgroundAlpha(float alpha) {
            malpha = alpha;
            return this;
        }

        public Builder backgroundColorResourse(int res) {
            resourseColor = res;
            return this;
        }

        public Builder contentView(View view) {
            content = view;
            return this;
        }

        public Builder anchorView(View view) {
            anchor = view;
            return this;
        }

        public TipView build() {
            return new TipView(this);
        }

    }
}
