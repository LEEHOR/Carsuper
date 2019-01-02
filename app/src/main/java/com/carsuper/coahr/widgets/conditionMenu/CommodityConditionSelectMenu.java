package com.carsuper.coahr.widgets.conditionMenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.carsuper.coahr.R;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.DisplayUtils;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.carsuper.coahr.R.color.background_translunt;

/**
 * Author： hengzwd on 2018/7/3.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityConditionSelectMenu {


    ConditionAdapter conditionAdapter;

    private View contentView;

    private RecyclerView recyclerView;

    private PopupWindow mPopupWindow;

    private static final int DEFAULT_HEIGHT = RecyclerView.LayoutParams.WRAP_CONTENT;

    private int popHeight = DEFAULT_HEIGHT;

    private static final int DEFAULT_ANIM_STYLE = R.style.Commodity_Condition_Popupwindow_Animation;

    private List<String> conditionList = new ArrayList<>();


    private OnStateChangeListener onStateChangeListener;

    private OnMenuItemClickListener onMenuItemClickListener;

    private Context mcontext;

    private LinearLayoutManager linearLayoutManager;

    private boolean isMenuOpen;



    @Inject
    public CommodityConditionSelectMenu(Activity context) {
        mcontext = context;
        init();
    }

    private void init() {
        contentView = LayoutInflater.from(mcontext).inflate(R.layout.layout_commodity_condition, null);
        recyclerView = contentView.findViewById(R.id.rv_conditioon);
        linearLayoutManager = new LinearLayoutManager(mcontext);
        recyclerView.setLayoutManager(linearLayoutManager);
        conditionAdapter=new ConditionAdapter();
        recyclerView.setAdapter(conditionAdapter);
        getPopupWindow();
    }

    public CommodityConditionSelectMenu setContentList(List<String> list,int selectedPosition) {
        conditionList = list;
        conditionAdapter.setData(conditionList,selectedPosition);
        return this;
    }


    private PopupWindow getPopupWindow() {
        popHeight= (int) mcontext.getResources().getDimension(R.dimen.condition_popuwindow_height);
        mPopupWindow = new PopupWindow(mcontext);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setWidth(DisplayUtils.getScreenWidth(mcontext));
        mPopupWindow.setHeight(popHeight);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPopupWindow.setElevation(3.0f);
        }
        mPopupWindow.setAnimationStyle(DEFAULT_ANIM_STYLE);
        mPopupWindow.setFocusable(false);//外部view在popuwindow show的时候能相应事件
        mPopupWindow.setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(-00000);
        mPopupWindow.setBackgroundDrawable(dw);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                onStateChangeListener.ondismiss();
                isMenuOpen = false;
            }
        });
        return mPopupWindow;
    }


    public void showAsDropDown(View anchor) {//以类似dropdown形式展示
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            int maxHeight = DisplayUtils.getScreenHeight(mcontext) - anchor.getBottom();
            mPopupWindow.setHeight(maxHeight);
            mPopupWindow.update();
            mPopupWindow.showAsDropDown(anchor, 0, 0);
            onStateChangeListener.onSpread();
            isMenuOpen = true;
        }
    }


    public CommodityConditionSelectMenu setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
        return this;
    }


    public boolean isMenuOpen() {
        return isMenuOpen;
    }

    public void dismiss() {
        if (isMenuOpen) {
            mPopupWindow.dismiss();
        }
    }

    public CommodityConditionSelectMenu setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        conditionAdapter.setOnMuItemListener(listener);
        return this;
    }



}
