package com.carsuper.coahr.mvp.view.myData.maintanceOrder;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MNeedToServeContract;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.presenter.myData.maintanceOrder.MNeedToServePresenter;
import com.carsuper.coahr.mvp.view.adapter.myMaintanceOrder.MaintanceOrderDetailCommodityAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.CancleReasonInputDialogFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.StarBar;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/14.
 * Email：hengzwdhengzwd@qq.com
 * 待服务
 */
public class MNeedToServeFragment extends BaseFragment<MNeedToServeContract.Presenter> implements MNeedToServeContract.View ,View.OnClickListener{

    @Inject
    MNeedToServePresenter mNeedToServePresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tv_owner_name)
    TextView tvOwnerName;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.iv_car)
    ImageView ivCar;
    @BindView(R.id.tv_lovely_car)
    TextView tvLovelyCar;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_maintance)
    ImageView ivMaintance;
    @BindView(R.id.tv_maintance_address)
    TextView tvMaintanceAddress;
    @BindView(R.id.rv_commodity_list)
    RecyclerView rvCommodityList;
    @BindView(R.id.tv_service_name)
    TextView tvServiceName;
    @BindView(R.id.tv_service_price)
    TextView tvServicePrice;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_copy_order_number)
    TextView tvCopyOrderNumber;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_need_to_pay)
    TextView tvNeedToPay;
    @BindView(R.id.tv_contact_someone)
    TextView tvContactSomeone;
    @BindView(R.id.tv_cancle_order)
    TextView tvCancelOrder;
    @BindView(R.id.tv_ensure_complete_service)
    TextView tvEnsureComplete;
    @BindView(R.id.tv_service_status)
    TextView tvServiceStatus;
    @BindView(R.id.iv_service_status)
    ImageView ivServiceStatus;

    /**
     * 门店
     */
    @BindView(R.id.line_station_show)
    LinearLayout line_station_show;
    @BindView(R.id.iv_store_img)
    ImageView iv_store_img;
    @BindView(R.id.tv_store_name)
    TextView tv_store_name;
    @BindView(R.id.tv_store_star)
    StarBar tv_store_star;
    @BindView(R.id.tv_store_address)
    TextView tv_store_address;
    @BindView(R.id.tv_store_distance)
    TextView tv_store_distance;

    private String order_id;

    private MaintanceOrderDetailCommodityAdapter maintanceOrderDetailCommodityAdapter;
    private LinearLayoutManager linearLayoutManager;
    public static MNeedToServeFragment newInstance(String order_Id) {
        MNeedToServeFragment fragment = new MNeedToServeFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id", order_Id);
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public MNeedToServeContract.Presenter getPresenter() {
        return mNeedToServePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_maintanceorder_need_to_serve;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(this);
        tvCopyOrderNumber.setOnClickListener(this);
        tvContactSomeone.setOnClickListener(this);
        tvCancelOrder.setOnClickListener(this);
        tvEnsureComplete.setOnClickListener(this);
    }

    @Override
    public void initData() {
        order_id=getArguments().getString("order_id");
        linearLayoutManager=new LinearLayoutManager(BaseApplication.mContext);
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id",order_id);
        getPresenter().getMaintanceOrderDetail(map);
    }



    @Override
    public void onGetMaintanceOrderDetialSuccess(MaintanceOrderDetailBean bean) {
        tvOwnerName.setText(bean.getJdata().getAppoint().getNickname());
        tvPhoneNumber.setText(bean.getJdata().getAppoint().getPhone());
        tvLovelyCar.setText(bean.getJdata().getAppoint().getCar());
        tvDate.setText(bean.getJdata().getAppoint().getAppoint_date());
        tvTime.setText(bean.getJdata().getAppoint().getAppoint_time());
        tvMaintanceAddress.setText(bean.getJdata().getAppoint().getAddress());

        maintanceOrderDetailCommodityAdapter= new MaintanceOrderDetailCommodityAdapter(bean.getJdata().getCommodity());
        rvCommodityList.setLayoutManager(linearLayoutManager);
        rvCommodityList.setAdapter(maintanceOrderDetailCommodityAdapter);
        rvCommodityList.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.material_grey_200)));

        tvServicePrice.setText("¥"+bean.getJdata().getOrder().getFee());
        tvOrderNumber.setText(bean.getJdata().getOrder().getOrder_id());
        tvOrderStatus.setText("待服务");
        tvOrderTime.setText(bean.getJdata().getOrder().getCreate_time());
        tvNeedToPay.setText("¥"+bean.getJdata().getOrder().getTotal());
        switch (bean.getJdata().getAppoint().getAssignment()) {
            case 1:
                tvServiceStatus.setText("等待指派门店");
                ivServiceStatus.setImageResource(R.mipmap.need_serve1);
                tvContactSomeone.setText("联系客服");
                tvEnsureComplete.setVisibility(View.GONE);
                break;
            case 2:
                tvServiceStatus.setText("门店已接单马上指派员工");
                ivServiceStatus.setImageResource(R.mipmap.need_serve2);
                tvContactSomeone.setText("联系门店");
                tvEnsureComplete.setVisibility(View.GONE);
                break;
            case 3:
                tvServiceStatus.setText("员工已接单");
                ivServiceStatus.setImageResource(R.mipmap.need_serve2);
                tvContactSomeone.setVisibility(View.GONE);
                break;
        }
        /**
         * 门店
         */
        if (bean.getJdata().getStation() !=null && bean.getJdata().getStation().getS_id() !=null){
            line_station_show.setVisibility(View.VISIBLE);
            Imageloader.loadImage(bean.getJdata().getStation().getPic1(),iv_store_img);
            tv_store_name.setText(bean.getJdata().getStation().getS_name());
            tv_store_address.setText(bean.getJdata().getStation().getS_address());
            tv_store_star.setStarMark(bean.getJdata().getStation().getLevel_score() !=null && !bean.getJdata().getStation().getLevel_score().equals("")?Float.parseFloat(bean.getJdata().getStation().getLevel_score()):Float.parseFloat("0"));
        } else {
            line_station_show.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetMaintanceOrderDetailFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancelOrderSuccess(ResultBean resultBean) {
        if (resultBean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext,resultBean.getJdata().getJmsg(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(BaseApplication.mContext,resultBean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCancelOrderFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConfirmSuccess(ResultBean bean) {
        if (bean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext,bean.getJdata().getJmsg(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(BaseApplication.mContext,bean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConfirmFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_left:
                _mActivity.onBackPressed();
            case R.id.tv_copy_order_number:
                ClipboardManager cm = (ClipboardManager) _mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText("text", tvOrderNumber.getText()));

              //  cm.setText(tvOrderNumber.getText());
                Toast.makeText(BaseApplication.mContext,"已复制到剪切板",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_cancle_order:
                CancleReasonInputDialogFragment dialogFragment = CancleReasonInputDialogFragment.newInstance(CancleReasonInputDialogFragment.TYPE_MAINTANCE_ORDER);
                dialogFragment.setOnInputCallback(new CancleReasonInputDialogFragment.InputCallback() {
                    @Override
                    public void onInputSend(String input) {
                        Map map = new HashMap();
                        map.put("token", Constants.token);
                        map.put("order_id", order_id);
                        map.put("reason", input);
                        getPresenter().cancelMaintanceOrder(map);
                    }
                });
                dialogFragment.show(_mActivity.getSupportFragmentManager(), TAG);
                break;
            case R.id.tv_contact_someone:
                call("400 688 4500");
                break;
            case R.id.tv_ensure_complete_service:
                Map map = new HashMap();
                map.put("token",Constants.token);
                map.put("order_id",order_id);
                getPresenter().confirmServiceFinish(map);
                break;
        }

    }
}
