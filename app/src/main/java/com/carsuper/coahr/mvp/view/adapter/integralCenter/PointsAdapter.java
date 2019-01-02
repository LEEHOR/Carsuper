package com.carsuper.coahr.mvp.view.adapter.integralCenter;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.IntegralCenterBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/24.
 * Email：hengzwdhengzwd@qq.com
 */
public class PointsAdapter extends BaseQuickAdapter<IntegralCenterBean.JdataEntity.ListEntity,BaseViewHolder> {

    public PointsAdapter() {
        super(R.layout.recyclerview_item_points, null);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, IntegralCenterBean.JdataEntity.ListEntity item) {
            helper.setText(R.id.tv_point_discription,item.getDescription())
                    .setText(R.id.tv_point_time,item.getAddtime())
                    .setText(R.id.tv_point,item.getPoint());
        ((TextView)helper.getView(R.id.tv_point)).setTextColor(Integer.parseInt(item.getType())>0?R.color.material_blue_300:R.color.material_grey_200);
    }


}
