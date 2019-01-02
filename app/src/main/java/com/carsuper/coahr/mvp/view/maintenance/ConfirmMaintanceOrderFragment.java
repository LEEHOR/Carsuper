package com.carsuper.coahr.mvp.view.maintenance;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.baidu.location.BDLocation;
import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.maintenance.ConfirmMaintanceOrderContract;
import com.carsuper.coahr.mvp.model.bean.AlyPayResult;
import com.carsuper.coahr.mvp.model.bean.ConfirmOrderBean;
import com.carsuper.coahr.mvp.model.bean.StationRecommend;
import com.carsuper.coahr.mvp.presenter.maintenance.ConfirmMaintanceOrderPresenter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.base.FragmentBaiduListener;
import com.carsuper.coahr.mvp.view.myData.MyCoupon.CouponViewPagerFragment;
import com.carsuper.coahr.utils.Permission.OnRequestPermissionListener;
import com.carsuper.coahr.utils.Permission.RequestPermissionUtils;
import com.carsuper.coahr.utils.StringUtils;
import com.carsuper.coahr.utils.TwoDecimal;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.StarBar;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.socks.library.KLog;
import com.jzxiang.pickerview.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 * 预约订单确认
 */
public class ConfirmMaintanceOrderFragment extends BaseFragment<ConfirmMaintanceOrderContract.Presenter> implements ConfirmMaintanceOrderContract.View, View.OnClickListener, OnDateSetListener {


    @Inject
    ConfirmMaintanceOrderPresenter confirmMaintanceOrderPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tv_current_location)
    TextView tvCurrentLocation;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_phone_number)
    EditText et_phone_number;
    @BindView(R.id.iv_date_go)
    ImageView ivDateGo;
    @BindView(R.id.iv_time_go)
    ImageView ivTimeGo;
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
    @BindView(R.id.tv_commodity_money)
    TextView tvCommodityMoney;
    @BindView(R.id.tv_service_money)
    TextView tvServiceMoney;
    @BindView(R.id.tv_totalprice)
    TextView tvTotalprice;
    @BindView(R.id.tv_submit_order)
    TextView tvSubmitOrder;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.rl_date)
    RelativeLayout rlDate;
    @BindView(R.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rdg_pay)
    RadioGroup rdgPay;

    /**
     * 滤芯
     */
    @BindView(R.id.rl_filter)
    RelativeLayout rl_filter;
    @BindView(R.id.iv_filter_img)
    ImageView  iv_filter_img;
    @BindView(R.id.tv_filter_info)
    TextView  tv_filter_info;
    @BindView(R.id.tv_filter_price)
    TextView  tv_filter_price;
    @BindView(R.id.tv_filter_count)
    TextView  tv_filter_count;

    /**
     * 门店
     */
    @BindView(R.id.iv_store_img)
    ImageView iv_store_img;  //门店图片
    @BindView(R.id.tv_store_info)
    TextView tv_store_info;   //门店名
    @BindView(R.id.tv_store_address)
    TextView tv_store_address; //门店地址
    @BindView(R.id.tv_store_distance)
    TextView tv_store_distance;  //门店距离
    @BindView(R.id.tv_store_star)
    StarBar tv_store_star;  //门店评价

    /**
     * 优惠券
     */
    @BindView(R.id.rl_select_coupon)
    RelativeLayout rl_select_coupon;
    @BindView(R.id.tv_coupon)
    TextView tv_coupon;
    @BindView(R.id.tv_coupon_money)
    TextView tv_coupon_money;


    private String c_Id;
    private int num;
    private String car_id;
    private float commodityPrice;  //机油、轮胎单价
    private float totalPrice_comodity;  //首页传来机油、轮胎总价
    private double Last_coupon_price;  //最后的总价
    private double Last_Price; //记录总价用于优惠券

    private String filter_id;
    private int filter_num;
    private  float filter_price;  //滤芯单价
    private String filter_name;
    private String filter_img;
    private  float  total_filter_price; //滤芯总价
    private  float  total_filter_oil_tyre; //机油、轮胎和滤芯的价格
    private String distance;
    private Double disCount;  //折扣
    private boolean isGetStationRecommendSuccess=false;
    private  String fee_other;
    private String s_id;

    public static final int REQUESTCODE=100;

    private TimePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    SimpleDateFormat sfDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sfTime = new SimpleDateFormat("HH:mm:ss");
    private String payType;//支付方式

    private  String type;   //满减还是免优惠

    private double servicePrice;//当前里程数服务费

    private double TotalServicePrice;//总服务费
    private String coupon_id="0";


    public static ConfirmMaintanceOrderFragment newInstance(String c_id, int num, String car_id, String commodityImg, String c_name, float price,String filter_id,int filter_num,float filter_price,
                                                            String filter_name,String filter_img) {
        ConfirmMaintanceOrderFragment fragment = new ConfirmMaintanceOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("c_id", c_id);
        bundle.putInt("num", num);
        bundle.putString("car_id", car_id);
        bundle.putString("commodityImg", commodityImg);
        bundle.putString("c_name", c_name);
        bundle.putFloat("price", price);
        bundle.putString("filter_c_id",filter_id);
        bundle.putInt("filter_num",filter_num);
        bundle.putFloat("filter_price",filter_price);
        bundle.putString("filter_name",filter_name);
        bundle.putString("filter_img",filter_img);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public ConfirmMaintanceOrderContract.Presenter getPresenter() {
        return confirmMaintanceOrderPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_confirm_maintance_order;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(this);
        ivLocation.setOnClickListener(this);
        rlDate.setOnClickListener(this);
        rlTime.setOnClickListener(this);
        tvSubmitOrder.setOnClickListener(this);
        rl_select_coupon.setOnClickListener(this);
        rdgPay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_zfb:
                        payType = "ali";
                        break;
                    case R.id.rb_wx:
                        payType = "wx";
                        break;
                }
            }
        });
        initTimePickerDialog();
    }

    @Override
    public void initData() {
        FragmentLocationStart();
       // getPresenter().startLocation();  //百度定位在Fragment中无效
        c_Id = getArguments().getString("c_id");
        num = getArguments().getInt("num");
        car_id = getArguments().getString("car_id");
        commodityPrice = getArguments().getFloat("price");
        totalPrice_comodity = num * commodityPrice;
        et_phone_number.setText(Constants.phone);

        //滤芯
         filter_price = getArguments().getFloat("filter_price");
         filter_id = getArguments().getString("filter_c_id");
         filter_num = getArguments().getInt("filter_num");
         filter_name = getArguments().getString("filter_name");
         filter_img = getArguments().getString("filter_img");
         if (filter_id !=null){  //滤芯价格显示
             rl_filter.setVisibility(View.VISIBLE);
             total_filter_price=filter_price*filter_num;
             Imageloader.loadImage(filter_img,iv_filter_img);
             tv_filter_info.setText(filter_name);
             double filter_price = TwoDecimal.getTwoDecimal(total_filter_price);
             tv_filter_price.setText("¥" + filter_price);
             tv_filter_count.setText(String.valueOf(num));

         } else {
             rl_filter.setVisibility(View.GONE);
         }
        total_filter_oil_tyre=total_filter_price+totalPrice_comodity;
        Imageloader.loadImage(getArguments().getString("commodityImg"), ivCommodityImg);
        tvCommodityInfo.setText(getArguments().getString("c_name"));
        tvCommodityPrice.setText(commodityPrice + "");
        tvCommodityCount.setText("x" + num);
        servicePrice=0;
        TotalServicePrice=Constants.COMMODITY_SERVICE_PRICE+servicePrice;
        tvServiceMoney.setText("+¥" + TotalServicePrice);  //服务费
        double commodity_price = TwoDecimal.getTwoDecimal(total_filter_oil_tyre);
        tvCommodityMoney.setText("¥" + commodity_price);    //机油、轮胎价格显示
        double filter_commodity_price = TwoDecimal.getTwoDecimal((total_filter_oil_tyre+total_filter_price));
        Last_coupon_price=filter_commodity_price;
        tvTotalprice.setText("¥" + Last_coupon_price);
        getLocationPermission();

    }


    @Override
    public void onConfirmOrderSuccess(final ConfirmOrderBean bean) {
        //支付订单
        Constants.LAST_PAYING_ORDERID = bean.getJdata().getOrder();
        if (payType.equals("ali")) {
            //最后支付页面
            Constants.LAST_PAYING_PAGER = "预约保养";
            toAliPay(bean.getJdata().getOrder_string());
        } else if (payType.equals("wx")) {
            if (bean.getJdata().getOrder_json().getReturn_code().equals("SUCCESS")) {
                Constants.LAST_PAYING_PAGER = "预约保养";
                toWXPay(bean.getJdata().getOrder_json());
            } else {
                Toast.makeText(BaseApplication.mContext, bean.getJdata().getOrder_json().getReturn_msg(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    /**
     * baseFragment定位
     */
    private void baiduLocation(){
        setFragmentBaidu(new FragmentBaiduListener() {
            @Override
            public void baiduLocationSuccess(BDLocation location) {
                Constants.latitude = location.getLatitude();
                Constants.longitude = location.getLongitude();
                String a[] = location.getAddrStr().split("-");
                Constants.locationDistrict = location.getDistrict();
                Constants.locationProvince = location.getProvince();
                Constants.locationAddress = a[0];
                Constants.locationcity = location.getCity();
                Constants.locationStreet=location.getStreet();
                if (TextUtils.isEmpty(Constants.pointCity)) {
                    Constants.pointCity = StringUtils.getCityName(Constants.locationcity);
                }
                tvCurrentLocation.setText(Constants.locationProvince + Constants.locationcity + Constants.locationDistrict+ Constants.locationStreet);

                /**
                 * 访问门店信息接口
                 */
                Map map=new HashMap();
                map.put("token",Constants.token);
                map.put("longitude",String.valueOf(location.getLongitude()));
                map.put("latitude",String.valueOf(location.getLatitude()));
                getPresenter().onGetStationRecommend(map);
            }

            @Override
            public void baiduLocationFail(int locType) {
                Toast.makeText(BaseApplication.mContext, "定位失败，请开启权限，保持网络通畅"+locType ,Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onConfirmOrderFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, "提交订单失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void onGetStationRecommendSuccess(StationRecommend stationRecommend) {

        s_id=stationRecommend.getJdata().getStation().getS_id();
        Imageloader.loadImage(stationRecommend.getJdata().getStation().getPic1(),iv_store_img);
        tv_store_info.setText(stationRecommend.getJdata().getStation().getS_name());
        tv_store_address.setText(stationRecommend.getJdata().getStation().getS_address());
        tv_store_distance.setText(String.valueOf(stationRecommend.getJdata().getStation().getDistance())+"km");
        distance=String.valueOf(stationRecommend.getJdata().getStation().getDistance());
        isGetStationRecommendSuccess=true;
        fee_other = stationRecommend.getJdata().getFee_other();
       // servicePrice = Double.parseDouble(fee_other != null && !fee_other.equals("") ? fee_other : "0");

         servicePrice= (int) Double.parseDouble(distance);
        double twoDecimal = TwoDecimal.getTwoDecimal((Constants.COMMODITY_SERVICE_PRICE + servicePrice));
        TotalServicePrice=twoDecimal;
        tvServiceMoney.setText("+¥" + twoDecimal);  //服务费
        double filter_commodity_price = TwoDecimal.getTwoDecimal((total_filter_oil_tyre+twoDecimal));
        Last_coupon_price=filter_commodity_price;
        Last_Price=Last_coupon_price;
        tvTotalprice.setText("¥" + Last_coupon_price);
        //打星
        tv_store_star.setStarMark(stationRecommend.getJdata().getStation().getLevel_score() !=null &&
                !stationRecommend.getJdata().getStation().getLevel_score().equals("")?
                Float.parseFloat(stationRecommend.getJdata().getStation().getLevel_score()):
                Float.parseFloat("0"));
       /* if (!TextUtils.isEmpty()) {
            tv_store_star.setStarMark(Float.parseFloat());
        }*/
    }

    @Override
    public void onGetStationRecommendFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_left:
                _mActivity.onBackPressed();
            case R.id.iv_location:
               // getPresenter().startLocation();  //Fragment百度定位无效
                FragmentLocationStart();
                break;
            case R.id.rl_date:
                datePickerDialog.show(getFragmentManager(), "datePickerDialog");
                break;
            case R.id.rl_time:
                timePickerDialog.show(getFragmentManager(), "timePickerDialog");
                break;
            case R.id.tv_submit_order:
                if (TextUtils.isEmpty(etAddress.getText().toString())) {
                    Toast.makeText(BaseApplication.mContext, "请输入详细地址", Toast.LENGTH_LONG).show();
                    break;
                }
                if (TextUtils.isEmpty(et_phone_number.getText().toString())) {
                    Toast.makeText(BaseApplication.mContext, "请输入手机号码", Toast.LENGTH_LONG).show();
                    break;
                }
                if (TextUtils.isEmpty(tvTime.getText())) {
                    Toast.makeText(BaseApplication.mContext, "预约时间不能为空", Toast.LENGTH_LONG).show();
                    break;
                }
                if (TextUtils.isEmpty(tvDate.getText())) {
                    Toast.makeText(BaseApplication.mContext, "预约日期不能为空", Toast.LENGTH_LONG).show();
                    break;
                }
                if (TextUtils.isEmpty(payType)) {
                    Toast.makeText(BaseApplication.mContext, "请选择支付方式", Toast.LENGTH_LONG).show();
                    break;
                }

                Map map = new HashMap();
                double twoDecimal = TwoDecimal.getTwoDecimal(Last_coupon_price);
                map.put("token", Constants.token);
                map.put("c_id", c_Id);
                map.put("num", num + "");
                map.put("car_id", car_id);
                map.put("province", Constants.locationProvince);
                map.put("city", Constants.locationcity);
                map.put("address", etAddress.getText().toString());
                map.put("longitude", Constants.longitude + "");
                map.put("latitude", Constants.latitude + "");
                map.put("phone", Constants.phone);
                map.put("data", tvDate.getText());
                map.put("time", tvTime.getText());
                map.put("total", String.valueOf(twoDecimal));
                map.put("payment", payType);
                map.put("filter_c_id",filter_id);
                map.put("filter_num",String.valueOf(filter_num));
                map.put("s_id",s_id);
                map.put("coupon_id",coupon_id);
                getPresenter().confirmMaintanceOrder(map);
                break;
            case R.id.rl_select_coupon: //选择优惠券
                if (isGetStationRecommendSuccess){
                    start(CouponViewPagerFragment.newInstance(Constants.ConfirmMaintanceOrderFragment,distance,Last_Price));
                } else {
                    Toast.makeText(BaseApplication.mContext,"门店尚未加载成功",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }


    private void initTimePickerDialog() {
        KLog.e(TAG, "---initTimePickerDialog---pre---");
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        long fourHour = 60 * 60 * 4L * 1000;
        datePickerDialog = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("预约日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .build();


        timePickerDialog = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("预约时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(System.currentTimeMillis() + fourHour)
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .build();

    }


    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

        Calendar selectTime = Calendar.getInstance();
        selectTime.setTimeInMillis(millseconds);

        Calendar RI = Calendar.getInstance();
        RI.setTimeInMillis(System.currentTimeMillis());
        if (timePickerView == datePickerDialog) {
            if (judgeDate(millseconds)) {
                tvDate.setText(getDateToString(sfDate, millseconds));
            } else {
                Toast.makeText(BaseApplication.mContext, "当前日期不合规", Toast.LENGTH_LONG).show();
            }

        }
        if (timePickerView == timePickerDialog) {

            tvTime.setText(getDateToString(sfTime, millseconds));
        }
    }

    public String getDateToString(SimpleDateFormat sf, long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    /**
     * 判断日期是否为同一天
     *
     * @param millseconds
     */
    private Boolean judgeDate(Long millseconds) {
        //获取当前时间
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTimeInMillis(System.currentTimeMillis());

        //选择的时间
        Calendar selectTime = Calendar.getInstance();
        selectTime.setTimeInMillis(millseconds);
        if (rightNow.get(Calendar.YEAR) == selectTime.get(Calendar.YEAR)) {  //同年
            if (rightNow.get(Calendar.MONTH) == selectTime.get(Calendar.MONTH)) {  //同月
                if (rightNow.get(Calendar.DATE) <=selectTime.get(Calendar.DATE)) {   //选择的日期要大于当前日期
                    if (rightNow.get(Calendar.DATE)==selectTime.get(Calendar.DATE)){   //用来判断时间

                    }
                    return true;
                }
            } else //选择的月份大于当前月份
// 选择的月份小于当前月份
                return rightNow.get(Calendar.MONTH) < selectTime.get(Calendar.MONTH);

        } else //选择的年份大于当前年份
            return rightNow.get(Calendar.YEAR) < selectTime.get(Calendar.YEAR);
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterLocation();
    }


    @Override
    public void recieveData(Bundle bundle) {

        coupon_id = bundle.getString("coupon_id");
        tv_coupon.setText(bundle.getString("coupon_name"));

        disCount = Double.parseDouble(bundle.getString("discount"));

        double amount = Double.parseDouble(bundle.getString("amount"));
        type = bundle.getString("type");
         if (type .equals("1")){     //满减
             if (total_filter_oil_tyre>=amount){
              total_filter_oil_tyre= (float) (total_filter_oil_tyre-disCount);
                 Last_coupon_price= total_filter_oil_tyre+TotalServicePrice;
                 tv_coupon_money.setText("-¥"+bundle.getString("discount"));
             }  else {
                 Toast.makeText(BaseApplication.mContext, "条件不满足无法使用", Toast.LENGTH_LONG).show();
             }
         }

         if (type.equals("2")){
             Last_coupon_price=total_filter_oil_tyre;
             tv_coupon_money.setText("-¥"+TotalServicePrice);
         }

        double twoDecimal = TwoDecimal.getTwoDecimal(Last_coupon_price);
        tvTotalprice.setText("¥" + twoDecimal);
    }

    /**
     * 动态获取权限
     */
    private void getLocationPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RequestPermissionUtils.requestPermission(getActivity(), new OnRequestPermissionListener() {
                @Override
                public void PermissionSuccess(List<String> permissions) {
                    baiduLocation();
                }

                @Override
                public void PermissionFail(List<String> permissions) {
                    Toast.makeText(getActivity(), "获取权限失败", Toast.LENGTH_LONG).show();
                }

                @Override
                public void PermissionHave() {
                    baiduLocation();
                }
            }, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE);

        } else {
            baiduLocation();
        }

    }
}
