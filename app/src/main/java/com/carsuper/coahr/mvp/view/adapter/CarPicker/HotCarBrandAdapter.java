package com.carsuper.coahr.mvp.view.adapter.CarPicker;

import android.view.View;
import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CarBrandBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/6.
 * Email：hengzwdhengzwd@qq.com
 */
public class HotCarBrandAdapter  extends BaseQuickAdapter<CarBrandBean.JdataEntity.HotEntity,BaseViewHolder>{
    private OnCarBrandItemClickListener onCarBrandItemClickListener;

    public HotCarBrandAdapter(List<CarBrandBean.JdataEntity.HotEntity> mdata) {
        super(R.layout.recyclerview_item_carbrand_grid, mdata);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CarBrandBean.JdataEntity.HotEntity item) {
        Imageloader.loadImage(item.getPic(), (ImageView) helper.getView(R.id.iv_car_brand));
        helper.setText(R.id.tv_carbrand_name,item.getB_name());
        helper.getView(R.id.rl_car_brand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCarBrandItemClickListener.onHotItemClick(item);
            }
        });
    }

    public void setBrandItemClicklistener(OnCarBrandItemClickListener onCarBrandItemClickListener){
        this.onCarBrandItemClickListener=onCarBrandItemClickListener;
    }
}
