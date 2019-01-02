package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/30.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreDetailBean {


    /**
     * code : 0
     * jdata : {"comment_one":{"comment":"很一般！！！","comment_count":"2","comment_pic":["https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg"],"comment_pic4":"","create_time":"2018-07-12 14:47:36","id":"79","level_score":"2","nickname":"张亮","uid":"864","userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132"},"service":[{"b_content":"此为工时费用，具体情况以实际为准","b_id":"3201","b_name":"换备胎","b_price_max":"60.00","b_price_min":"30.00"},{"b_content":"此为工时费用（不含补片价格），具体情况以实际为准","b_id":"3202","b_name":"补胎","b_price_max":"90.00","b_price_min":"40.00"},{"b_content":"此为工时费用，具体情况以实际为准","b_id":"3203","b_name":"倒胎","b_price_max":"100.00","b_price_min":"100.00"},{"b_content":"不含拆装，具体情况以实际为准","b_id":"3204","b_name":"加气","b_price_max":"20.00","b_price_min":"10.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3205","b_name":"加水","b_price_max":"15.00","b_price_min":"10.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3206","b_name":"换发电机皮带","b_price_max":"150.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3207","b_name":"换油门线","b_price_max":"80.00","b_price_min":"50.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3208","b_name":"换水泵","b_price_max":"400.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3209","b_name":"换油底壳","b_price_max":"100.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3210","b_name":"换空滤","b_price_max":"15.00","b_price_min":"15.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3211","b_name":"换散热器","b_price_max":"260.00","b_price_min":"150.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3212","b_name":"换离合总泵","b_price_max":"200.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3213","b_name":"换离合分泵","b_price_max":"150.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3214","b_name":"换刹车总泵","b_price_max":"150.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3215","b_name":"换刹车分泵","b_price_max":"150.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3216","b_name":"换汽缸垫","b_price_max":"800.00","b_price_min":"600.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3217","b_name":"换开门锁","b_price_max":"300.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3218","b_name":"换水温传感器","b_price_max":"60.00","b_price_min":"50.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3219","b_name":"换机油传感器","b_price_max":"80.00","b_price_min":"50.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3220","b_name":"换电瓶","b_price_max":"100.00","b_price_min":"50.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3221","b_name":"换离合器三件套","b_price_max":"800.00","b_price_min":"600.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3222","b_name":"换发电机","b_price_max":"150.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3223","b_name":"换半轴","b_price_max":"120.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3224","b_name":"换十字节","b_price_max":"240.00","b_price_min":"150.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3225","b_name":"换前刹车鼓","b_price_max":"180.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3226","b_name":"换后刹车鼓","b_price_max":"150.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3227","b_name":"换灯组开关","b_price_max":"200.00","b_price_min":"60.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3228","b_name":"换转动轴","b_price_max":"150.00","b_price_min":"120.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3229","b_name":"换检修线路","b_price_max":"120.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3230","b_name":"换充电线路","b_price_max":"100.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3231","b_name":"换高压油管","b_price_max":"60.00","b_price_min":"50.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3232","b_name":"调刹车","b_price_max":"80.00","b_price_min":"60.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3233","b_name":"换机油","b_price_max":"50.00","b_price_min":"50.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3234","b_name":"换直推","b_price_max":"100.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3235","b_name":"换横拉杆","b_price_max":"100.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3236","b_name":"换启动机","b_price_max":"150.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3237","b_name":"发动机大修","b_price_max":"3500.00","b_price_min":"1200.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3238","b_name":"发动机四配件","b_price_max":"2000.00","b_price_min":"600.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3239","b_name":"换机油泵","b_price_max":"260.00","b_price_min":"160.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3240","b_name":"换空压机","b_price_max":"500.00","b_price_min":"300.00"}],"station":{"distance":0,"s_address":"湖北省黄冈市麻城市木子店服务区(沪蓉高速公路北)","s_endtime":"00:00:00","s_id":"199","s_latitude":"31.243313","s_longitude":"115.424311","s_name":"木子店服务区（北）","s_service_tag":[{"tag":"上门保养"},{"tag":"24小时救援"},{"tag":"免费WiFi"},{"tag":"免费淋浴"},{"tag":"免费用电"},{"tag":"免费开水"}],"s_starttime":"00:00:00","s_type":"0","turn_pic":[{"pic":"https://shop.carsuper.cn/Data/Station/5b4561e93b20b.jpg"},{"pic":"https://shop.carsuper.cn/Data/Station/5b4561ef1e848.jpg"},{"pic":"https://shop.carsuper.cn/Data/Station/5b4561f453ec6.jpg"}]}}
     * msg : success
     */

    private int code;
    private JdataEntity jdata;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JdataEntity getJdata() {
        return jdata;
    }

    public void setJdata(JdataEntity jdata) {
        this.jdata = jdata;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class JdataEntity {

        /**
         * comment_one : {"comment":"很一般！！！","comment_count":"2","comment_pic":["https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg"],"comment_pic4":"","create_time":"2018-07-12 14:47:36","id":"79","level_score":"2","nickname":"张亮","uid":"864","userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132"}
         * service : [{"b_content":"此为工时费用，具体情况以实际为准","b_id":"3201","b_name":"换备胎","b_price_max":"60.00","b_price_min":"30.00"},{"b_content":"此为工时费用（不含补片价格），具体情况以实际为准","b_id":"3202","b_name":"补胎","b_price_max":"90.00","b_price_min":"40.00"},{"b_content":"此为工时费用，具体情况以实际为准","b_id":"3203","b_name":"倒胎","b_price_max":"100.00","b_price_min":"100.00"},{"b_content":"不含拆装，具体情况以实际为准","b_id":"3204","b_name":"加气","b_price_max":"20.00","b_price_min":"10.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3205","b_name":"加水","b_price_max":"15.00","b_price_min":"10.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3206","b_name":"换发电机皮带","b_price_max":"150.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3207","b_name":"换油门线","b_price_max":"80.00","b_price_min":"50.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3208","b_name":"换水泵","b_price_max":"400.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3209","b_name":"换油底壳","b_price_max":"100.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3210","b_name":"换空滤","b_price_max":"15.00","b_price_min":"15.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3211","b_name":"换散热器","b_price_max":"260.00","b_price_min":"150.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3212","b_name":"换离合总泵","b_price_max":"200.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3213","b_name":"换离合分泵","b_price_max":"150.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3214","b_name":"换刹车总泵","b_price_max":"150.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3215","b_name":"换刹车分泵","b_price_max":"150.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3216","b_name":"换汽缸垫","b_price_max":"800.00","b_price_min":"600.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3217","b_name":"换开门锁","b_price_max":"300.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3218","b_name":"换水温传感器","b_price_max":"60.00","b_price_min":"50.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3219","b_name":"换机油传感器","b_price_max":"80.00","b_price_min":"50.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3220","b_name":"换电瓶","b_price_max":"100.00","b_price_min":"50.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3221","b_name":"换离合器三件套","b_price_max":"800.00","b_price_min":"600.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3222","b_name":"换发电机","b_price_max":"150.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3223","b_name":"换半轴","b_price_max":"120.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3224","b_name":"换十字节","b_price_max":"240.00","b_price_min":"150.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3225","b_name":"换前刹车鼓","b_price_max":"180.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3226","b_name":"换后刹车鼓","b_price_max":"150.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3227","b_name":"换灯组开关","b_price_max":"200.00","b_price_min":"60.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3228","b_name":"换转动轴","b_price_max":"150.00","b_price_min":"120.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3229","b_name":"换检修线路","b_price_max":"120.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3230","b_name":"换充电线路","b_price_max":"100.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3231","b_name":"换高压油管","b_price_max":"60.00","b_price_min":"50.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3232","b_name":"调刹车","b_price_max":"80.00","b_price_min":"60.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3233","b_name":"换机油","b_price_max":"50.00","b_price_min":"50.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3234","b_name":"换直推","b_price_max":"100.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3235","b_name":"换横拉杆","b_price_max":"100.00","b_price_min":"80.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3236","b_name":"换启动机","b_price_max":"150.00","b_price_min":"100.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3237","b_name":"发动机大修","b_price_max":"3500.00","b_price_min":"1200.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3238","b_name":"发动机四配件","b_price_max":"2000.00","b_price_min":"600.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3239","b_name":"换机油泵","b_price_max":"260.00","b_price_min":"160.00"},{"b_content":"外出抢修价格根据实际情况而定；根据车型的级别、结构升级的车型维修价格另议","b_id":"3240","b_name":"换空压机","b_price_max":"500.00","b_price_min":"300.00"}]
         * station : {"distance":0,"s_address":"湖北省黄冈市麻城市木子店服务区(沪蓉高速公路北)","s_endtime":"00:00:00","s_id":"199","s_latitude":"31.243313","s_longitude":"115.424311","s_name":"木子店服务区（北）","s_service_tag":[{"tag":"上门保养"},{"tag":"24小时救援"},{"tag":"免费WiFi"},{"tag":"免费淋浴"},{"tag":"免费用电"},{"tag":"免费开水"}],"s_starttime":"00:00:00","s_type":"0","turn_pic":[{"pic":"https://shop.carsuper.cn/Data/Station/5b4561e93b20b.jpg"},{"pic":"https://shop.carsuper.cn/Data/Station/5b4561ef1e848.jpg"},{"pic":"https://shop.carsuper.cn/Data/Station/5b4561f453ec6.jpg"}]}
         */


        private String s_phone;
        private CommentOneEntity comment_one;
        private StationEntity station;
        private List<ServiceEntity> service;

        public String getS_phone() {
            return s_phone;
        }

        public void setS_phone(String s_phone) {
            this.s_phone = s_phone;
        }

        public CommentOneEntity getComment_one() {
            return comment_one;
        }

        public void setComment_one(CommentOneEntity comment_one) {
            this.comment_one = comment_one;
        }

        public StationEntity getStation() {
            return station;
        }

        public void setStation(StationEntity station) {
            this.station = station;
        }

        public List<ServiceEntity> getService() {
            return service;
        }

        public void setService(List<ServiceEntity> service) {
            this.service = service;
        }

        public static class CommentOneEntity {
            /**
             * comment : 很一般！！！
             * comment_count : 2
             * comment_pic : ["https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg"]
             * comment_pic4 :
             * create_time : 2018-07-12 14:47:36
             * id : 79
             * level_score : 2
             * nickname : 张亮
             * uid : 864
             * userheadimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132
             */

            private String comment;
            private String comment_count;
            private String comment_pic4;
            private String create_time;
            private String id;
            private String level_score;
            private String nickname;
            private String uid;
            private String userheadimg;
            private List<String> comment_pic;

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getComment_pic4() {
                return comment_pic4;
            }

            public void setComment_pic4(String comment_pic4) {
                this.comment_pic4 = comment_pic4;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLevel_score() {
                return level_score;
            }

            public void setLevel_score(String level_score) {
                this.level_score = level_score;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUserheadimg() {
                return userheadimg;
            }

            public void setUserheadimg(String userheadimg) {
                this.userheadimg = userheadimg;
            }

            public List<String> getComment_pic() {
                return comment_pic;
            }

            public void setComment_pic(List<String> comment_pic) {
                this.comment_pic = comment_pic;
            }
        }

        public static class StationEntity {
            /**
             * distance : 0
             * s_address : 湖北省黄冈市麻城市木子店服务区(沪蓉高速公路北)
             * s_endtime : 00:00:00
             * s_id : 199
             * s_latitude : 31.243313
             * s_longitude : 115.424311
             * s_name : 木子店服务区（北）
             * s_service_tag : [{"tag":"上门保养"},{"tag":"24小时救援"},{"tag":"免费WiFi"},{"tag":"免费淋浴"},{"tag":"免费用电"},{"tag":"免费开水"}]
             * s_starttime : 00:00:00
             * s_type : 0
             * turn_pic : [{"pic":"https://shop.carsuper.cn/Data/Station/5b4561e93b20b.jpg"},{"pic":"https://shop.carsuper.cn/Data/Station/5b4561ef1e848.jpg"},{"pic":"https://shop.carsuper.cn/Data/Station/5b4561f453ec6.jpg"}]
             */

            private float distance;
            private String s_address;
            private String s_endtime;
            private String s_id;
            private String s_latitude;
            private String s_longitude;
            private String s_name;
            private String s_starttime;
            private String s_type;
            private List<SServiceTagEntity> s_service_tag;
            private List<TurnPicEntity> turn_pic;

            public float getDistance() {
                return distance;
            }

            public void setDistance(float distance) {
                this.distance = distance;
            }

            public String getS_address() {
                return s_address;
            }

            public void setS_address(String s_address) {
                this.s_address = s_address;
            }

            public String getS_endtime() {
                return s_endtime;
            }

            public void setS_endtime(String s_endtime) {
                this.s_endtime = s_endtime;
            }

            public String getS_id() {
                return s_id;
            }

            public void setS_id(String s_id) {
                this.s_id = s_id;
            }

            public String getS_latitude() {
                return s_latitude;
            }

            public void setS_latitude(String s_latitude) {
                this.s_latitude = s_latitude;
            }

            public String getS_longitude() {
                return s_longitude;
            }

            public void setS_longitude(String s_longitude) {
                this.s_longitude = s_longitude;
            }

            public String getS_name() {
                return s_name;
            }

            public void setS_name(String s_name) {
                this.s_name = s_name;
            }

            public String getS_starttime() {
                return s_starttime;
            }

            public void setS_starttime(String s_starttime) {
                this.s_starttime = s_starttime;
            }

            public String getS_type() {
                return s_type;
            }

            public void setS_type(String s_type) {
                this.s_type = s_type;
            }

            public List<SServiceTagEntity> getS_service_tag() {
                return s_service_tag;
            }

            public void setS_service_tag(List<SServiceTagEntity> s_service_tag) {
                this.s_service_tag = s_service_tag;
            }

            public List<TurnPicEntity> getTurn_pic() {
                return turn_pic;
            }

            public void setTurn_pic(List<TurnPicEntity> turn_pic) {
                this.turn_pic = turn_pic;
            }

            public static class SServiceTagEntity {
                /**
                 * tag : 上门保养
                 */

                private String tag;

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }
            }

            public static class TurnPicEntity {
                /**
                 * pic : https://shop.carsuper.cn/Data/Station/5b4561e93b20b.jpg
                 */

                private String pic;

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }
            }
        }

        public static class ServiceEntity {
            /**
             * b_content : 此为工时费用，具体情况以实际为准
             * b_id : 3201
             * b_name : 换备胎
             * b_price_max : 60.00
             * b_price_min : 30.00
             */

            private String b_content;
            private String b_id;
            private String b_name;
            private String b_price_max;
            private String b_price_min;

            public String getB_content() {
                return b_content;
            }

            public void setB_content(String b_content) {
                this.b_content = b_content;
            }

            public String getB_id() {
                return b_id;
            }

            public void setB_id(String b_id) {
                this.b_id = b_id;
            }

            public String getB_name() {
                return b_name;
            }

            public void setB_name(String b_name) {
                this.b_name = b_name;
            }

            public String getB_price_max() {
                return b_price_max;
            }

            public void setB_price_max(String b_price_max) {
                this.b_price_max = b_price_max;
            }

            public String getB_price_min() {
                return b_price_min;
            }

            public void setB_price_min(String b_price_min) {
                this.b_price_min = b_price_min;
            }
        }
    }
}
