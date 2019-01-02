package com.carsuper.coahr.mvp.view.adapter;

import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Leehor
 * on 2018/10/17
 * on 13:37
 */
public class LinearManagerDecoration extends RecyclerView.ItemDecoration {
    private int left_Right,top_bottom,color;
    private  ColorDrawable colorDrawable;
    public LinearManagerDecoration(int left_Right, int top_Bottom, int mColor) {
        this.left_Right = left_Right;
        this.top_bottom = top_Bottom;
        if (mColor != 0) {
             colorDrawable = new ColorDrawable(mColor);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
