package com.carsuper.coahr.mvp.view.adapter.citypicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.view.adapter.citypicker.decoration.CityGridItemDecoration;
import  com.carsuper.coahr.mvp.model.bean.CityInfoBean.JdataEntity.CityListEntity.CityEntity;


import java.util.ArrayList;
import java.util.List;


/**
 * @Author: Bro0cL
 * @Date: 2018/2/5 12:06
 */
public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.BaseViewHolder> {
    private static final int VIEW_TYPE_CURRENT = 10;
    private static final int VIEW_TYPE_HOT = 11;
    private static final int VIEW_TYPE_COUNTRY = 12;
    private int selectedPosition =1000;//上一个选中的item飘红

    private Context mContext;
    private List<CityEntity> mData;
    private List<CityEntity> mHotData;
    private int locateState;
    private InnerListener mInnerListener;

    private List<CityEntity> mAllCities = new ArrayList<CityEntity>(); //所有城市



    public CityListAdapter(Context context, List<CityEntity> data, List<CityEntity> hotData, int state) {
        this.mData = data;
        this.mContext = context;
        this.mHotData = hotData;
        this.locateState = state;
    }

    public void setNewData(List<CityEntity> data, List<CityEntity> hotData, int state){
        this.mData = data;
        this.mHotData = hotData;
        notifyDataSetChanged();
    }
    public void updateData(List<CityEntity> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void updateData(List<CityEntity> data,int lastSelectPostion) {
        this.mData = data;
        this.selectedPosition= lastSelectPostion;
        notifyDataSetChanged();
    }

    public void updateLocateState(CityEntity location, int state) {

        mData.remove(0);
        mData.add(0, location);
        locateState = state;
        notifyItemChanged(0);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPE_CURRENT:
                view = LayoutInflater.from(mContext).inflate(R.layout.cp_list_item_location_layout, parent, false);
                return new LocationViewHolder(view);
            case VIEW_TYPE_HOT:
                view = LayoutInflater.from(mContext).inflate(R.layout.cp_list_item_hot_layout, parent, false);
                return new HotViewHolder(view);
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.cp_list_item_default_layout, parent, false);
                return new DefaultViewHolder(view);
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder == null) return;
        if (holder instanceof DefaultViewHolder) {
            final int pos = holder.getAdapterPosition();
            final CityEntity data = mData.get(pos);
            if (data == null) return;
            ((DefaultViewHolder) holder).name.setText(data.getC_name());
            if (selectedPosition == position){ ((DefaultViewHolder) holder).name.setTextColor(R.color.prominent_text_color);}
            ((DefaultViewHolder) holder).name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mInnerListener != null) {
                        mInnerListener.dismiss(pos, data);
                    }
                }
            });
        }
        //定位城市
        if (holder instanceof LocationViewHolder) {
            final int pos = holder.getAdapterPosition();
             final CityEntity data = mData.get(pos);
            switch (locateState) {
                case Constants.LOCATION_ING:
                    ((LocationViewHolder) holder).current.setText(R.string.cp_locating);
                    break;
                case Constants.LOCATION_SUCCESS:
                    ((LocationViewHolder) holder).current.setText(data.getC_name());
                    break;
                case Constants.LOCATION_FAILURE:
                    ((LocationViewHolder) holder).current.setText(R.string.cp_locate_failed);
                    break;
            }
            ((LocationViewHolder) holder).current.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (locateState == Constants.LOCATION_SUCCESS) {
                        if (mInnerListener != null&&!data.getC_name().equals("未知")) {//由于data是final的，所以有可能造成时差错误
                            mInnerListener.dismiss(pos, data);
                        }
                    } else if (locateState == Constants.LOCATION_FAILURE) {
                        locateState = Constants.LOCATION_ING;
                        notifyItemChanged(0);
                        if (mInnerListener != null) {
                            mInnerListener.locate();
                        }
                    }
                }
            });
        }

        //热门城市
        if (holder instanceof HotViewHolder) {
            final int pos = holder.getAdapterPosition();
            final List<CityEntity> data = mHotData;
            if (data == null) return;
            HotCityGridListAdapter mAdapter = new HotCityGridListAdapter(mContext, mHotData);
            mAdapter.setInnerListener(mInnerListener);
            ((HotViewHolder) holder).mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public int getItemCount() {

        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 &&mData.get(position).getC_id().equals("0"))
            return VIEW_TYPE_CURRENT;
        if (position == 1&&mData.get(position).getC_id().equals("0"))
            return VIEW_TYPE_HOT;
        return super.getItemViewType(position);
    }

    public void setInnerListener(InnerListener listener) {
        this.mInnerListener = listener;
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder {
        BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class DefaultViewHolder extends BaseViewHolder {
        TextView name;

        DefaultViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cp_list_item_name);
        }
    }

    public static class HotViewHolder extends BaseViewHolder {
        RecyclerView mRecyclerView;

        HotViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.cp_hot_list);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(),
                    HotCityGridListAdapter.SPAN_COUNT, LinearLayoutManager.VERTICAL, false));
            int space = itemView.getContext().getResources().getDimensionPixelSize(R.dimen.cp_grid_item_space);
            mRecyclerView.addItemDecoration(new CityGridItemDecoration(HotCityGridListAdapter.SPAN_COUNT,
                    space));
        }
    }

    public static class LocationViewHolder extends BaseViewHolder {

        TextView current;

        LocationViewHolder(View itemView) {
            super(itemView);
            current = itemView.findViewById(R.id.cp_list_item_location);
        }
    }

}
