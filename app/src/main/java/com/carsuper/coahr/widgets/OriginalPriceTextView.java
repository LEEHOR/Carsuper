package com.carsuper.coahr.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.carsuper.coahr.R;
import com.socks.library.KLog;


/**
 * Author： hengzwd on 2018/6/26.
 * Email：hengzwdhengzwd@qq.com
 */
public class OriginalPriceTextView extends AppCompatTextView {
    private Paint mPaint;



    public OriginalPriceTextView(Context context) {
        this(context,null);
    }

    public OriginalPriceTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,android.R.attr.textViewStyle);
    }

    public OriginalPriceTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
//        mPaint=new Paint();
//        mPaint.setColor(getCurrentTextColor());
    }


//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        int width = getMeasuredWidth();
//        int height = getMeasuredHeight();
//        KLog.e("width:"+width+"height:"+height);
//        canvas.drawLine(0,(float) (height*0.5),(float) width,(float) (height*0.5),mPaint);
//        canvas.drawLine(0,20,50, 20,mPaint);
//
//    }
}
