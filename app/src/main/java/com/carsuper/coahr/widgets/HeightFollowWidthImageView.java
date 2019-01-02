package com.carsuper.coahr.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Author： hengzwd on 2018/8/3.
 * Email：hengzwdhengzwd@qq.com
 */
public class HeightFollowWidthImageView extends android.support.v7.widget.AppCompatImageView {
    public HeightFollowWidthImageView(Context context) {
        super(context);
    }

    public HeightFollowWidthImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeightFollowWidthImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = this.getMeasuredWidth();
        setMeasuredDimension(w, w);
    }
}
