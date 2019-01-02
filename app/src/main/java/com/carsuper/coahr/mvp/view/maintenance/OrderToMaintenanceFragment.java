package com.carsuper.coahr.mvp.view.maintenance;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.maintenance.OrderToMaintenanceContract;
import com.carsuper.coahr.mvp.model.bean.RecommendServiceBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;
import com.carsuper.coahr.mvp.presenter.maintenance.OrderToMaintenancePresenter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.myData.LoginFragment;
import com.carsuper.coahr.mvp.view.myData.MyLovelyCarFragment;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.carsuper.coahr.utils.TwoDecimal;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.CommodityCountView;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 * 预约保养首页
 */
public class OrderToMaintenanceFragment extends BaseFragment<OrderToMaintenanceContract.Presenter> implements OrderToMaintenanceContract.View, View.OnClickListener {


    @Inject
    OrderToMaintenancePresenter orderToMaintenancePresenter;
    @BindView(R.id.tittle_bar)
    NormalTittleBar tittleBar;
    @BindView(R.id.tv_mylovelycar)
    TextView tvMylovelycar;
    @BindView(R.id.iv_mylovelycar)
    ImageView ivMylovelycar;
    @BindView(R.id.tv_text_power_type)
    TextView tv_text_power_type;
    @BindView(R.id.tv_displacement)
    TextView tvDisplacement;
    @BindView(R.id.et_drivedistance)
    EditText etDrivedistance;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.iv_commodity_img)
    ImageView ivCommodityImg;
    @BindView(R.id.tv_commodity_info)
    TextView tvCommodityInfo;
    @BindView(R.id.tv_commodity_price)
    TextView tvCommodityPrice;
    @BindView(R.id.tv_commodity_count)
    TextView tvCommodityCount;
    @BindView(R.id.rl_commodity)
    RelativeLayout rlCommodity;
    @BindView(R.id.tv_change)
    TextView tvChange;
    @BindView(R.id.rl_edit_commodity)
    RelativeLayout rlEditCommodity;
    @BindView(R.id.rl_service_oil)
    RelativeLayout rlServiceOil;
    @BindView(R.id.rl_service_tyre)
    RelativeLayout rlServiceTyre;
    @BindView(R.id.tv_totalprice)
    TextView tvTotalprice;
    @BindView(R.id.tv_submit_order)
    TextView tvSubmitOrder;
    @BindView(R.id.tv_service_tittle)
    TextView tvServiceTittle;

    @BindView(R.id.ccv_commodity_count)
    CommodityCountView ccvCommodityCount;
    /**
     * 滤芯
     */
    @BindView(R.id.rl_element_service)
    RelativeLayout rl_element_service;
    @BindView(R.id.tv_element_tittle)
    TextView tv_element_tittle; //标题
    @BindView(R.id.tv_element_save)
    TextView tv_element_save; //编辑滤芯
    @BindView(R.id.element)
    RelativeLayout element;   //控制隐藏和显示
    @BindView(R.id.iv_element_img)
    ImageView iv_element_img;  //滤芯图片
    @BindView(R.id.tv_element_info)
    TextView tv_element_info;  //滤芯商品名
    @BindView(R.id.tv_element_price)
    TextView tv_element_price;  //滤芯价格
    @BindView(R.id.tv_element_count)
    TextView tv_element_count;   //滤芯个数
    @BindView(R.id.rl_element_count)
    RelativeLayout rl_element_count; //点击编辑时显示个数
    @BindView(R.id.rl_element_msg)
    RelativeLayout rl_element_msg;   //显示滤芯信息
    @BindView(R.id.ccv_element_count)
    CommodityCountView ccv_element_count; //滤芯个数
    @BindView(R.id.tv_elemrnt_change)
    TextView tv_elemrnt_change;  //滤芯更换


    private String cs_id;//车系
    private String car_id;
    private String carSerialName;//车系名称
    private String cd_id; //具体车型

    private String displacement;//排量

    private String mileage;//里程

    private String service = "oil";//页面默认服务  oil

    private String c_id;//选择的商品id


    private float commodityPrice = 0; //机油、轮胎单价

    private float TotalcommodityPrice=0;//机油、轮胎中价

    private String commodityImg;   //轮胎、机油商品图片


    private int type;  //区别于滤芯和机油、轮胎

    private float TotalElementPrice = 0;  //滤芯的总价

    private float elementPrice = 0; //滤芯单价
    private float TotalOther = 0; //其余的总价

    private String filter_c_id;  //滤芯id
    private int filter_num;   //滤芯数量

    private String filter_name; //滤芯名字

    private String filter_img;   //滤芯图片

    public static final int RESULT_REPLACEABLECOMMODITY_ELEMENT = 0;
    public static final int RESULT_MYLOVELYCAR = 1;
    public static final int RESULT_SELECTCAR = 2;


    public static OrderToMaintenanceFragment newInstance() {
        return new OrderToMaintenanceFragment();
    }

    @Override
    public OrderToMaintenanceContract.Presenter getPresenter() {
        return orderToMaintenancePresenter;
    }

    //从栈顶回退到此的时候，接收到额bundle,并重新获取推荐服务
    @Override
    public void recieveData(Bundle bundle) {
        cs_id = bundle.getString("cs_id");
//        cd_id = bundle.getString("cd_id");
//        displacement = bundle.getString("displacement");
//        carSerialName= bundle.getString("carSerialName");
////        mileage= bundle.getString("mileage");
//        tvMylovelycar.setText(carSerialName);
//        tvDisplacement.setText(displacement+"L");


        Map map = new HashMap();
        map.put("cs_id", cs_id);
        map.put("service", service);
        map.put("token", Constants.token);
        getPresenter().recommendService(map);

        /**
         * 访问滤芯接口
         */
    }

    @Override
    public int bindLayout() {
        return R.layout.layout_maintance;
    }

    @Override
    public void initView() {

        tittleBar.getTvTittle().setText("预约保养");
        tittleBar.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        tvChange.setOnClickListener(this);
        ivMylovelycar.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        rlServiceOil.setOnClickListener(this);
        rlServiceTyre.setOnClickListener(this);
        tvSubmitOrder.setOnClickListener(this);

        /**
         * 滤芯
         */
        tv_element_save.setOnClickListener(this);
        tv_elemrnt_change.setOnClickListener(this);

//        ccvCommodityCount.setOnCountChangeListener(new CommodityCountView.onCountChangeListener() {
//            @Override
//            public void onCountChange(int count) {
//                tvTotalprice.setText("¥" + (ccvCommodityCount.getCount()*commodityPrice + Constants.SERVICE_PRICE));
//
//            }
//        });

    }

    @Override
    public void initData() {
        /**
         * 默认访问oil接口
         */
        Map map = new HashMap();
        map.put("cs_id", cs_id);
        map.put("service", service);
        map.put("token", Constants.token);
        getPresenter().recommendService(map);

    }


    @Override
    public void onClick(View v) {
        Map map = new HashMap();
        switch (v.getId()) {
            case R.id.iv_mylovelycar:
                if (haslogin()) {
                   // start(CarBrandFragment.newInstance(Constants.ORDERTOMAINTENANCEFRAGMENT));
                    startForResult(MyLovelyCarFragment.newInstance(Constants.ORDERTOMAINTENANCEFRAGMENT),RESULT_MYLOVELYCAR);
                } else {
                    Toast.makeText(BaseApplication.mContext, "请登录后再试", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_change:  //机油或轮胎更换渠道
                if (service.equals("filter")) {
                    service = "oil";
                }
                startForResult(CommodityForMaintanceFragment.newInstance(service, cs_id, type = 1), RESULT_REPLACEABLECOMMODITY_ELEMENT);
                break;
            case R.id.tv_save:
                if (tvSave.getText().equals("编辑")) {
                    tvSave.setText("保存");
                    rlCommodity.setVisibility(View.GONE);
                    rlEditCommodity.setVisibility(View.VISIBLE);
                } else {
                    tvSave.setText("编辑");
                    rlEditCommodity.setVisibility(View.GONE);
                    rlCommodity.setVisibility(View.VISIBLE);
                    tvCommodityCount.setText("x" + ccvCommodityCount.getCount());
                    TotalcommodityPrice = ccvCommodityCount.getCount() * commodityPrice;
                    TotalOther= TotalElementPrice + TotalcommodityPrice;
                    double twoDecimal = TwoDecimal.getTwoDecimal(TotalOther);
                    tvTotalprice.setText("¥" +twoDecimal);
                }
                KLog.d("计价一机油",commodityPrice,TotalcommodityPrice);
                break;
            case R.id.rl_service_oil:
                rlServiceOil.setVisibility(View.GONE);
                element.setVisibility(View.VISIBLE);
                rlServiceTyre.setVisibility(View.VISIBLE);
                rl_element_service.setVisibility(View.VISIBLE);
                tvServiceTittle.setText("机油服务");
                service = "oil";
                map.put("cs_id", cs_id);
                map.put("service", service);
                map.put("token", Constants.token);
                getPresenter().recommendService(map);
                break;
            case R.id.rl_service_tyre:
                rlServiceTyre.setVisibility(View.GONE);
                element.setVisibility(View.GONE);
                rl_element_service.setVisibility(View.GONE);
                rlServiceOil.setVisibility(View.VISIBLE);
                tvServiceTittle.setText("轮胎服务");
                elementPrice = 0;
                TotalElementPrice = 0;
                service = "tyre";
                map.put("cs_id", cs_id);
                map.put("service", service);
                map.put("token", Constants.token);
                getPresenter().recommendService(map);
                break;
            case R.id.tv_submit_order:
                if (TextUtils.isEmpty(etDrivedistance.getText().toString())) {
                    Toast.makeText(BaseApplication.mContext, "请填写行驶里程", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(tvCommodityInfo.getText().toString())) {
                    Toast.makeText(BaseApplication.mContext, "数据为空请刷新后再试", Toast.LENGTH_LONG).show();
                    return;
                }
                if (haslogin()) {
                    if (tvMylovelycar.getText() !=null && !tvMylovelycar.getText().equals("")) {
                        map.put("token", Constants.token);
                        map.put("car_id", car_id);
                        map.put("distance", etDrivedistance.getText().toString());
                        getPresenter().saveUserCarDistance(map);
                    } else {
                        Toast.makeText(BaseApplication.mContext, "请先选择爱车后提交订单", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(BaseApplication.mContext, "请先登录后提交订单", Toast.LENGTH_LONG).show();
                    start(LoginFragment.newInstance(Constants.ORDERTOMAINTENANCEFRAGMENT));
                }
                break;
            case R.id.tv_element_save:  //滤芯编辑
                if (tv_element_save.getText().equals("编辑")) {
                    tv_element_save.setText("保存");
                    rl_element_count.setVisibility(View.VISIBLE);
                    rl_element_msg.setVisibility(View.GONE);
                } else {
                    tv_element_save.setText("编辑");
                    rl_element_count.setVisibility(View.GONE);
                    rl_element_msg.setVisibility(View.VISIBLE);
                    tv_element_count.setText("x" + ccv_element_count.getCount());
                    filter_num=ccv_element_count.getCount();
                    TotalElementPrice = ccv_element_count.getCount() * elementPrice;  //当前滤芯价格
                    TotalOther=  TotalElementPrice + TotalcommodityPrice;
                    double twoDecimal = TwoDecimal.getTwoDecimal(TotalOther);
                    tvTotalprice.setText("¥" + twoDecimal);
                }
            KLog.d("计价滤芯11",elementPrice,TotalElementPrice);
                break;
            case R.id.tv_elemrnt_change:  //滤芯更换渠道
                service = "filter";
                startForResult(CommodityForMaintanceFragment.newInstance(service, cs_id, type = 2), RESULT_REPLACEABLECOMMODITY_ELEMENT);
                break;
        }
    }


    @Override
    public void onRecommendServiceSuccess(RecommendServiceBean bean) {
        c_id = bean.getJdata().getCommodity().getC_id();

        if (bean.getJdata().getMycar() != null) {
            car_id = bean.getJdata().getMycar().getCar_id();
            tvMylovelycar.setText(bean.getJdata().getMycar().getSerials());
            tvDisplacement.setText(bean.getJdata().getMycar().getMotor());
            tv_text_power_type.setText(bean.getJdata().getMycar().getHorsepower());

        }

        Imageloader.loadImage(bean.getJdata().getCommodity().getC_thumbnail(), ivCommodityImg);
        commodityImg = bean.getJdata().getCommodity().getC_thumbnail();
        tvCommodityInfo.setText(bean.getJdata().getCommodity().getC_name());

        ccvCommodityCount.setCount(1);
        tvCommodityCount.setText("x" + 1);
//if (bean.getJdata().getCommodity().getC_price()!=null && !bean.getJdata().getCommodity().equals("") )
        tvCommodityPrice.setText("¥" + bean.getJdata().getCommodity().getC_price());

        commodityPrice = Float.parseFloat(bean.getJdata().getCommodity().getC_price() != null && !bean.getJdata().getCommodity().getC_price().equals("") ? bean.getJdata().getCommodity().getC_price() : "0");

        TotalcommodityPrice=commodityPrice*1;
        /**
         * 滤芯数据
         */
        if (bean.getJdata().getCommodity_filter() !=null && bean.getJdata().getCommodity_filter().getC_id() !=null){
            filter_img=bean.getJdata().getCommodity_filter().getC_thumbnail();
            filter_name=bean.getJdata().getCommodity_filter().getC_name();
            Imageloader.loadImage(filter_img, iv_element_img);
            tv_element_info.setText(filter_name);
            tv_element_price.setText("¥" + bean.getJdata().getCommodity_filter().getC_price());
            tv_element_count.setText("x" + 1);
            ccv_element_count.setCount(1);
            filter_c_id=bean.getJdata().getCommodity_filter().getC_id();
            filter_num=1;
            elementPrice = Float.parseFloat(bean.getJdata().getCommodity_filter().getC_price() != null && !bean.getJdata().getCommodity_filter().getC_price().equals("") ? bean.getJdata().getCommodity_filter().getC_price() : "0");
            TotalElementPrice=elementPrice*1;
        } else {
            filter_img=null;
            filter_name=null;
            elementPrice=0;
            TotalElementPrice=0;
            filter_c_id=null;
            filter_num=0;
        }
            TotalOther=(TotalcommodityPrice+TotalElementPrice);
            tvTotalprice.setText("¥" + TotalOther);

    }

    @Override
    public void onRecommendServiceFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSaveUserCarDistanceSuccess(ResultBean bean) {
        if (bean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext, bean.getJdata().getJmsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BaseApplication.mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
        }
        start(ConfirmMaintanceOrderFragment.newInstance(c_id, ccvCommodityCount.getCount(), car_id, commodityImg, tvCommodityInfo.getText().toString(), commodityPrice,filter_c_id,filter_num,elementPrice,filter_name,filter_img));

    }

    @Override
    public void onSaveUserCarDistanceFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetFilterSuccess(RecommendServiceBean bean) {

    }

    @Override
    public void onGetFilterFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, "获取滤芯失败", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (data != null) {
            switch (resultCode) {
                case RESULT_REPLACEABLECOMMODITY_ELEMENT:
                    if (data.getInt("type") == 1) {  //机油、轮胎回调
                        commodityPrice = Float.parseFloat(data.getString("price"));
                        tvCommodityPrice.setText("¥" + commodityPrice);
                        c_id = data.getString("c_id");
                        commodityImg = data.getString("commodity_url");
                        Imageloader.loadImage(commodityImg, ivCommodityImg);
                        tvCommodityInfo.setText(data.getString("name"));

                    } else if (data.getInt("type") == 2) {  //滤芯回调
                        elementPrice = Float.parseFloat(data.getString("price"));
                        tv_element_price.setText("¥" + elementPrice);
                        filter_c_id = data.getString("c_id");
                        filter_img = data.getString("commodity_url");
                        filter_name=data.getString("name");
                        Imageloader.loadImage(filter_img, iv_element_img);
                        tv_element_info.setText(filter_name);
                    }
                    KLog.d("计价回调",elementPrice,commodityPrice);
                    break;
                case RESULT_MYLOVELYCAR:
                    cs_id = data.getString("cs_id");
//        cd_id = bundle.getString("cd_id");
//        displacement = bundle.getString("displacement");
//        carSerialName= bundle.getString("carSerialName");
////        mileage= bundle.getString("mileage");
//        tvMylovelycar.setText(carSerialName);
//        tvDisplacement.setText(displacement+"L");


                    Map map = new HashMap();
                    map.put("cs_id", cs_id);
                    map.put("service", service);
                    map.put("token", Constants.token);
                    getPresenter().recommendService(map);

                    break;
            }
        }
    }
}
