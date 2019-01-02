package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/8.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityEvaluateDetailbean {
    /**
     * code : 0
     * jdata : {"comment_one":{"ao_id":"79","comment":"很一般！！！","comment_pic":["https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg"],"create_time":"2018/07/19 11:17:17","level_score":"2","nickname":"张亮","order_id":"2018071250505251","praise":"1","praise_status":1,"uid":"864","userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132"},"commodity":{"c_id":"67","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00","c_price_old":"359.00","c_sold_real":"0","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180704/5b3c35d08b3c8.png"},"reply":[{"addtime":"2018-07-20 15:52:21","ap_id":"3","comment":"21212121","nickname":"心灵的忧伤","praise":"13","praise_status":1,"userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eowR6ZLlzvd9x8VbbDhudwpIFzD6QyfeJZk5BAJxQg6yYTRpXdXgOVe8WiaWibfodRoht2yx9h0eianA/132"},{"addtime":"2018-07-20 15:51:46","ap_id":"2","comment":"212121","nickname":"Alant","praise":"11","praise_status":1,"userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/3RgGlXW9F0SU4O0S3gMjVriatN4ibg3UBNxy02eyKrT8hQj0pqhE6uHogC5EW9e2cicEw5lffPvDC99c8d9INeBNg/132"}],"reply_count":"2"}
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
         * comment_one : {"ao_id":"79","comment":"很一般！！！","comment_pic":["https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg"],"create_time":"2018/07/19 11:17:17","level_score":"2","nickname":"张亮","order_id":"2018071250505251","praise":"1","praise_status":1,"uid":"864","userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132"}
         * commodity : {"c_id":"67","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00","c_price_old":"359.00","c_sold_real":"0","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180704/5b3c35d08b3c8.png"}
         * reply : [{"addtime":"2018-07-20 15:52:21","ap_id":"3","comment":"21212121","nickname":"心灵的忧伤","praise":"13","praise_status":1,"userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eowR6ZLlzvd9x8VbbDhudwpIFzD6QyfeJZk5BAJxQg6yYTRpXdXgOVe8WiaWibfodRoht2yx9h0eianA/132"},{"addtime":"2018-07-20 15:51:46","ap_id":"2","comment":"212121","nickname":"Alant","praise":"11","praise_status":1,"userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/3RgGlXW9F0SU4O0S3gMjVriatN4ibg3UBNxy02eyKrT8hQj0pqhE6uHogC5EW9e2cicEw5lffPvDC99c8d9INeBNg/132"}]
         * reply_count : 2
         */

        private CommentOneEntity comment_one;
        private CommodityEntity commodity;
        private String reply_count;
        private List<ReplyEntity> reply;

        public CommentOneEntity getComment_one() {
            return comment_one;
        }

        public void setComment_one(CommentOneEntity comment_one) {
            this.comment_one = comment_one;
        }

        public CommodityEntity getCommodity() {
            return commodity;
        }

        public void setCommodity(CommodityEntity commodity) {
            this.commodity = commodity;
        }

        public String getReply_count() {
            return reply_count;
        }

        public void setReply_count(String reply_count) {
            this.reply_count = reply_count;
        }

        public List<ReplyEntity> getReply() {
            return reply;
        }

        public void setReply(List<ReplyEntity> reply) {
            this.reply = reply;
        }

        public static class CommentOneEntity {
            /**
             * ao_id : 79
             * comment : 很一般！！！
             * comment_pic : ["https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg"]
             * create_time : 2018/07/19 11:17:17
             * level_score : 2
             * nickname : 张亮
             * order_id : 2018071250505251
             * praise : 1
             * praise_status : 1
             * uid : 864
             * userheadimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132
             */

            private String so_id;
            private String comment;
            private String create_time;
            private String level_score;
            private String nickname;
            private String order_id;
            private String praise;
            private int praise_status;
            private String uid;
            private String userheadimg;
            private List<String> comment_pic;

            public String getSo_id() {
                return so_id;
            }

            public void setSo_id(String ao_id) {
                this.so_id = ao_id;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
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

            public int getPraise_status() {
                return praise_status;
            }

            public void setPraise_status(int praise_status) {
                this.praise_status = praise_status;
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

        public static class CommodityEntity {
            /**
             * c_id : 67
             * c_name : 道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L
             * c_price : 329.00
             * c_price_old : 359.00
             * c_sold_real : 0
             * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180704/5b3c35d08b3c8.png
             */

            private String c_id;
            private String c_name;
            private String c_price;
            private String c_price_old;
            private String c_sold_real;
            private String c_thumbnail;

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

            public String getC_thumbnail() {
                return c_thumbnail;
            }

            public void setC_thumbnail(String c_thumbnail) {
                this.c_thumbnail = c_thumbnail;
            }
        }

        public static class ReplyEntity {
            /**
             * addtime : 2018-07-20 15:52:21
             * ap_id : 3
             * comment : 21212121
             * nickname : 心灵的忧伤
             * praise : 13
             * praise_status : 1
             * userheadimg : https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eowR6ZLlzvd9x8VbbDhudwpIFzD6QyfeJZk5BAJxQg6yYTRpXdXgOVe8WiaWibfodRoht2yx9h0eianA/132
             */

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
