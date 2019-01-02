package com.carsuper.coahr.mvp.view.adapter.citypicker.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.View;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean.JdataEntity.CityListEntity.CityEntity;

import java.util.List;

public class CitySectionItemDecoration extends RecyclerView.ItemDecoration {
    private List<CityEntity> mData;
    private Paint mBgPaint;
    private TextPaint mTextPaint;
    private Rect mBounds;

    private int mSectionHeight;
    private int mBgColor;
    private int mTextColor;
    private int mTextSize;

    public CitySectionItemDecoration(Context context, List<CityEntity> data) {
        this.mData = data;
        TypedValue typedValue = new TypedValue();

        context.getTheme().resolveAttribute(R.attr.cpSectionBackground, typedValue, true);
        mBgColor = context.getResources().getColor(typedValue.resourceId);

        context.getTheme().resolveAttribute(R.attr.cpSectionHeight, typedValue, true);
        mSectionHeight = context.getResources().getDimensionPixelSize(typedValue.resourceId);

        context.getTheme().resolveAttribute(R.attr.cpSectionTextSize, typedValue, true);
        mTextSize = context.getResources().getDimensionPixelSize(typedValue.resourceId);

        context.getTheme().resolveAttribute(R.attr.cpSectionTextColor, typedValue, true);
        mTextColor = context.getResources().getColor(typedValue.resourceId);

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(mBgColor);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);

        mBounds = new Rect();
    }

    public void setData(List<CityEntity> data) {
        this.mData = data;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int position = params.getViewLayoutPosition();
            if (mData != null && !mData.isEmpty() && position <= mData.size() - 1 && position > -1) {
                if (position == 0) {
                    drawSection(c, left, right, child, params, position);
                } else {
                    if (null != mData.get(position).getC_code()
                            && !mData.get(position).getC_code().equals(mData.get(position - 1).getC_code())) {
                        drawSection(c, left, right, child, params, position);
                    }
                }
            }
        }
    }

    private void drawSection(Canvas c, int left, int right, View child,
                             RecyclerView.LayoutParams params, int position) {
        c.drawRect(left,
                child.getTop() - params.topMargin - mSectionHeight,
                right,
                child.getTop() - params.topMargin, mBgPaint);
        mTextPaint.getTextBounds(mData.get(position).getC_code(),
                0,
                mData.get(position).getC_code().length(),
                mBounds);
        c.drawText(mData.get(position).getC_code(),
                child.getPaddingLeft(),
                child.getTop() - params.topMargin - (mSectionHeight / 2 - mBounds.height() / 2),
                mTextPaint);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int pos = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        if (pos < 0) return;
        if (mData == null || mData.isEmpty()) return;
        String section = mData.get(pos).getC_code();
        View child = parent.findViewHolderForLayoutPosition(pos).itemView;

        boolean flag = false;
        if ((pos + 1) < mData.size()) {//不是处于最后一个position
            if (null != section && !section.equals(mData.get(pos + 1).getC_code())) {//标题不为空且跟下一个标题 不同
                if (child.getHeight() + child.getTop() < mSectionHeight) {//item的底部Y值小于装饰的高度，表现为被装饰覆盖，且与下一个装饰相撞
                    c.save();//保存当前画布状态
                    flag = true;
                    //将画布向Y轴负方向移动，看上去被下面的装饰给撞上去了（为什么你这么优秀）
                    c.translate(0, child.getHeight() + child.getTop() - mSectionHeight);
                }
            }
        }

        //如果上面的的if判断中的代码没走，则在parent顶部一直绘制相同的精致装饰，如果走了判断，画布一直在向上移动，同样的代码画出来的矩形装饰表现得一直往上移动（一举两得，这么优秀的吗）
        c.drawRect(parent.getPaddingLeft(),
                parent.getPaddingTop(),
                parent.getRight() - parent.getPaddingRight(),
                parent.getPaddingTop() + mSectionHeight, mBgPaint);
        mTextPaint.getTextBounds(section, 0, section.length(), mBounds);
        c.drawText(section,
                child.getPaddingLeft(),
                parent.getPaddingTop() + mSectionHeight - (mSectionHeight / 2 - mBounds.height() / 2),
                mTextPaint);
        if (flag)//走了上面的if判断语句中的代码
            c.restore();//画布回归原始状态（就是没有向y轴方向移动，处于（0.0）坐标），下一次走ondrawover（），上面的画布便宜判断同样有效（啥也不说了，写的真好）
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (mData != null && !mData.isEmpty() && position <= mData.size() - 1 && position > -1) {
            if (position == 0) {
                outRect.set(0, mSectionHeight, 0, 0);
            } else {
                if (null != mData.get(position).getC_code()
                        && !mData.get(position).getC_code().equals(mData.get(position - 1).getC_code())) {
                    outRect.set(0, mSectionHeight, 0, 0);
                }
            }
        }
    }

}
