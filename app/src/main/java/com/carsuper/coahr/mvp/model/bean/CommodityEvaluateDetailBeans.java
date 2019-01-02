package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

public class CommodityEvaluateDetailBeans {

    /**
     * code : 0
     * msg : success
     * jdata : {"comment_one":{"so_id":"2018091216123867548999","comment":"但是方法也","level_score":null,"praise":"53","uid":"893","nickname":"放干干净净","userheadimg":"/Data/User/pic1_5b7a23bc0f502.jpg","create_time":"2018/09/18 17:04:08","praise_status":0},"commodity":{"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png","c_price":"579.99","c_price_old":"580.00","c_sold_real":"9"},"reply_count":"0","reply":{}}
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
         * comment_one : {"so_id":"2018091216123867548999","comment":"但是方法也","level_score":null,"praise":"53","uid":"893","nickname":"放干干净净","userheadimg":"/Data/User/pic1_5b7a23bc0f502.jpg","create_time":"2018/09/18 17:04:08","praise_status":0}
         * commodity : {"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png","c_price":"579.99","c_price_old":"580.00","c_sold_real":"9"}
         * reply_count : 0
         * reply : {}
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
             * so_id : 2018091216123867548999
             * comment : 但是方法也
             * level_score : null
             * praise : 53
             * uid : 893
             * nickname : 放干干净净
             * userheadimg : /Data/User/pic1_5b7a23bc0f502.jpg
             * create_time : 2018/09/18 17:04:08
             * praise_status : 0
             * "comment_pic":[{"pic":"https://shop.carsuper.cnsdfgsdfg"}]
             */

            private String so_id;
            private String comment;
            private String level_score;
            private String praise;
            private String uid;
            private String nickname;
            private String userheadimg;
            private String create_time;
            private int praise_status;
            private List<Object> comment_pic;
            public String getSo_id() {
                return so_id;
            }

            public void setSo_id(String so_id) {
                this.so_id = so_id;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getLevel_score() {
                return level_score;
            }

            public void setLevel_score(String level_score) {
                this.level_score = level_score;
            }

            public String getPraise() {
                return praise;
            }

            public void setPraise(String praise) {
                this.praise = praise;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

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

            public List<Object> getComment_pic() {
                return comment_pic;
            }

            public void setComment_pic(List<Object> comment_pic) {
                this.comment_pic = comment_pic;
            }

            public static class CommentPicBean {
                /**
                 * pic : https://shop.carsuper.cnsdfgsdfg
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


        //购买的商品
        public static class CommodityBean {

            /**
             * c_id : 76
             * c_name : 米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦
             * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png
             * c_price : 579.99
             * c_price_old : 580.00
             * c_sold_real : 9
             *
             */

            private String c_id;
            private String c_name;
            private String c_thumbnail;
            private String c_price;
            private String c_price_old;
            private String c_sold_real;


            public String getC_id() {
                return c_id;
            }

            public void setC_id(String c_id) {
                this.c_id = c_id;
            }

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getC_thumbnail() {
                return c_thumbnail;
            }

            public void setC_thumbnail(String c_thumbnail) {
                this.c_thumbnail = c_thumbnail;
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
            private String create_time;
            private String sp_id;
            private String comment;
            private String nickname;
            private String praise;
            private int praise_status;
            private String userheadimg;

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String addtime) {
                this.create_time = addtime;
            }

            public String getSp_id() {
                return sp_id;
            }

            public void setSp_id(String ap_id) {
                this.sp_id = ap_id;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPraise() {
                return praise;
            }

            public void setPraise(String praise) {
                this.praise = praise;
            }

            public int getPraise_status() {
                return praise_status;
            }

            public void setPraise_status(int praise_status) {
                this.praise_status = praise_status;
            }

            public String getUserheadimg() {
                return userheadimg;
            }

            public void setUserheadimg(String userheadimg) {
                this.userheadimg = userheadimg;
            }
        }
    }
}