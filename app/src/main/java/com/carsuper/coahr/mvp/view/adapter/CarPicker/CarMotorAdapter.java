package com.carsuper.coahr.mvp.view.adapter.CarPicker;

import android.view.View;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CarMotorBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarMotorAdapter extends BaseQuickAdapter<CarMotorBean.JdataBean.CarDetailBean,BaseViewHolder> {

    private onCarDisplacementItemClickLisenter onCarserialItemClickListener;
    public CarMotorAdapter() {
        super(R.layout.recyclerview_item_carinfo, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, final CarMotorBean.JdataBean.CarDetailBean item) {
        helper.setText(R.id.tv_car_info,item.getMotor());
        helper.getView(R.id.tv_car_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCarserialItemClickListener.onItemClick(item);
            }
        });
    }


    public void setoncarDisplacementClickListener(onCarDisplacementItemClickLisenter onCarserialItemClickListener){
        this.onCarserialItemClickListener=onCarserialItemClickListener;
    }


    public interface  onCarDisplacementItemClickLisenter{
        void  onItemClick(CarMotorBean.JdataBean.CarDetailBean entity);
    }
}
