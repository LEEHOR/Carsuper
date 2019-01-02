package com.carsuper.coahr.widgets.conditionMenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.view.adapter.citypicker.CityListAdapter;
import com.carsuper.coahr.mvp.view.adapter.citypicker.InnerListener;
import com.carsuper.coahr.mvp.view.adapter.citypicker.decoration.CitySectionItemDecoration;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.DisplayUtils;
import com.carsuper.coahr.widgets.SideBar;
import com.carsuper.coahr.widgets.SideIndexBar;
import com.socks.library.KLog;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean.JdataEntity.CityListEntity.CityEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/7/27.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreConditionMenu {

    ConditionAdapter conditionAdapter;

    private View contentView;

    private RecyclerView recyclerView;
    private SideBar sideIndexBar;
    private TextView tv_dialog;

    private PopupWindow mPopupWindow;

    private static final int DEFAULT_HEIGHT = RecyclerView.LayoutParams.WRAP_CONTENT;

    private static int FILL_SCREEN = 0;

    private int popHeight =DEFAULT_HEIGHT;

    private static final int DEFAULT_ANIM_STYLE = R.style.Commodity_Condition_Popupwindow_Animation;

    private List<String> sortList = new ArrayList<>();

    private List<CityEntity> mAllCities = new ArrayList<CityEntity>();
    private List<CityEntity> mHotCities = new ArrayList<CityEntity>();
    private CityListAdapter mAdapter;
    private int locateState = Constants.LOCATION_ING;

    private OnStateChangeListener onStateChangeListener;

    private OnMenuItemClickListener onMenuItemClickListener;

    private Context mcontext;

    private LinearLayoutManager linearLayoutManager;

    private boolean isMenuOpen;

    private View anchor;


    @Inject
    public StoreConditionMenu(Activity context) {
        mcontext = context;
        init();
    }

    private void init() {
        contentView = LayoutInflater.from(mcontext).inflate(R.layout.layout_store_condition, null);
        recyclerView = contentView.findViewById(R.id.rv_city);
        sideIndexBar = contentView.findViewById(R.id.side_index_bar);
        tv_dialog= contentView.findViewById(R.id.tv_dialog);
        linearLayoutManager = new LinearLayoutManager(mcontext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new CitySectionItemDecoration(mcontext, mAllCities), 0);
        conditionAdapter = new ConditionAdapter();
        mAdapter = new CityListAdapter(mcontext, mAllCities, mHotCities, locateState);
        sideIndexBar.setType(1);//没有中文
        sideIndexBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onHit(String letter) {
                tv_dialog.setVisibility(View.VISIBLE);
                tv_dialog.setText(letter);
                //滚动RecyclerView到索引位置
                if (mAllCities == null || mAllCities.isEmpty()) return;
                if (TextUtils.isEmpty(letter)) return;
                int size = mAllCities.size();
                for (int i = 0; i < size; i++) {
                    if (TextUtils.equals(letter.substring(0, 1), mAllCities.get(i).getC_code().substring(0, 1).toUpperCase())) {
                        if (linearLayoutManager != null) {
                            linearLayoutManager.scrollToPositionWithOffset(i, 0);
                            return;
                        }
                    }
                }
            }

            @Override
            public void onCancel() {
                tv_dialog.setVisibility(View.GONE);
            }
        });
       /* sideIndexBar.setOnIndexChangedListener(new SideIndexBar.OnIndexTouchedChangedListener() {
            @Override
            public void onIndexChanged(String index, int position) {
                //滚动RecyclerView到索引位置
                if (mAllCities == null || mAllCities.isEmpty()) return;
                if (TextUtils.isEmpty(index)) return;
                int size = mAllCities.size();
                for (int i = 0; i < size; i++) {
                    if (TextUtils.equals(index.substring(0, 1), mAllCities.get(i).getC_code().substring(0, 1))) {
                        if (linearLayoutManager != null) {
                            linearLayoutManager.scrollToPositionWithOffset(i, 0);
                            return;
                        }
                    }
                }
            }
        });*/
        getPopupWindow();
    }

    public void setAnchor(View anchor) {
        this.anchor = anchor;
    }

    public StoreConditionMenu setSortList(List<String> list, int selectedPosition) {
        sortList = list;
        sideIndexBar.setVisibility(View.GONE);
        recyclerView.setAdapter(conditionAdapter);
        ((CitySectionItemDecoration)recyclerView.getItemDecorationAt(0)).setData(null);
        conditionAdapter.setData(sortList, selectedPosition);
        return this;
    }

    public StoreConditionMenu setCityList(List<CityEntity> mAllCities, int selectPosition) {
        this.mAllCities = mAllCities;
        sideIndexBar.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(mAdapter);
        ((CitySectionItemDecoration)recyclerView.getItemDecorationAt(0)).setData(mAllCities);
        mAdapter.updateData(mAllCities, selectPosition);
        return this;
    }


    private PopupWindow getPopupWindow() {
        popHeight=DensityUtils.dp2px(BaseApplication.mContext,350);
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

    private int getPopuwindowHeight(View anchor) {//当popuwindow超出屏幕界限的时候，会不遵守anchor的界限，所以要在这里计算
        int maxHeight = DisplayUtils.getScreenHeight(mcontext) - anchor.getBottom();

        if (measureViewHeight(contentView) < maxHeight) {
            return measureViewHeight(contentView);
        } else {
            return maxHeight;
        }
    }

    private int measureViewHeight(View view) {//通过测量得到view的宽高
        int intw = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int inth = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(intw, inth);
        int intwidth = view.getMeasuredWidth();
        int intheight = view.getMeasuredHeight();
        return intheight;
    }

    public void showAsDropDown(View anchor) {//以类似dropdown形式展示
        this.anchor = anchor;
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
//            mPopupWindow.setHeight(getPopuwindowHeight(anchor));
            popHeight=  DisplayUtils.getScreenHeight(mcontext) - anchor.getBottom();
//        popHeight = DensityUtils.dp2px(BaseApplication.mContext,500);
            mPopupWindow.setHeight(popHeight);
            mPopupWindow.update();
            mPopupWindow.showAsDropDown(anchor, 0, 0);
            onStateChangeListener.onSpread();
            isMenuOpen = true;
        }
    }
    public void showAsDropDown() {//以类似dropdown形式展示
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            popHeight=  DisplayUtils.getScreenHeight(mcontext) - anchor.getBottom();
//        popHeight = DensityUtils.dp2px(BaseApplication.mContext,500);
            mPopupWindow.setHeight(popHeight);
            mPopupWindow.update();
            mPopupWindow.showAsDropDown(anchor, 0, 0);
            onStateChangeListener.onSpread();
            isMenuOpen = true;
        }
    }

    public StoreConditionMenu setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
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

    public StoreConditionMenu setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        conditionAdapter.setOnMuItemListener(listener);
        return this;
    }

    public StoreConditionMenu setInnerListener(InnerListener listener) {
        mAdapter.setInnerListener(listener);
        return this;
    }


}
