package com.carsuper.coahr.mvp.view.adapter;

import android.view.View;
import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.TrankForEvaluateBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/30.
 * Email：hengzwdhengzwd@qq.com
 */
public class ContinueEvaluateAdapter extends BaseQuickAdapter<TrankForEvaluateBean.JdataEntity.OrderEntity,BaseViewHolder> {



    private OnGoToEvaluateListener onGoToEvaluateListener ;
    public ContinueEvaluateAdapter() {
        super(R.layout.recyclerview_item_continue_to_evaluate, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final TrankForEvaluateBean.JdataEntity.OrderEntity item) {
                helper.setText(R.id.tv_order_info,item.getC_name());
        Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_order_img));
        helper.getView(R.id.tv_goto_evaluate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGoToEvaluateListener.gotoEvaluate(item);
            }
        });
    }

    public void setOnGoToEvaluateListener(OnGoToEvaluateListener onGoToEvaluateListener) {
        this.onGoToEvaluateListener = onGoToEvaluateListener;
    }
    public interface OnGoToEvaluateListener{
        void gotoEvaluate(TrankForEvaluateBean.JdataEntity.OrderEntity item);
    }
}
