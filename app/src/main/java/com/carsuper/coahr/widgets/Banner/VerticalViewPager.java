package com.carsuper.coahr.widgets.Banner;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.socks.library.KLog;

import java.util.List;

/**
 * Author： hengzwd on 2018/6/12.
 * Email：hengzwdhengzwd@qq.com
 */
public class VerticalViewPager extends ViewGroup {

    //scroller实例
    private Scroller mScroller;

    //判定为拖动的最小距离
    private float mTouchSlop;

    //手指按下时的Y坐标
    private float mYdown;
    //触发acition_move的Y坐标
    private float mYmove;
    //上次触发事件时的Y坐标
    private float mYlast;

    //滚动的上边界
    private int mYupBorder;

    private List<View> viewList;


    public VerticalViewPager(Context context) {
        super(context);
        init(context);
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VerticalViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mScroller = new Scroller(context);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
//        mTouchSlop=ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        mTouchSlop = ViewConfigurationCompat.getScaledVerticalScrollFactor(viewConfiguration, context);
        //view 的  onTouchEvent()在clickble=false情况下ACTION_DOWN时 默认返回false，clickble=true就返回true，
        // 在ontouchEvent为false情况下，dispatchTouchEvent返回false，这种情况下，第一次ACTION返回false，不会触发之后的action
        this.setClickable(true);
    }


    public void setContentViewList(List<View> viewList){
        this.viewList=viewList;
        for (View view:viewList){
            this.addView(view);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                childView.layout(0, i * childView.getMeasuredHeight(), childView.getMeasuredWidth(), (i + 1) * childView.getMeasuredHeight());
            }
            mYupBorder = getChildAt(0).getTop();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                KLog.e("ACTION_DOWN");
                mYdown = ev.getRawY();//得到相对于屏幕的Y值
                mYlast = mYdown;
                break;
            case MotionEvent.ACTION_MOVE:
                KLog.e("ACTION_MOVE");

                mYmove = ev.getRawY();
                float diff = Math.abs(mYmove - mYdown);//是否达到触发滚动的界限
//                mYlast=mYmove;
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mYmove = event.getRawY();
                KLog.e("ACTION_MOVE");
                int scrolldy = (int) (mYlast - mYmove);
                if (getScrollY() + scrolldy < mYupBorder) {//滑动趋向于超过上边界
                    scrollTo(0, mYupBorder);
                    return true;
                } else {
                    scrollBy(0, scrolldy);
                    mYlast = mYmove;
                    return true;
                }
//                break;
            case MotionEvent.ACTION_UP:
                int targetIndex = (getScrollY() + getHeight() / 2) / getHeight();//应该滑动到哪一个子view
                int dy = targetIndex * getHeight() - getScrollY();  //得到需要矫正的scroll y值
                mScroller.startScroll(0, getScrollY(), 0, dy);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {//滚动尚未完成
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
