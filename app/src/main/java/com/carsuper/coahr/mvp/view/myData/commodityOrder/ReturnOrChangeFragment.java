package com.carsuper.coahr.mvp.view.myData.commodityOrder;

import android.net.Uri;
import android.os.Bundle;
import android.os.TokenWatcher;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.commodityOrder.ReturnOrChangeContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.presenter.myData.commodityOrder.ReturnOrChangePresenter;
import com.carsuper.coahr.mvp.view.adapter.OpinionIMGAdapter;
import com.carsuper.coahr.mvp.view.adapter.myCommodityOrder.CommodityOrderDetailCommodityAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 * 我的商品订单取消订单
 */
public class ReturnOrChangeFragment extends BaseFragment<ReturnOrChangeContract.Presenter> implements ReturnOrChangeContract.View {


    @Inject
    ReturnOrChangePresenter returnOrChangePresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tv_return_change_message)
    TextView tvReturnChangeMessage;
    @BindView(R.id.rv_commodity_list)
    RecyclerView rvCommodityList;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rv_img_list)
    RecyclerView rvImgList;
    @BindView(R.id.bt_cancle_submit)
    Button btCancleSubmit;

    private LinearLayoutManager linearLayoutManager;
    private CommodityOrderDetailCommodityAdapter commodityOrderDetailCommodityAdapter;
    private String order_Id;

    private String refund_id;

    private LinearLayoutManager linearLayoutManager0;
    private ReturnChangeIMGadapter returnChangeIMGadapter;
    private static final int maxImg = 3;//最多图片数量

    private List<Uri> uris = new ArrayList<>();//图片地址数组

    public static ReturnOrChangeFragment newInstance(String order_Id) {
        ReturnOrChangeFragment fragment = new ReturnOrChangeFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id", order_Id);
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public ReturnOrChangeContract.Presenter getPresenter() {
        return returnOrChangePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_return_change_detail;
    }

    @Override
    public void initView() {
        tbTittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

        btCancleSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //撤销申请
                Map map  = new HashMap();
                map.put("token",Constants.token);
                map.put("refund_id",refund_id);
                getPresenter().refundCancel(map);

            }
        });
    }

    @Override
    public void initData() {
        order_Id = getArguments().getString("order_id");
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        rvCommodityList.setLayoutManager(linearLayoutManager);

        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext,LinearLayoutManager.HORIZONTAL,false);
        returnChangeIMGadapter = new ReturnChangeIMGadapter();
        rvImgList.setLayoutManager(linearLayoutManager);
        rvImgList.setAdapter(returnChangeIMGadapter);

        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id", order_Id);
        getPresenter().getCommodityOrderDetail(map);
    }


    @Override
    public void onGetCommodityOrderDetialSuccess(CommodityOrderDetailBean bean) {
        commodityOrderDetailCommodityAdapter = new CommodityOrderDetailCommodityAdapter(bean.getJdata().getCommodity());
        rvCommodityList.setAdapter(commodityOrderDetailCommodityAdapter);
        rvCommodityList.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.material_grey_200)));
        if (bean.getJdata().getRefund_order() != null) {
            refund_id = bean.getJdata().getRefund_order().getId();
            tvNumber.setText("退款编号："+bean.getJdata().getRefund_order().getId());
            tvReason.setText("退款原因："+bean.getJdata().getRefund_order().getReason());
            tvMoney.setText("退款金额；￥"+bean.getJdata().getRefund_order().getRefund());
            tvTime.setText("申请时间："+ bean.getJdata().getRefund_order().getAddtime());
            if (bean.getJdata().getRefund_order().getDes_pic() != null) {
                returnChangeIMGadapter.setNewData(bean.getJdata().getRefund_order().getDes_pic());
            }
        }
    }

    @Override
    public void onGetCommodityOrderDetailFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefundCancelSuccess(ResultBean bean) {
        if (bean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext,bean.getJdata().getJmsg(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(BaseApplication.mContext,bean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRefundCancelFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();
    }


    public static  class ReturnChangeIMGadapter extends BaseQuickAdapter<String,BaseViewHolder> {

        public ReturnChangeIMGadapter() {
            super(R.layout.recyclerview_item_opinion_img, null);
        }

        @Override
        protected void convert(final BaseViewHolder helper, String item) {
            Imageloader.loadImage(item, (ImageView) helper.getView(R.id.iv_opinion_img));
            helper.getView(R.id.iv_cancle).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData().remove(helper.getAdapterPosition());
                    notifyItemRemoved(helper.getAdapterPosition());
                }
            });
        }
    }
}
