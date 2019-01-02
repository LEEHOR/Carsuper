package com.carsuper.coahr.mvp.view.adapter.CarPicker;

import android.view.View;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CarHorsePowerBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarHorsePowerAdapter extends BaseQuickAdapter<CarHorsePowerBean.JdataBean.CarDetailBean,BaseViewHolder> {

    private onCarTypeItemClickListener onCarTypeItemClickListener;

    public CarHorsePowerAdapter() {
        super(R.layout.recyclerview_item_carinfo, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, final CarHorsePowerBean.JdataBean.CarDetailBean item) {
        helper.setText(R.id.tv_car_info,item.getHorsepower());
        helper.getView(R.id.tv_car_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCarTypeItemClickListener.onItemClick(item);
            }
        });
    }


    public void setOnCarTypeItemClickListener(onCarTypeItemClickListener onCarTypeItemClickListener){
        this.onCarTypeItemClickListener=onCarTypeItemClickListener;
    }


    public interface  onCarTypeItemClickListener{
        void  onItemClick(CarHorsePowerBean.JdataBean.CarDetailBean entity);
    }
}
