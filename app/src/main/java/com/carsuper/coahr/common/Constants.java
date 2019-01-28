package com.carsuper.coahr.common;

import com.carsuper.coahr.utils.StoreSpaceUtils;

import java.security.spec.RSAOtherPrimeInfo;

/**
 * Author： hengzwd on 2018/6/5.
 * Email：hengzwdhengzwd@qq.com
 */
public class Constants {

    //网络访问基地址
//    public static String BASE_URL = "http://shop.ksuper.coahr.com/";
    public static String BASE_URL = "https://shop.carsuper.cn/";
//    public static String BASE_URL = "https://test.carsuper.cn";

//    public static String BASE_URL = "http://shop.carsuper.cn/";
//    public static String BASE_URL = "http://www.baidu.com/";

    //网络访问超时时间
    public static final int DEFAULT_TIMEOUT = 5;

    //加入我们
    public static final String jionUsUrl="https://a.xiumi.us/stage/v5/3GDjK/94462290";

    //首页新闻轮播
    public static final String newsUrl="https://shop.carsuper.cn/Home/news/index?id=";

    //首页banner图链接
    public  static  final  String bannerUrl="https://shop.carsuper.cn/home/index/banner?id=";
    //首页论坛地址
    public static final String TribuneUrl="http://shop.carsuper.cn/H5/index.html";
    //登录后返回token
    public static String token = "";
    //登录返回Uid
    public static String uid = "";
    //登录使用的手机号码
    public static String phone = "";
    //头像地址
    public static String touxiang ="";

    //昵称
    public  static  String nickname="";

    //UMENG相对手机的devicestoken
    public static String devicestoken;

    //是否需要自杀进程以便打补丁
    public static boolean isKill=true;


    /**
     * 支付成功的跳转用到
     */

    //正在支付的订单编号：
    public static  String LAST_PAYING_ORDERID = "";
    //正在支付的页面
    public static  String LAST_PAYING_PAGER="";

    //正在分享
    public  static String ShareNow="";

    //是否验证过token
    public static boolean has_verify_token = false ;

    //商品订单服务费
    public static final int   COMMODITY_SERVICE_PRICE = 50;

    //保养订单服务费
    public static final int  MAINTANCE_SERVICE_PRICE = 200;

    //微信开放平台
    public static final String wx_app_id = "wx89f3b1477df1aa39";

    public static final String API_KEY = "carsuper1201wechat905appweixinpy";

    //屏幕宽度(dp)
    public  static int screenWidth=0;

    //定位状态
    public static final int LOCATION_FAILURE = 10001;
    public static final int LOCATION_SUCCESS = 10002;
    public static final int LOCATION_ING = 10003;



    //定位数据
    public static String locationProvince="";
    public static String locationcity="";//baidusdk定位到的城市
    public static String pointCity = "";//用户指向的城市
    public  static String  locationAddress=""; //定位的地址
    public  static String  locationDistrict="";  //
    public  static String  locationStreet="";
    public static double latitude;
    public static double longitude;


    //通知通道
    public  static String channel_id="carsuper";
    public  static  String channel_name="通知";
    //购物车 商品数量
     public static int SHOPPING_CART_COMMODITY_COUNT = 0;

    //每个fragment 指定一个int值,用于containeractivity 装载相应fragment

    public static final int LOGINFRAGMENT = 0;
    public static final int SEARCHFRAGMENT = 1;
    public static final int CARSELECTFRAGMENT = 2;
    public static final int  COMMODITYDETAILFRAGMENT = 3;
    public static final int  STOREDETAILFRAGMENT = 4;

    public static final int ORDERTOMAINTENANCEFRAGMENT = 5;
    public static final int MYDATAFRAGMENT = 6;

    public static final int MYCOMMODITYORDERFRAGMENT = 7;

    public static final int NEEDTOPAYFRAGMENT = 8;
    public static final int NEEDTOSENDFRAGMENT = 9;
    public static final int NEEDTORECIEVEFRAGMENT = 10;
    public static final int NEEDTOEVALUATEFRAGMENT = 11;
    public static final int RETURNORCHANGEFRAGMENT = 12;

    public static final int     MYMAINTANCEORDERFRAGMENT =13;

    public static final int     MYLOVELYCARFRAGMENT =14;

    public static final int     MYADDRESSFRAGMENT =15;

    public static final int     OPINIONFRAGMENT =16;

    public static final int     SETTINGFRAGMENT =17;

    public static final int     SHOPPINGCARTFRAGMENT =18;

    public static final int     INTEGRALCENTERFRAGMENT =19;//积分中心2

    public static final int     SHOPPINGMALLFRAGMENT =20;
    public static final int     CONFIRMCOMMODITYORDERFRAGMENT =21;
    public static final int     PAYMENTSUCCESSFRAGMENT =22;

    public static final int     MYINTEGRALCENTERFRAGMENT=23; //积分中心1


    public static final  int    MYMAINCOUPON=24;  //积分中心2

    public static  final  int   MyInvitesCourtesy=25; //邀请有礼

    public static final  int    MyMainFragment=26;    //首页打开新闻链接用


    public static final  int    ConfirmMaintanceOrderFragment=27;


    public static final  int    MYRECIVECOUPON=28;

    public static final  int FragmentExchange=29;


    //图片来源

    public static final int     PHOTO_CAMERA =1;
    public static final int     PHOTO_ALBUM =2;



    //SDCard路径
    public static String SDCARD_PATH = StoreSpaceUtils.getSDCardPath();

    /**
     * 本地存储总目录
     */
    public static String SAVE_DIR_BASE = SDCARD_PATH.concat("/carsuper/");

    /**
     * 图片存储位置
     */
    public static String SAVE_DIR_GLIDE_CACHE = SAVE_DIR_BASE.concat("GlideCache/");
    /**
     * 图片存储位置
     */
    public static String SAVE_DIR_PHOTO = SAVE_DIR_BASE.concat("photo/");
    /**
     * 头像存储位置
     */
    public static String SAVE_DIR_ICON = SAVE_DIR_BASE.concat("icon/");
    /**
     * 视频存储位置
     */
    public static String SAVE_DIR_VIDEO = SAVE_DIR_BASE.concat("video/");
    /**
     * 语音存储位置
     */
    public static String SAVE_DIR_VOICE = SAVE_DIR_BASE.concat("voice/");
    /**
     * 奔溃存储路径
     */
    public static String SAVE_DIR_CRASH = SAVE_DIR_BASE.concat("crash/");
}
