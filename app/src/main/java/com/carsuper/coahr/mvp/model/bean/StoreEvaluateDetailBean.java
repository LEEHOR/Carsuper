package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/1.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreEvaluateDetailBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"comment_one":{"nickname":"图样图森波图样图","userheadimg":"/Data/User/pic1_5b9f4a8308471.jpg","uid":"1019","ao_id":"227","level_score":"0","comment":"厉害了6666666699999999","order_id":"2018091910210056","praise":"0","create_time":"2018/09/30 11:06:08","praise_status":0,"comment_pic":["https://shop.carsuper.cn/Data/Comment/Station/pic1_5bb03da0836f7.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic2_5bb03da0873ed.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic3_5bb03da0873ed.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic4_5bb03da0873ed.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic5_5bb03da0873ed.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic6_5bb03da08b0e3.jpg"]},"commodity":{"c_thumbnail":"https://shop.carsuper.cn"},"reply_count":"5","reply":[{"nickname":"李浩","userheadimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132","ap_id":"26","comment":"黑帝的咯","praise":"0","create_time":"2018-10-19 15:04:10","praise_status":0},{"nickname":"李浩","userheadimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132","ap_id":"25","comment":"黑帝的咯","praise":"0","create_time":"2018-10-19 15:04:03","praise_status":0},{"nickname":"李浩","userheadimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132","ap_id":"24","comment":"黑帝的咯","praise":"0","create_time":"2018-10-19 15:03:42","praise_status":0},{"nickname":"李浩","userheadimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132","ap_id":"23","comment":"呵呵呵","praise":"0","create_time":"2018-10-19 14:59:51","praise_status":0},{"nickname":"李浩","userheadimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132","ap_id":"22","comment":"哈哈哈","praise":"0","create_time":"2018-10-19 14:32:52","praise_status":0}]}
     */

    private int code;
    private String msg;
    private JdataBean jdata;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JdataBean getJdata() {
        return jdata;
    }

    public void setJdata(JdataBean jdata) {
        this.jdata = jdata;
    }

    public static class JdataBean {
        /**
         * comment_one : {"nickname":"图样图森波图样图","userheadimg":"/Data/User/pic1_5b9f4a8308471.jpg","uid":"1019","ao_id":"227","level_score":"0","comment":"厉害了6666666699999999","order_id":"2018091910210056","praise":"0","create_time":"2018/09/30 11:06:08","praise_status":0,"comment_pic":["https://shop.carsuper.cn/Data/Comment/Station/pic1_5bb03da0836f7.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic2_5bb03da0873ed.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic3_5bb03da0873ed.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic4_5bb03da0873ed.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic5_5bb03da0873ed.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic6_5bb03da08b0e3.jpg"]}
         * commodity : {"c_thumbnail":"https://shop.carsuper.cn"}
         * reply_count : 5
         * reply : [{"nickname":"李浩","userheadimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132","ap_id":"26","comment":"黑帝的咯","praise":"0","create_time":"2018-10-19 15:04:10","praise_status":0},{"nickname":"李浩","userheadimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132","ap_id":"25","comment":"黑帝的咯","praise":"0","create_time":"2018-10-19 15:04:03","praise_status":0},{"nickname":"李浩","userheadimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132","ap_id":"24","comment":"黑帝的咯","praise":"0","create_time":"2018-10-19 15:03:42","praise_status":0},{"nickname":"李浩","userheadimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132","ap_id":"23","comment":"呵呵呵","praise":"0","create_time":"2018-10-19 14:59:51","praise_status":0},{"nickname":"李浩","userheadimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132","ap_id":"22","comment":"哈哈哈","praise":"0","create_time":"2018-10-19 14:32:52","praise_status":0}]
         */

        private CommentOneBean comment_one;
        private CommodityBean commodity;
        private String reply_count;
        private List<ReplyBean> reply;

        public CommentOneBean getComment_one() {
            return comment_one;
        }

        public void setComment_one(CommentOneBean comment_one) {
            this.comment_one = comment_one;
        }

        public CommodityBean getCommodity() {
            return commodity;
        }

        public void setCommodity(CommodityBean commodity) {
            this.commodity = commodity;
        }

        public String getReply_count() {
            return reply_count;
        }

        public void setReply_count(String reply_count) {
            this.reply_count = reply_count;
        }

        public List<ReplyBean> getReply() {
            return reply;
        }

        public void setReply(List<ReplyBean> reply) {
            this.reply = reply;
        }

        public static class CommentOneBean {
            /**
             * nickname : 图样图森波图样图
             * userheadimg : /Data/User/pic1_5b9f4a8308471.jpg
             * uid : 1019
             * ao_id : 227
             * level_score : 0
             * comment : 厉害了6666666699999999
             * order_id : 2018091910210056
             * praise : 0
             * create_time : 2018/09/30 11:06:08
             * praise_status : 0
             * comment_pic : ["https://shop.carsuper.cn/Data/Comment/Station/pic1_5bb03da0836f7.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic2_5bb03da0873ed.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic3_5bb03da0873ed.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic4_5bb03da0873ed.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic5_5bb03da0873ed.jpg","https://shop.carsuper.cn/Data/Comment/Station/pic6_5bb03da08b0e3.jpg"]
             */

            private String nickname;
            private String userheadimg;
            private String uid;
            private String ao_id;
            private String level_score;
            private String comment;
            private String order_id;
            private String praise;
            private String create_time;
            private int praise_status;
            private List<String> comment_pic;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUserheadimg() {
                return userheadimg;
            }

            public void setUserheadimg(String userheadimg) {
                this.userheadimg = userheadimg;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getAo_id() {
                return ao_id;
            }

            public void setAo_id(String ao_id) {
                this.ao_id = ao_id;
            }

            public String getLevel_score() {
                return level_score;
            }

            public void setLevel_score(String level_score) {
                this.level_score = level_score;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getPraise() {
                return praise;
            }

            public void setPraise(String praise) {
                this.praise = praise;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getPraise_status() {
                return praise_status;
            }

            public void setPraise_status(int praise_status) {
                this.praise_status = praise_status;
            }

            public List<String> getComment_pic() {
                return comment_pic;
            }

            public void setComment_pic(List<String> comment_pic) {
                this.comment_pic = comment_pic;
            }
        }

        public static class CommodityBean {
            /**
             *
             * "c_id":"160",
             *  	"c_name":"弗列加LF9009机油滤清器",
             *   "c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1539765279_945315.jpeg",
             *   "c_price":"124.99",
             *    "c_price_old":"125.00",
             *     "c_sold_real":"3"
             */

            private String c_thumbnail;
            private String c_name;
            private String c_price;
            private String c_price_old;
            private String c_sold_real;

            public String getC_thumbnail() {
                return c_thumbnail;
            }

            public void setC_thumbnail(String c_thumbnail) {
                this.c_thumbnail = c_thumbnail;
            }

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getC_price() {
                return c_price;
            }

            public void setC_price(String c_price) {
                this.c_price = c_price;
            }

            public String getC_price_old() {
                return c_price_old;
            }

            public void setC_price_old(String c_price_old) {
                this.c_price_old = c_price_old;
            }

            public String getC_sold_real() {
                return c_sold_real;
            }

            public void setC_sold_real(String c_sold_real) {
                this.c_sold_real = c_sold_real;
            }
        }

        public static class ReplyBean {
            /**
             * nickname : 李浩
             * userheadimg : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132
             * ap_id : 26
             * comment : 黑帝的咯
             * praise : 0
             * create_time : 2018-10-19 15:04:10
             * praise_status : 0
             */

            private String nickname;
            private String userheadimg;
            private String ap_id;
            private String comment;
            private String praise;
            private String create_time;
            private int praise_status;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUserheadimg() {
                return userheadimg;
            }

            public void setUserheadimg(String userheadimg) {
                this.userheadimg = userheadimg;
            }

            public String getAp_id() {
                return ap_id;
            }

            public void setAp_id(String ap_id) {
                this.ap_id = ap_id;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getPraise() {
                return praise;
            }

            public void setPraise(String praise) {
                this.praise = praise;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getPraise_status() {
                return praise_status;
            }

            public void setPraise_status(int praise_status) {
                this.praise_status = praise_status;
            }
        }
    }
}
