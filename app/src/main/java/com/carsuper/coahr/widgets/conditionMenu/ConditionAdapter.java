package com.carsuper.coahr.widgets.conditionMenu;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.carsuper.coahr.R;
import com.socks.library.KLog;

import java.util.List;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/7/3.
 * Email：hengzwdhengzwd@qq.com
 */
public class ConditionAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private OnMenuItemClickListener listener;


    private int selectedPosition;



    @Inject
    public ConditionAdapter() {
        super(R.layout.recyclerview_item_condition, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {

        helper.setText(R.id.tv_condition, item)
                .setTextColor(R.id.tv_condition, Color.BLACK)
                .getView(R.id.tv_condition).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                ((TextView) v).setTextColor(R.color.prominent_text_color);
                listener.onMenuItemClick(helper.getAdapterPosition());
            }
        });

        if (helper.getAdapterPosition() == selectedPosition) {
            helper.setTextColor(R.id.tv_condition, R.color.prominent_text_color);
        }
    }


    public void setData(List<String> data, int selectedPosition) {
        this.selectedPosition = selectedPosition;
        setNewData(data);
    }

    public void setOnMuItemListener(OnMenuItemClickListener listener) {
        this.listener = listener;
    }
}
