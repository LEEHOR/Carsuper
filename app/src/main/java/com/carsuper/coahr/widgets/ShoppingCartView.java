package com.carsuper.coahr.widgets;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import com.carsuper.coahr.utils.DisplayUtils;

/**
 * Author： hengzwd on 2018/6/1.
 * Email：hengzwdhengzwd@qq.com
 */

//购物车控件view
public class ShoppingCartView extends FloatingActionButton {

    //布局形成的view的坐标点X，Y
    private int mLayoutX;
    private int mLayoutY;

    private int realTimeX;
    private int realTimeY;

    private Context mContext;

    public ShoppingCartView(Context context) {
        this(context, null);
    }

    public ShoppingCartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShoppingCartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getLocation();
        mLayoutX = realTimeX;
        mLayoutY = realTimeY;
        mContext = context;
    }


    public void show() {
        if (realTimeX == mLayoutX && realTimeY == mLayoutY) {
            return;
        } else {
            getLocation();
            ObjectAnimator.ofFloat(this, "translationX", -(DisplayUtils.getScreenWidth(mContext) - mLayoutX));
        }
    }

    public void hide() {
        if (realTimeX == mLayoutX && realTimeY == mLayoutY) {
            getLocation();
            ObjectAnimator.ofFloat(this, "translationX", (DisplayUtils.getScreenWidth(mContext) - mLayoutX));
        }
        return;
    }


    private void getLocation() {
        int[] position = new int[2];
        this.getLocationInWindow(position);
        realTimeX = position[0];
        realTimeY = position[1];
    }
}
