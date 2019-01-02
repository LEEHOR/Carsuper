package com.carsuper.coahr.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carsuper.coahr.R;

import org.w3c.dom.Text;

/**
 * Author： hengzwd on 2018/7/24.
 * Email：hengzwdhengzwd@qq.com
 */
public class ConditionSelectView extends LinearLayout {
    //条件view个数
    private int itemCount = 0;

    //上一个展开的菜单位置
    private int lastShowPosition = 100;

    private boolean isShow = false;


    private int showIcon;

    private int hidenIcon;

    private onItemShowHidenListener onItemShowHidenListener;
    public ConditionSelectView(Context context) {
        this(context, null);
    }

    public ConditionSelectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConditionSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ConditionSelectView, defStyleAttr, 0);
//        itemCount = array.getInteger(R.styleable.ConditionSelectView_itemCount,0);
        showIcon = array.getResourceId(R.styleable.ConditionSelectView_showIcon, 0);
        hidenIcon = array.getResourceId(R.styleable.ConditionSelectView_hidenIcon, 0);
        array.recycle();
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                itemCount=ConditionSelectView.this.getChildCount();
                for (int i = 0; i < itemCount; i++) {
                    if (((ViewGroup)ConditionSelectView.this.getChildAt(i)).getChildAt(0) instanceof TextView) {
                        Drawable drawable =getResources().getDrawable(hidenIcon);
                        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                        ((TextView)((ViewGroup)ConditionSelectView.this.getChildAt(i)).getChildAt(0)).setCompoundDrawables(null,null,drawable,null);
                        final int finalI = i;
                        ((ViewGroup)ConditionSelectView.this.getChildAt(i)).getChildAt(0).setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isShow&&lastShowPosition== finalI) {//正在展开，而且点击的上次展开的position
                                    hidenPosition(finalI);
                                    isShow = false;
                                    onItemShowHidenListener.onAllHiden();
                                }else {
                                    showPosition(finalI);
                                    isShow=true;
                                    onItemShowHidenListener.onItemShow(finalI);
                                }
                            }
                        });
                    }else {
                        try {
                            throw  new Exception("select  item  must  be   textview");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },500);

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }

    public void showPosition(int position){
        Drawable drawable =getResources().getDrawable(showIcon);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        ((TextView)((ViewGroup)this.getChildAt(position)).getChildAt(0)).setCompoundDrawables(null,null,drawable,null);
        if (lastShowPosition!=position) {
            hidenPosition(lastShowPosition);
            lastShowPosition = position;
        }
    }


    public void hidenPosition(int position){
        if (isShow) {
            Drawable drawable =getResources().getDrawable(hidenIcon);
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            ((TextView)((ViewGroup)this.getChildAt(position)).getChildAt(0)).setCompoundDrawables(null,null,drawable,null);
        }
    }

    public void  hidenAll(){
        if (isShow) {
            Drawable drawable =getResources().getDrawable(hidenIcon);
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            ((TextView)((ViewGroup)this.getChildAt(lastShowPosition)).getChildAt(0)).setCompoundDrawables(null,null,drawable,null);
        }
        isShow=false;
    }

    public void setItemShowHidenListener(onItemShowHidenListener onItemShowHidenListener){
        this.onItemShowHidenListener = onItemShowHidenListener;
    }

    public interface  onItemShowHidenListener{
        void onItemShow(int position);//某个item  展示出来

        void onAllHiden();//所有item都 hiden
    }
}
