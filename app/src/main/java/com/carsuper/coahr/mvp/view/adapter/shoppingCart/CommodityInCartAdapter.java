package com.carsuper.coahr.mvp.view.adapter.shoppingCart;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.ShoppingCartBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.CommodityCountView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Author： hengzwd on 2018/8/20.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityInCartAdapter extends BaseQuickAdapter<ShoppingCartBean.JdataEntity.CommodityEntity, BaseViewHolder> {


    private onSelectChangeListener onSelectChangeListener;

    private List<ShoppingCartBean.JdataEntity.CommodityEntity> mSelectedPositions = new ArrayList<>();

    public CommodityInCartAdapter() {
        super(R.layout.recyclerview_item_commodity_in_cart, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShoppingCartBean.JdataEntity.CommodityEntity item) {

        helper.setText(R.id.tv_commodity_info, item.getC_name())
                .setText(R.id.tv_commodity_price, "￥" + item.getC_price());
        Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_commodity_img));
        ((CommodityCountView) helper.getView(R.id.ccv_commodity_count)).setCount(Integer.parseInt(item.getC_num()));
        ((CommodityCountView) helper.getView(R.id.ccv_commodity_count)).setOnCountChangeListener(new CommodityCountView.onCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                item.setC_num(count + "");
                onSelectChangeListener.onChange();
            }
        });
        ((CheckBox) helper.getView(R.id.tv_check)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!mSelectedPositions.contains(item)) {
                        mSelectedPositions.add(item);
                    }
                } else {
                    mSelectedPositions.remove(item);
                }
                onSelectChangeListener.onChange();
            }
        });
        if (mSelectedPositions.contains(item)) {
            ((CheckBox) helper.getView(R.id.tv_check)).setChecked(true);
        } else {
            ((CheckBox) helper.getView(R.id.tv_check)).setChecked(false);
        }
        helper.getView(R.id.rl_commodity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedPositions.contains(item)) {
                    ((CheckBox) helper.getView(R.id.tv_check)).setChecked(false);
                } else {
                    ((CheckBox) helper.getView(R.id.tv_check)).setChecked(true);
                }
            }
        });

    }

    public boolean isAllSelected() {
        return mSelectedPositions.size() == getItemCount();
    }

    public void setOnSelectChangeListener(onSelectChangeListener onSelectChangeListener) {
        this.onSelectChangeListener = onSelectChangeListener;
    }

    public List<ShoppingCartBean.JdataEntity.CommodityEntity> getmSelectedPositions() {
        return mSelectedPositions;
    }


    public void checkAll() {
        mSelectedPositions.clear();
        mSelectedPositions.addAll(getData());
        notifyDataSetChanged();
        onSelectChangeListener.onChange();
    }

    public void unCheckAll() {
        mSelectedPositions.clear();
        notifyDataSetChanged();
        onSelectChangeListener.onChange();
    }

    public interface onSelectChangeListener {
        void onChange();
    }
}
