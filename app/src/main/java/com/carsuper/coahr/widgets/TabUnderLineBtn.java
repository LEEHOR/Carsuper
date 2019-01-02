package com.carsuper.coahr.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class TabUnderLineBtn extends RelativeLayout  {
    /**
     * 下划线高度
     * 下划线宽度
     * 文字大小
     */
    private float lineHeight, lineWeight, textSize;
    /**
     * 选中时的颜色
     * 未选中时的颜色
     */
    private int unCheckedColor, checkedColor;
    /**
     * 是否被选中
     */
    private boolean isChecked;
    private String text;

    /**
     * 控件
     */
    private TextView textView;//文字
    private View view;//下划线
    private LayoutParams textViewParams, viewParams;

    public TabUnderLineBtn(Context context) {
        super(context);
    }

    public TabUnderLineBtn(Context context, AttributeSet attrs,int defStyleAttr) {
        super(context, attrs,defStyleAttr);
    }

    public TabUnderLineBtn(Context context, AttributeSet attrs) {
        super(context, attrs );
        /**
         * 获取自定义属性
         */
    }


}
