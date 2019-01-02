package com.carsuper.coahr.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;

/**
 * Created by Leehor
 * on 2018/10/30
 * on 15:12
 */
public class SideBar extends View {
    public interface OnTouchingLetterChangedListener {

        void onHit(String letter);

        void onCancel();
    }


    private OnTouchingLetterChangedListener touchingLetterChangedListener;

    private Paint paint;

    private boolean hit;

    private int type = 1;

    private final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z", "#"};

    private static final String[] DEFAULT_INDEX_ITEMS = {"当前", "热门", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private final int DEFAULT_WIDTH;

    public void setType(int type) {
        this.type = type;
    }

    public SideBar(Context context) {
        this(context, null);
    }

    public SideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.parseColor("#565656"));
        DEFAULT_WIDTH = dpToPx(context, 24);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getWidthSize(widthMeasureSpec), getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    private int getWidthSize(int widthMeasureSpec) {
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        switch (widthMode) {
            case View.MeasureSpec.AT_MOST: {
                if (widthSize >= DEFAULT_WIDTH) {
                    return DEFAULT_WIDTH;
                } else {
                    return widthSize;
                }
            }
            case View.MeasureSpec.EXACTLY: {
                return widthSize;
            }
            case View.MeasureSpec.UNSPECIFIED:
            default:
                return DEFAULT_WIDTH;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                hit = true;
                onHit(event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                onHit(event.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                hit = false;
                if (touchingLetterChangedListener != null) {
                    touchingLetterChangedListener.onCancel();
                }
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (hit) {
            //字母索引条背景色
            canvas.drawColor(Color.parseColor("#bababa"));
        }
        if (type == 1) {
            float letterHeight = ((float) getHeight()) / letters.length;
            float width = getWidth();
            float textSize = letterHeight * 5 / 7;
            paint.setTextSize(textSize);

            for (int i = 0; i < letters.length; i++) {
                canvas.drawText(letters[i], width / 2, letterHeight * i + textSize, paint);
            }
        } else if (type == 2) {
            float letterHeight = ((float) getHeight()) / DEFAULT_INDEX_ITEMS.length;
            float width = getWidth();
            float textSize = letterHeight * 5 / 7;
            paint.setTextSize(textSize);

            for (int i = 0; i < DEFAULT_INDEX_ITEMS.length; i++) {
                canvas.drawText(DEFAULT_INDEX_ITEMS[i], width / 2, letterHeight * i + textSize, paint);
            }
        }
    }

    private void onHit(float offset) {
        if (hit && touchingLetterChangedListener != null) {
            if (type == 1) {
                int index = (int) (offset / getHeight() * letters.length);
                index = Math.max(index, 0);
                index = Math.min(index, letters.length - 1);
                touchingLetterChangedListener.onHit(letters[index]);
            } else if (type == 2) {
                int index = (int) (offset / getHeight() * DEFAULT_INDEX_ITEMS.length);
                index = Math.max(index, 0);
                index = Math.min(index, letters.length - 1);
                touchingLetterChangedListener.onHit(DEFAULT_INDEX_ITEMS[index]);
            }
        }
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.touchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    private int dpToPx(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
