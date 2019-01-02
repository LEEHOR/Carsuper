package com.carsuper.coahr.mvp.view.adapter.CommodityEvaluata;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateDetailBeans;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateDetailbean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/8.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommoditySecondEvaluateAdapter extends BaseQuickAdapter<CommodityEvaluateDetailBeans.JdataBean.ReplyBean,BaseViewHolder> {


    private OnDianzanClickListener onDianzanClickListener;

    public CommoditySecondEvaluateAdapter(List<CommodityEvaluateDetailBeans.JdataBean.ReplyBean> replyEntities) {
        super(R.layout.recyclerview_item_secondlevel_evaluation, replyEntities);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final CommodityEvaluateDetailBeans.JdataBean.ReplyBean item) {
        helper.setText(R.id.tv_user_name,item.getNickname())
                .setText(R.id.tv_evaluate_time,item.getCreate_time())
                .setText(R.id.tv_evaluate_message,item.getComment())
                .setText(R.id.tv_zan,item.getPraise());
        if (item.getPraise_status()==0) {
            Drawable drawable =helper.getView(R.id.tv_zan).getResources().getDrawable(R.mipmap.zan_no);
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            ((TextView)helper.getView(R.id.tv_zan)).setCompoundDrawables(drawable,null,null,null);
        }else {
            Drawable drawable =helper.getView(R.id.tv_zan).getResources().getDrawable(R.mipmap.zan);
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            ((TextView)helper.getView(R.id.tv_zan)).setCompoundDrawables(drawable,null,null,null);

        }
        helper.getView(R.id.tv_zan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    if (item.getPraise_status()==0) {
//                        helper.setText(R.id.tv_zan,Integer.parseInt(item.getPraise())+1+"");
//                    }else {
//                        helper.setText(R.id.tv_zan,Integer.parseInt(item.getPraise())-11+"");
//                    }
                onDianzanClickListener.onDianzanClick(helper.getAdapterPosition(),item.getSp_id());
            }
        });
        Imageloader.loadCircularImage(item.getUserheadimg(), (ImageView) helper.getView(R.id.iv_user_headerimg));
    }


    public void setOnDianzanClickListener(OnDianzanClickListener onDianzanClickListener){
        this.onDianzanClickListener=onDianzanClickListener;
    }

    public interface  OnDianzanClickListener{
        void  onDianzanClick(int position,String sp_id);
    }
}
