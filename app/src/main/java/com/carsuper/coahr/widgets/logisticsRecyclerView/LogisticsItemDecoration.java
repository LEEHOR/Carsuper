package com.carsuper.coahr.widgets.logisticsRecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.carsuper.coahr.R;

import kotlin.jvm.internal.PropertyReference0Impl;

/**
 * Author： hengzwd on 2018/6/1.
 * Email：hengzwdhengzwd@qq.com
 */
public class LogisticsItemDecoration extends RecyclerView.ItemDecoration {

    //笔
    private Paint mPaint;
    //直线的颜色
    private int mLineColor = Color.GRAY;
    //暗淡圆的颜色
    private int mHollowCircleColor = Color.GRAY;

    //直径
    private int diameter = 20;
    //直线的粗细
    private int mLineWidth = 1;
    //每个itemview离左边的距离
    private int distanceLeft ;

    //物流到达标志图

    private int logisticsIMG = R.mipmap.present;

    private Bitmap bitmap;



    public LogisticsItemDecoration(Context context){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        bitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(),logisticsIMG));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.left = diameter + 20;
        distanceLeft=diameter+20;
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i=0;i<childCount;i++){
            if (childCount!=1) {
                float topX = parent.getPaddingLeft()+distanceLeft/2;
                float topY;
                if (i==0) {
                     topY = parent.getChildAt(i).getTop()+parent.getChildAt(i).getPaddingTop();
                }else {
                    topY= parent.getChildAt(i).getTop();
                }
                float bottomX = parent.getPaddingLeft()+distanceLeft/2;
                float bottomY = parent.getChildAt(i).getBottom();
                mPaint.setColor(mLineColor);
                c.drawLine(topX,topY,bottomX,bottomY,mPaint);
            }

            if (i==0) {
                c.drawBitmap(bitmap,parent.getPaddingLeft()+distanceLeft/2-bitmap.getWidth()/2,parent.getChildAt(i).getTop()+parent.getChildAt(0).getPaddingTop(),mPaint);
            }else {
                float mRadius = diameter/2;
                float centerX=parent.getPaddingLeft()+distanceLeft/2;
                float centerY =parent.getChildAt(i).getTop()+parent.getChildAt(i).getPaddingTop()+diameter/2;
                mPaint.setColor(mHollowCircleColor);
                c.drawCircle(centerX,centerY,mRadius,mPaint);
            }
        }
    }
}
