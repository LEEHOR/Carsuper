package com.carsuper.coahr.widgets.radioGroup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Author： hengzwd on 2018/6/28.
 * Email：hengzwdhengzwd@qq.com
 */
public class CustomRadioGroup extends LinearLayout {
    //第一级子view个数
    private int count = 0;

    //被选中的子view 颜色

    private int mSelectColor = Color.RED;
    //没有 被选中的子view 颜色

    private int unelectColor = Color.GRAY;
    //被选中了第几个
    private int mSelected = 0;

    private OnRadioChangeLisener onRadioChangeLisener;

    public CustomRadioGroup(Context context) {
        this(context, null);
    }

    public CustomRadioGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRadioGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                count = getChildCount();
                if (count > 0) {
                    for (int i = 0; i < count; i++) {
                        final int finalI = i;
                        getChildAt(i).setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mSelected!= finalI) {
                                    onRadioChangeLisener.Onchange(CustomRadioGroup.this.getChildAt(finalI),finalI);
                                    mSelected=finalI;
                                }
                            }
                        });
                    }
                }
            }
        },200);

    }


    public void setOnRadioChangeLisener(OnRadioChangeLisener onRadioChangeLisener) {
        this.onRadioChangeLisener = onRadioChangeLisener;
        this.onRadioChangeLisener.Onchange(CustomRadioGroup.this.getChildAt(0),0);
    }

    public interface OnRadioChangeLisener {
        void Onchange(View changeTo, int positon);
    }
}
