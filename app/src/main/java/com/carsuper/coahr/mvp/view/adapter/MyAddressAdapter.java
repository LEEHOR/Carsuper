package com.carsuper.coahr.mvp.view.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.UserAddressBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/16.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyAddressAdapter extends BaseQuickAdapter<UserAddressBean.JdataEntity.AddressEntity, BaseViewHolder> {

    private TextView primary_checkbox;
    private int primaryPostion = 0xfffffff;//默认车的位置
    private onAddressHandleListener onAddressHandleListener;
    public MyAddressAdapter() {
        super(R.layout.recyclerview_item_myaddress, null);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final UserAddressBean.JdataEntity.AddressEntity item) {
        helper.setText(R.id.tv_user_name, item.getUsername())
                .setText(R.id.tv_telephone, item.getTelephone())
                .setText(R.id.tv_address, item.getAddress());
        if (item.getSelected().equals("1")) {
            primary_checkbox = helper.getView(R.id.tv_primary_address);
            primaryPostion = helper.getAdapterPosition();
            setDrawLeft(primary_checkbox,R.mipmap.settle_on);
        }else {
            setDrawLeft((TextView) helper.getView(R.id.tv_primary_address),R.mipmap.select);
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressHandleListener.onItemClick(item);
            }
        });
        helper.getView(R.id.tv_primary_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primaryPostion!=helper.getAdapterPosition()) {
                    if (primary_checkbox != null) {
                        setDrawLeft(primary_checkbox,R.mipmap.select);
                    }
                    primaryPostion = helper.getAdapterPosition();
                    primary_checkbox = helper.getView(R.id.tv_primary_address);
                    setDrawLeft(primary_checkbox,R.mipmap.settle_on);
                    onAddressHandleListener.onSetPrimary(item);
                }
            }
        });
        helper.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressHandleListener.delete(item);
            }
        });
        helper.getView(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressHandleListener.edit(item);
            }
        });
    }
    private void setDrawLeft(TextView view,int res){
        Drawable drawable =view.getResources().getDrawable(res);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        view.setCompoundDrawables(drawable,null,null,null);
    }

    public void setOnAddressHandleListener(onAddressHandleListener onAddressHandleListener){
        this.onAddressHandleListener = onAddressHandleListener;
    }
    public interface onAddressHandleListener {

        void onItemClick(UserAddressBean.JdataEntity.AddressEntity item);
        void edit(UserAddressBean.JdataEntity.AddressEntity item);

        void delete(UserAddressBean.JdataEntity.AddressEntity item);

        void onSetPrimary(UserAddressBean.JdataEntity.AddressEntity item);
    }
}
