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
import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MHaveBeenCompleteContract;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;
import com.carsuper.coahr.mvp.presenter.myData.maintanceOrder.MHaveBeenCompletePresenter;
import com.carsuper.coahr.mvp.view.adapter.myMaintanceOrder.MaintanceOrderDetailCommodityAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.StarBar;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/14.
 * Email：hengzwdhengzwd@qq.com
 */
public class MHaveBeenCompleteFragment extends BaseFragment<MHaveBeenCompleteContract.Presenter> implements MHaveBeenCompleteContract.View ,View.OnClickListener{

    @Inject
    MHaveBeenCompletePresenter mHaveBeenCompletePresenter;

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
    public static MHaveBeenCompleteFragment newInstance(String order_Id) {
        MHaveBeenCompleteFragment fragment = new MHaveBeenCompleteFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id", order_Id);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public MHaveBeenCompleteContract.Presenter getPresenter() {
        return mHaveBeenCompletePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_maintanceorder_havebeen_complete;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(this);
        tvCopyOrderNumber.setOnClickListener(this);
    }

    @Override
    public void initData() {
        order_id=getArguments().getString("order_id");
        linearLayoutManager=new LinearLayoutManager(BaseApplication.mContext);
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id",order_id);
       // KLog.d("完成",order_id,Constants.token);
        getPresenter().getMaintanceOrderDetail(map);
    }



    @Override
    public void onGetMaintanceOrderDetialSuccess(MaintanceOrderDetailBean bean) {
      //  KLog.d("已完成",bean.getJdata().getAppoint().getNickname(),"/",bean.getJdata().getAppoint().getPhone());
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
        tvOrderStatus.setText("已完成");
        tvOrderTime.setText(bean.getJdata().getOrder().getCreate_time());
        tvNeedToPay.setText("¥"+bean.getJdata().getOrder().getTotal());

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
     //   KLog.e("失败"+failure);
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                _mActivity.onBackPressed();
                break;
            case R.id.tv_copy_order_number:
                ClipboardManager cm = (ClipboardManager) _mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
               // cm.setText(tvOrderNumber.getText());
                cm.setPrimaryClip(ClipData.newPlainText("text", tvOrderNumber.getText()));
                Toast.makeText(BaseApplication.mContext,"已复制到剪切板",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
