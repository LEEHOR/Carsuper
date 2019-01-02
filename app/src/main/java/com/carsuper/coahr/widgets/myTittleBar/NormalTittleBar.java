package com.carsuper.coahr.widgets.myTittleBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carsuper.coahr.R;


/**
 * Author： hengzwd on 2017/8/10.
 * Email：hengzwdhengzwd@qq.com
 */

public class NormalTittleBar extends RelativeLayout {


    private int inflateView;
    private TextView tvContent;
    private EditText etSearch;
    private Context context;
    private FrameLayout.LayoutParams layoutParams;
    private View view;

    public NormalTittleBar(Context context) {
        this(context, null);
    }

    public NormalTittleBar(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }


    public NormalTittleBar(Context context, AttributeSet attributeSet, int defstyleAttr)
    {
        super(context,attributeSet,defstyleAttr);
        this.context=context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.NormalTittleBar, 0, 0);
//        inflateView=a.getResourceId(R.styleable.NormalTittleBar_layout_inflate,R.layout.nomal_tittle_bar);
        String tittle = a.getString(R.styleable.NormalTittleBar_tittle);
        a.recycle();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        view=  inflater.inflate(R.layout.nomal_tittle_bar, this, true);
        getTvTittle().setText(tittle);
        getTvTittle().setTextSize(14);
    }


    /**
     * 获取标题
     */

    public TextView getTvTittle()
    {
        return (TextView) view.findViewById(R.id.tv_center);
    }

    public TextView getTvTittle(int resId)
    {
        return (TextView) view.findViewById(resId);
    }




    public ImageView getLeftIcon()
    {
        return (ImageView) view.findViewById(R.id.iv_left);
    }

    public ImageView getLeftIcon(int resId)
    {
        return (ImageView) view.findViewById(resId);
    }



    public TextView getRightTitle()
    {
        return (TextView) view.findViewById(R.id.iv_right);
    }

    public TextView getRightTitle(int resId)
    {
        return (TextView) view.findViewById(resId);
    }

    private int dip2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
