package com.carsuper.coahr.mvp.model;


import android.support.annotation.Nullable;

import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.CarBrandBean;
import com.carsuper.coahr.mvp.model.bean.CarMotorBean;
import com.carsuper.coahr.mvp.model.bean.CarSerialBean;
import com.carsuper.coahr.mvp.model.bean.CarHorsePowerBean;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean;
import com.carsuper.coahr.mvp.model.bean.CommodityDetailBean;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateBean;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateDetailBeans;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderBean;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.ConfirmCommodityOrderBean;
import com.carsuper.coahr.mvp.model.bean.ConfirmOrderBean;
import com.carsuper.coahr.mvp.model.bean.CouponBean;
import com.carsuper.coahr.mvp.model.bean.Coupon_Select;
import com.carsuper.coahr.mvp.model.bean.Coupon_Used;
import com.carsuper.coahr.mvp.model.bean.DeleteCarBean;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.bean.EvaluateBean;
import com.carsuper.coahr.mvp.model.bean.ExchangeByStone;
import com.carsuper.coahr.mvp.model.bean.ExchangeDetail;
import com.carsuper.coahr.mvp.model.bean.ExchangeMallList;
import com.carsuper.coahr.mvp.model.bean.ExchangeRe;
import com.carsuper.coahr.mvp.model.bean.GetCoupon;
import com.carsuper.coahr.mvp.model.bean.GetCouponDown;
import com.carsuper.coahr.mvp.model.bean.IntegralCenterBean;
import com.carsuper.coahr.mvp.model.bean.LoginBean;
import com.carsuper.coahr.mvp.model.bean.LogisticsBean;
import com.carsuper.coahr.mvp.model.bean.MainBean;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderBean;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.MyDataAdList;
import com.carsuper.coahr.mvp.model.bean.MyLovelyCarBean;
import com.carsuper.coahr.mvp.model.bean.PaymentSuccessBean;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;
import com.carsuper.coahr.mvp.model.bean.PhoneMessageBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.RecommendServiceBean;
import com.carsuper.coahr.mvp.model.bean.RefundApplyBean;
import com.carsuper.coahr.mvp.model.bean.RefundFormBean;
import com.carsuper.coahr.mvp.model.bean.ReplaceableCommodityBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveCommentBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserInfoBean;
import com.carsuper.coahr.mvp.model.bean.SearchBean;
import com.carsuper.coahr.mvp.model.bean.ServiceOrderCopyBean;
import com.carsuper.coahr.mvp.model.bean.ShoppingCartBean;
import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean;
import com.carsuper.coahr.mvp.model.bean.StationRecommend;
import com.carsuper.coahr.mvp.model.bean.StoreBean;
import com.carsuper.coahr.mvp.model.bean.StoreDetailBean;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateBean;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateDetailBean;
import com.carsuper.coahr.mvp.model.bean.TrankForEvaluateBean;
import com.carsuper.coahr.mvp.model.bean.UserAddressBean;
import com.carsuper.coahr.mvp.model.bean.VerifyTokenBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;


/**
 * Author： hengzwd on 2017/6/1.
 * Email：hengzwdhengzwd@qq.com
 */
public interface ApiService {

    /**
     * 微信登录
     *
     * @param openid
     * @param unionid
     * @param nickname
     * @param headimgurl
     * @param device_token
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/Home/wx_login")
    Call<LoginBean> wxLogin(@Field("phone") String phone, @Field("verify_code") String vcode,@Field("openid") String openid, @Field("unionid") String unionid, @Field("nickname") String nickname, @Field("headimgurl") String headimgurl, @Field("device_token") String device_token);

    /**
     * 解除微信绑定
     *
     * @param token
     * @param uid
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/unset_wx")
    Call<ResultBean> unSet(@Field("token") String token, @Field("uid") String uid);


    /**
     * 微信绑定
     *
     * @param token
     * @param uid
     * @param openid
     * @param nickname
     * @param headimgurl
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/bind_wx")
    Call<ResultBean> bindWx(@Field("token") String token, @Field("uid") String uid, @Field("openid") String openid, @Field("nickname") String nickname, @Field("headimgurl") String headimgurl,@Field("unionid") String unionid);

    /**
     * 手机登录
     *
     * @param phone
     * @param verify_code
     * @param device_token
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/Home/mb_login")
    Call<LoginBean> phoneLogin(@Field("phone") String phone, @Field("verify_code") String verify_code, @Field("device_token") String device_token);

    /**
     * 登出
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/Home/logout")
    Call<ResultBean> loginOut(@Field("token") String token);


    /**
     * 绑定手机
     *
     * @param phone
     * @param vcode
     * @param uid
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("APP/User/bind_phone")
    Call<BindPhoneBean> bindPhone(@Field("phone") String phone, @Field("vcode") String vcode, @Field("uid") String uid, @Field("token") String token);

    /**
     * 验证码
     *
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("APP/Home/get_verify_code")
    Call<PhoneMessageBean> getVerifyCode(@Field("phone") String phone);

    /**
     * 个人信息
     *
     * @param token
     * @param uid
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/get_userinfo")
    Call<PersonInfoBean> getUserInfo(@Field("token") String token, @Field("uid") String uid);


    @FormUrlEncoded
    @POST("/APP/Home/get_login_status")
    Call<VerifyTokenBean> verifyToken(@Field("token") String token);

    @FormUrlEncoded
    @POST("/APP/Query/get_city")
    Call<CityInfoBean> getCity(@Field("token") @Nullable String token);


    @FormUrlEncoded
    @POST("/APP/Query/search_station_general")
    Call<SearchBean> searchAll(@Field("search_key") String token);


    @FormUrlEncoded
    @POST("/APP/Query/get_commodity_list")
    Call<ShoppingMallBean> getCommodityList(@Field("brand") String brand, @Field("order") String order, @Field("sort") String sort, @Field("page") String page, @Field("length") String length);


    @FormUrlEncoded
    @POST("/APP/Query/get_commodity_detail")
    Call<CommodityDetailBean> getCommodityDetail(@Field("c_id") String c_id, @Field("token") String token);


    @FormUrlEncoded
    @POST("/APP/Query/get_station_list")
    Call<StoreBean> getStoreList(@Field("order") String order, @Field("page") String page, @Field("length") String length, @Field("city") String city, @Field("longitude") String longitude, @Field("latitude") String latitude);


    @FormUrlEncoded
    @POST("/APP/Query/get_station_detail")
    Call<StoreDetailBean> getStoreDetail(@Field("s_id") String S_id, @Field("longitude") String longitude, @Field("latitude") String latitude);


    @FormUrlEncoded
    @POST("/APP/Query/initial_data")
    Call<MainBean> getmain(@Field("page") String page);

    /**
     * 门店评价列表
     *
     * @param s_id
     * @param filter
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/Query/get_station_comment_list")
    Call<StoreEvaluateBean> getStoreComments(@Field("s_id") String s_id, @Field("filter") String filter, @Field("token") String token);


    /**
     * 商品评价列表
     *
     * @param c_id
     * @param filter
     * @param token
     * @return
     */

    @FormUrlEncoded
    @POST("/APP/Query/get_commodity_comment_list")
    Call<CommodityEvaluateBean> getCommodityComments(@Field("c_id") String c_id, @Field("filter") String filter, @Field("token") String token);

    //商品评论详情
    @FormUrlEncoded
    @POST("/APP/Query/get_commodity_comment_detail")
    Call<CommodityEvaluateDetailBeans> getCommodityCommentDetail(@Field("so_id") String so_id, @Field("token") String token);

    //门店评论详情
    @FormUrlEncoded
    @POST("/APP/Query/get_station_comment_detail")
    Call<StoreEvaluateDetailBean> getStoreCommentDetail(@Field("ao_id") String ao_id, @Field("token") String token);


    @FormUrlEncoded
    @POST("/APP/Query/set_praise")
    Call<DianZanBean> storeEvaluatedianZan(@Field("ao_id") String ao_id, @Field("token") String token);

    @FormUrlEncoded
    @POST("/APP/Query/set_praise")
    Call<DianZanBean> storeSecondEvaluatedianZan(@Field("ap_id") String ap_id, @Field("token") String token);

    @FormUrlEncoded
    @POST("APP/User/save_station_comment_reply")
    Call<EvaluateBean> storeSecondEvaluate(@Field("ao_id") String ao_id, @Field("token") String token, @Field("comment") String comment);

    @FormUrlEncoded
    @POST("/APP/Query/set_praise")
    Call<DianZanBean> commodityEvaluatedianZan(@Field("so_id") String so_id, @Field("token") String token);

    @FormUrlEncoded
    @POST("/APP/Query/set_praise")
    Call<DianZanBean> commoditySecondEvaluatedianZan(@Field("sp_id") String sp_id, @Field("token") String token);


    @FormUrlEncoded
    @POST("APP/User/save_commodity_comment_reply")
    Call<EvaluateBean> commoditySecondEvaluate(@Field("so_id") String so_id, @Field("token") String token, @Field("comment") String comment);

    @FormUrlEncoded
    @POST("/APP/Query/recommend_service")
    Call<RecommendServiceBean> recommendService(@Field("service") String service, @Field("cs_id") String cs_id, @Field("token") String token);

    /**
     * 预约选择汽车
     * @param token
     * @param cd_id
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/save_user_car")
    Call<SaveUserCarBean> saveUserCarInfo(@Field("token") String token, @Field("cd_id") String cd_id, @Field("motor") String motor,@Field("horsepower") String horsepower);

    @FormUrlEncoded
    @POST("/APP/User/save_user_car_distance")
    Call<ResultBean> saveUserCarDistance(@Field("token") String token, @Field("car_id") String car_id, @Field("distance") String distance);


    @FormUrlEncoded
    @POST("/APP/Query/get_replaceable_commodity")
    Call<ReplaceableCommodityBean> getReplayceCommodity(@Field("service") String service, @Field("cs_id") String cs_id, @Field("page") String page, @Field("length") String length);


    @FormUrlEncoded
    @POST("/APP/Query/get_car_brand")
    Call<CarBrandBean> getCarBrand(@Field("token") String service);


    @FormUrlEncoded
    @POST("/APP/Query/get_car_serial")
    Call<CarSerialBean> getCarSerial(@Field("cb_id") String cb_id);


    /**
     * 发动机型号选择
     * @param cs_id
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/Query/get_car_motor")
    Call<CarMotorBean> getCarMotor(@Field("cs_id") String cs_id);

    /**
     * 发动机马力选择
     * @param cs_id
     * @param motor
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/Query/get_car_horsepower")
    Call<CarHorsePowerBean> getCarHorsePower(@Field("cs_id") String cs_id, @Field("motor") String motor);


    /**
     * 预约保养--提交订单
     * @param token
     * @param c_id
     * @param num
     * @param car_id
     * @param province
     * @param city
     * @param address
     * @param longitude
     * @param latitude
     * @param phone
     * @param date
     * @param time
     * @param total
     * @param payment
     * @param filter_c_id
     * @param filter_num
     * @param s_id
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/save_dating_service_order")
    Call<ConfirmOrderBean> confirmMaintanceOrder(@Field("token") String token, @Field("c_id") String c_id, @Field("num") String num
            , @Field("car_id") String car_id, @Field("province") String province, @Field("city") String city, @Field("address") String address
            , @Field("longitude") String longitude, @Field("latitude") String latitude, @Field("phone") String phone, @Field("date") String date
            , @Field("time") String time, @Field("total") String total, @Field("payment") String payment
            , @Field("filter_c_id") String filter_c_id,@Field("filter_num") String filter_num,@Field("s_id") String s_id
            , @Field("coupon_id") String coupon_id);


    @FormUrlEncoded
    @POST("/APP/User/get_commodity_order_list")
    Call<CommodityOrderBean> getCommodityOrderList(@Field("token") String token, @Field("order_status") String order_status, @Field("page") String page, @Field("length") String length);


    @FormUrlEncoded
    @POST("/APP/User/get_commodity_order_detail")
    Call<CommodityOrderDetailBean> getCommodityOrderDetail(@Field("token") String token, @Field("order_id") String order_id);


    @FormUrlEncoded
    @POST("/APP/User/get_service_order_list")
    Call<MaintanceOrderBean> getMaintanceOrderList(@Field("token") String token, @Field("order_status") String order_status, @Field("page") String page, @Field("length") String length);


    @FormUrlEncoded
    @POST("/APP/User/get_service_order_detail")
    Call<MaintanceOrderDetailBean> getMaintanceOrderDetail(@Field("token") String token, @Field("order_id") String order_id);


    @FormUrlEncoded
    @POST("/APP/User/get_user_car_list")
    Call<MyLovelyCarBean> getMyLoveLyCarList(@Field("token") String token);


    @FormUrlEncoded
    @POST("/APP/User/set_user_car_default")
    Call<ResultBean> setCarPrimary(@Field("token") String token, @Field("car_id") String car_id);


    @FormUrlEncoded
    @POST("/APP/User/del_user_car")
    Call<ResultBean> deleteCar(@Field("token") String token, @Field("car_id") String car_id);


    @FormUrlEncoded
    @POST("/APP/User/get_user_address_list")
    Call<UserAddressBean> getUserAddressList(@Field("token") String token);


    @FormUrlEncoded
    @POST("/APP/User/save_user_address")
    Call<ResultBean> saveUserAddress(@Field("token") String token, @Field("username") String username, @Field("telephone") String telephne, @Field("address") String address, @Field("id") @Nullable String id);


    @Multipart
    @POST("/APP/User/save_suggest")
    Call<ResultBean> saveSuggest(@PartMap Map<String, RequestBody> map, @Part() List<MultipartBody.Part> parts);


    @FormUrlEncoded
    @POST("/APP/User/del_user_address")
    Call<ResultBean> deleteUserAddress(@Field("token") String token, @Field("id") String id);

    @FormUrlEncoded
    @POST("/APP/User/set_user_address")
    Call<ResultBean> setPrimaryAddress(@Field("token") String token, @Field("id") String id);


    @FormUrlEncoded
    @POST("/APP/User/confirm_commodity_order")
    Call<ConfirmCommodityOrderBean> confirmCommodityOrder(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("/APP/User/save_commodity_order")
    Call<ConfirmOrderBean> saveCommodityOrder(@FieldMap Map<String, String> map);


    @Multipart
    @POST("/APP/User/save_userinfo")
    Call<SaveUserInfoBean> saveUserInfo(@PartMap Map<String, RequestBody> map, @Nullable @Part() MultipartBody.Part part);

    @FormUrlEncoded
    @POST("/APP/User/add_to_shopping_cart ")
    Call<ResultBean> addToShoppingCart(@Field("c_id") String c_id, @Field("c_num") String c_num, @Field("token") String token);


    /**
     * 购物车
     * @param token
     * @param page
     * @param length
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/shopping_cart")
    Call<ShoppingCartBean> getShoppingCart(@Field("token") String token, @Field("page") String page, @Field("length") String length);


    /**
     * 删除购物车
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/del_from_shopping_cart")
    Call<DeleteCarBean> del_from_shopping_cart(@Field("token") String token,@Field("c_id") String c_id);

    @FormUrlEncoded
    @POST("/APP/User/get_commodity_logistics")
    Call<LogisticsBean> getCommodityLogistics(@Field("token") String token, @Field("order_id") String order_id);


    @FormUrlEncoded
    @POST("/APP/User/get_point_list")
    Call<IntegralCenterBean> getPointList(@Field("token") String token, @Field("page") String page, @Field("length") String length, @Field("filter") String filter);


    @FormUrlEncoded
    @POST("/APP/User/signin")
    Call<ResultBean> signin(@Field("token") String token);


    @FormUrlEncoded
    @POST("/APP/User/confirm_order_received")
    Call<ResultBean> ensureRecieve(@Field("token") String token, @Field("order_id") String order_id);


    @FormUrlEncoded
    @POST("/APP/User/reminder_order")
    Call<ResultBean> reminderOrder(@Field("token") String token, @Field("order_id") String order_id);


    @Multipart
    @POST("/APP/User/save_comment")
    Call<SaveCommentBean> saveComment(@PartMap Map<String, RequestBody> map, @Part() List<MultipartBody.Part> parts);


    @FormUrlEncoded
    @POST("/APP/User/comment_ending_recommend")
    Call<TrankForEvaluateBean> commentEndingRecommend(@Field("token") String token, @Field("order_id") String order_id, @Field("type") String type);

    @FormUrlEncoded
    @POST("/APP/User/save_commodity_order_cancel")
    Call<ResultBean> cancelCommodityOrder(@Field("token") String token, @Field("order_id") String order_id, @Field("reason") String reason);


    @FormUrlEncoded
    @POST("/APP/User/save_service_order_cancel")
    Call<ResultBean> cancelMaintanceOrder(@Field("token") String token, @Field("order_id") String order_id, @Field("reason") String reason);

    @FormUrlEncoded
    @POST("/APP/User/refund_apply")
    Call<RefundApplyBean> refundApply(@Field("token") String token, @Field("order_id") String order_id);


    @FormUrlEncoded
    @POST("/APP/User/refund_form")
    Call<RefundFormBean> refundForm(@Field("token") String token, @Field("order_id") String order_id, @Field("refund_type") String refund_type);

    @FormUrlEncoded
    @POST("/APP/User/refund_apply_cancel")
    Call<ResultBean> refundCancel(@Field("token") String token, @Field("refund_id") String refund_id);


    @Multipart
    @POST("/APP/User/save_refund")
    Call<ResultBean> saveRefund(@PartMap Map<String, RequestBody> map, @Part() List<MultipartBody.Part> parts);


    @FormUrlEncoded
    @POST("/APP/User/quick_pay")
    Call<QuickPayBean> quickPay(@Field("token") String token, @Field("order_id") String order_id, @Field("order_type") String order_type, @Field("payment") String payment);

    @FormUrlEncoded
    @POST("/APP/User/confirm_service_finished  ")
    Call<ResultBean> confrimServiceFinish(@Field("token") String token, @Field("order_id") String order_id);


    @FormUrlEncoded
    @POST("/APP/User/dating_service_order_copy  ")
    Call<ServiceOrderCopyBean> serviceOrderCopy(@Field("token") String token, @Field("order_id") String order_id);


    @FormUrlEncoded
    @POST("/APP/User/order_ending_recommend")
    Call<PaymentSuccessBean> getEndingRecommend(@Field("token") String token, @Field("order_id") String order_id);

    /**
     * 优惠券
     */

    @FormUrlEncoded
    @POST("/APP/User/get_coupon_list")
    Call<CouponBean> get_coupon_list(@Field("token") String token,@Field("page") String page,@Field("length") String length);


    /**
     * 优惠券使用
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/get_coupon_selected")
    Call<Coupon_Used>get_coupon_selected(@Field("coupon_id") String coupon_id ,@Field("token") String token);

    /**
     * 预约订单页面获取门店信息
     * @param token
     * @param longitude
     * @param latitude
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/get_station_recommend")
    Call<StationRecommend>get_station_recommend(@Field("token") String token, @Field("longitude") String longitude, @Field("latitude") String latitude);


    /**
     * 新优惠券列表
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/get_coupon_all")
    Call<GetCoupon>get_coupon_all(@Field("token") String token);

    /**
     * 优惠券领取接口
     */

    @FormUrlEncoded
    @POST("/APP/User/get_coupon_byself")
    Call<GetCouponDown>get_coupon_byself(@Field("token") String token, @Field("coupon_id") String coupon_id);


    /**
     * 兑换商城列表
     * @param page
     * @param length
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/Query/get_bonus_commodity_list")
    Call<ExchangeMallList> getExchangeMallList(@Field("page") String page ,@Field("length") String length);


    /**
     * 兑换商品详情
     * @param c_id
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/get_bonus_commodity_detail")
    Call<ExchangeDetail> getExchangeDetail(@Field("c_id") String c_id,@Field("token") String token);

    /**
     * 没有登录
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/Query/get_bonus_commodity_detail")
    Call<ExchangeDetail> getExchangeDetailNoLogin(@Field("c_id") String c_id );


    /**
     * 宝石兑换
     * @param c_id
     * @param token
     * @param o_status
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/exchange_bonus_commodity")
    Call<ExchangeByStone> ExchangeByStone(@Field("c_id") String c_id,@Field("token") String token,@Field("o_status") String o_status);

    /**
     * 领取
     * @param c_id
     * @param token
     * @param o_status
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/User/receive_bonus_commodity")
    Call<ExchangeRe> ExchangeRe(@Field("c_id") String c_id,@Field("token") String token,@Field("o_status") String o_status,@Field("code") String code);


    /**
     * 我的页面轮播图
     * @param asid
     * @return
     */
    @FormUrlEncoded
    @POST("/APP/home/get_advertise")
    Call<MyDataAdList> getAdList(@Field("asid") String asid );

}
