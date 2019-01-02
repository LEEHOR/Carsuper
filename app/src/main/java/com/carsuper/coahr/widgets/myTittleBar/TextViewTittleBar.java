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
 * Author： hengzwd on 2018/7/10.
 * Email：hengzwdhengzwd@qq.com
 */
public class TextViewTittleBar extends RelativeLayout {

    private int inflateView;
    private TextView tvContent;
    private EditText etSearch;
    private Context context;
    private FrameLayout.LayoutParams layoutParams;
    private View view;

    public TextViewTittleBar(Context context) {
        super(context);
    }

    public TextViewTittleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewTittleBar(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context,attrs,defStyleAttr);
        this.context=context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextViewTittleBar, 0, 0);
//        inflateView=a.getResourceId(R.styleable.NormalTittleBar_layout_inflate,R.layout.nomal_tittle_bar);
        String tittle = a.getString(R.styleable.TextViewTittleBar_tittle);
        a.recycle();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        view=  inflater.inflate(R.layout.textview_tittle_bar, this, true);
        getTvTittle().setText(tittle);
    }


    /**
     * 获取标题
     */

    public TextView getTvTittle()
    {
        return (TextView) view.findViewById(R.id.tv_center_title);
    }

    public TextView getTvTittle(int resId)
    {
        return (TextView) view.findViewById(resId);
    }




    public ImageView getLeftIcon()
    {
        return (ImageView) view.findViewById(R.id.iv_left_image);
    }

    public ImageView getLeftIcon(int resId)
    {
        return (ImageView) view.findViewById(resId);
    }



    public TextView getRightText()
    {
        return (TextView) view.findViewById(R.id.tv_right_text);
    }

    public TextView getRightText(int resId)
    {
        return (TextView) view.findViewById(resId);
    }

    private int dip2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
