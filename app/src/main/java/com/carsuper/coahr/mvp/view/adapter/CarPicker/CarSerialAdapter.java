package com.carsuper.coahr.mvp.view.adapter.CarPicker;

import android.view.View;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CarSerialBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarSerialAdapter extends BaseQuickAdapter<CarSerialBean.JdataEntity.SerialEntity,BaseViewHolder> {

    private OnCarserialItemClickListener onCarserialItemClickListener;
    public CarSerialAdapter() {
        super(R.layout.recyclerview_item_carinfo, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, final CarSerialBean.JdataEntity.SerialEntity item) {
                helper.setText(R.id.tv_car_info,item.getCs_name());
                helper.getView(R.id.tv_car_info).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCarserialItemClickListener.onItemClick(item);
                    }
                });
    }


    public void setOnCarserialItemClickListener(OnCarserialItemClickListener onCarserialItemClickListener){
        this.onCarserialItemClickListener=onCarserialItemClickListener;
    }


    public interface  OnCarserialItemClickListener{
        void  onItemClick(CarSerialBean.JdataEntity.SerialEntity serialEntity);
    }
}
