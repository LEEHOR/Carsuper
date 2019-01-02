package com.carsuper.coahr.widgets.bottomNavigation;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.widgets.SelectorImageView;
import com.socks.library.KLog;


/**
 * Author： hengzwd on 2017/9/12.
 * Email：hengzwdhengzwd@qq.com
 */

public class MyBottomNavigation extends LinearLayout {
    private int inflateView;
    private Context context;
    private View rootView;//填充进来布局的跟布局

    private int mselect = 0;  //导航栏被选中的一栏

    //被选中时的颜色
    private int selectTextColor = R.color.prominent_text_color;

    //失去选中时的颜色
    private int notSelectTextColor = R.color.gray;


    private OnTabPositionListener ontabpostionListener;

    public MyBottomNavigation(Context context) {
        this(context, null);
    }

    public MyBottomNavigation(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBottomNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(getContext());
//        rootView = ((ViewGroup)inflater.inflate(R.layout.layout_mynavigation, this, true)).getChildAt(0);
        rootView = inflater.inflate(R.layout.layout_mynavigation, this, true);

        initViewList();
    }

    private void initViewList() {
        getImage(((ViewGroup)rootView).getChildAt(0),0).toggle();
        getText(((ViewGroup)rootView).getChildAt(0),0).setTextColor(getResources().getColor(selectTextColor));

        int childCount = ((ViewGroup)rootView).getChildCount();
        for (int i = 0; i < childCount; i++) {
            final int finalI = i;
            ((ViewGroup)rootView).getChildAt(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    beenSelect(finalI);
                    if (ontabpostionListener != null) {
                        ontabpostionListener.onPositionTab(finalI);
                    }
                }
            });
        }

    }


    public   void beenSelect(int position){
        if (mselect==position) {
            return;
        }else {
            //之前被选中的图片和文字变色
            getImage(((ViewGroup)rootView).getChildAt(mselect),mselect).toggle();
            getText(((ViewGroup)rootView).getChildAt(mselect),mselect).setTextColor(getResources().getColor(notSelectTextColor));
            //现在变选中的文字变色
            getImage(((ViewGroup)rootView).getChildAt(position),position).toggle();
            getText(((ViewGroup)rootView).getChildAt(position),position).setTextColor(getResources().getColor(selectTextColor));
        }
        mselect = position;
    }

    private SelectorImageView getImage(View parent , int position){

       return  parent.findViewById(position==0?R.id.iv_one:position==1?R.id.iv_two:position==2?R.id.iv_three:position==3?R.id.iv_four:position==4?R.id.iv_five:-1);
    }

    private TextView getText(View parent , int position){
        return  parent.findViewById(position==0?R.id.tv_one:position==1?R.id.tv_two:position==2?R.id.tv_three:position==3?R.id.tv_four:position==4?R.id.tv_five:-1);
    }


    public void setOnTabPositionListener(OnTabPositionListener ontablistener) {
        ontabpostionListener = ontablistener;
    }


    public interface OnTabPositionListener {
        void onPositionTab(int position);
    }
}
