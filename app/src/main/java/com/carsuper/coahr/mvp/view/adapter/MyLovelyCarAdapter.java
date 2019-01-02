package com.carsuper.coahr.mvp.view.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.MyLovelyCarBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/15.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyLovelyCarAdapter extends BaseQuickAdapter<MyLovelyCarBean.JdataBean.MycarBean, BaseViewHolder> {

    private TextView primary_checkbox;

    private MyLovelyCarBean.JdataBean.MycarBean primary_carEntity;
    private int primaryPostion = 0xfffffff;//默认车的位置
    private OnLovelyCarHandleListener onLovelyCarHandleListener;

    public MyLovelyCarAdapter() {
        super(R.layout.recyclerview_item_mylovely_car, null);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final MyLovelyCarBean.JdataBean.MycarBean item) {
        helper.setText(R.id.tv_car_motor, item.getMotor())
                .setText(R.id.tv_car_horsepower,  item.getHorsepower())
                .setText(R.id.tv_car_brand, item.getSerials());
        Imageloader.loadImage(item.getPic(), (ImageView) helper.getView(R.id.iv_car_img));
        if (item.getDefault_check().equals("1")) {
            primary_checkbox = helper.getView(R.id.tv_primary_car);
            primaryPostion = helper.getAdapterPosition();
            setDrawLeft(primary_checkbox,R.mipmap.settle_on);
            primary_checkbox.setText("已设为默认车型");
            primary_carEntity =item;
        }else {
            TextView tv_check = helper.getView(R.id.tv_primary_car);
            setDrawLeft(tv_check,R.mipmap.select);
            tv_check.setText("设为默认车型");
        }
        helper.getView(R.id.tv_primary_car).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primaryPostion!=helper.getAdapterPosition()) {
                    if (primary_checkbox != null) {
                        setDrawLeft(primary_checkbox,R.mipmap.select);
                        primary_checkbox.setText("设为默认车型");
                        primary_carEntity.setDefault_check("-1");
                    }
                    primaryPostion = helper.getAdapterPosition();
                    primary_checkbox = helper.getView(R.id.tv_primary_car);
                    setDrawLeft(primary_checkbox,R.mipmap.settle_on);
                    primary_checkbox.setText("已设为默认车型");
                    primary_carEntity = item;
                    primary_carEntity.setDefault_check("1");
                    notifyDataSetChanged();
                    onLovelyCarHandleListener.onSetPrimary(item);
                }
            }
        });

        helper.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemRemoved(helper.getAdapterPosition());
                MyLovelyCarAdapter.this.getData().remove(item);
                onLovelyCarHandleListener.onDeleteCar(item);
            }
        });

        helper.getView(R.id.my_love_rel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (primary_checkbox != null) {
                    setDrawLeft(primary_checkbox,R.mipmap.select);
                    primary_checkbox.setText("设为默认车型");
                    primary_carEntity.setDefault_check("-1");
                }
                primaryPostion = helper.getAdapterPosition();
                primary_checkbox = helper.getView(R.id.tv_primary_car);
                setDrawLeft(primary_checkbox,R.mipmap.settle_on);
                primary_checkbox.setText("已设为默认车型");
                primary_carEntity = item;
                primary_carEntity.setDefault_check("1");
                notifyDataSetChanged();
                onLovelyCarHandleListener.onSelectMyOrderCar(item);
            }
        });
    }


    private void setDrawLeft(TextView view,int res){
        Drawable drawable =view.getResources().getDrawable(res);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        view.setCompoundDrawables(drawable,null,null,null);
    }

    public void setOnLovelyCarHandleListener(OnLovelyCarHandleListener onLovelyCarHandleListener) {
        this.onLovelyCarHandleListener = onLovelyCarHandleListener;
    }

    public interface OnLovelyCarHandleListener {
        void onDeleteCar(MyLovelyCarBean.JdataBean.MycarBean item);

        void onSetPrimary(MyLovelyCarBean.JdataBean.MycarBean item);

        void onSelectMyOrderCar(MyLovelyCarBean.JdataBean.MycarBean item);
    }
}
