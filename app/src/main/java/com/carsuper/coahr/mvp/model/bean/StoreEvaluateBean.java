package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/1.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreEvaluateBean {


    /**
     * code : 0
     * jdata : {"comment":[{"ao_id":"79","comment":"很一般！！！","comment_count":"3","comment_pic":["https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg","https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg","https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg","https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg"],"create_time":"2018/07/19 11:17:17","level_score":"2","nickname":"张亮","praise":"0","praise_status":0,"reply":{"comment":"21212121","nickname":"心灵的忧伤","uid":"842"},"uid":"864","userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132"}],"count_all":"2","count_low_score":"1","count_pic":"2"}
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
         * comment : [{"ao_id":"79","comment":"很一般！！！","comment_count":"3","comment_pic":["https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg","https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg","https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg","https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg"],"create_time":"2018/07/19 11:17:17","level_score":"2","nickname":"张亮","praise":"0","praise_status":0,"reply":{"comment":"21212121","nickname":"心灵的忧伤","uid":"842"},"uid":"864","userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132"}]
         * count_all : 2
         * count_low_score : 1
         * count_pic : 2
         */

        private String count_all;
        private String count_low_score;
        private String count_pic;
        private List<CommentEntity> comment;

        public String getCount_all() {
            return count_all;
        }

        public void setCount_all(String count_all) {
            this.count_all = count_all;
        }

        public String getCount_low_score() {
            return count_low_score;
        }

        public void setCount_low_score(String count_low_score) {
            this.count_low_score = count_low_score;
        }

        public String getCount_pic() {
            return count_pic;
        }

        public void setCount_pic(String count_pic) {
            this.count_pic = count_pic;
        }

        public List<CommentEntity> getComment() {
            return comment;
        }

        public void setComment(List<CommentEntity> comment) {
            this.comment = comment;
        }

        public static class CommentEntity {
            /**
             * ao_id : 79
             * comment : 很一般！！！
             * comment_count : 3
             * comment_pic : ["https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg","https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg","https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg","https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg"]
             * create_time : 2018/07/19 11:17:17
             * level_score : 2
             * nickname : 张亮
             * praise : 0
             * praise_status : 0
             * reply : {"comment":"21212121","nickname":"心灵的忧伤","uid":"842"}
             * uid : 864
             * userheadimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132
             */

            private String ao_id;
            private String comment;
            private String comment_count;
            private String create_time;
            private String level_score;
            private String nickname;
            private String praise;
            private int praise_status;
            private String uid;
            private String userheadimg;
            private ReplyEntity reply;
            private List<String> comment_pic;

            public String getAo_id() {
                return ao_id;
            }

            public void setAo_id(String ao_id) {
                this.ao_id = ao_id;
            }

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

            public ReplyEntity getReply() {
                return reply;
            }

            public void setReply(ReplyEntity reply) {
                this.reply = reply;
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

            public static class ReplyEntity {
                /**
                 * comment : 21212121
                 * nickname : 心灵的忧伤
                 * uid : 842
                 */

                private String comment;
                private String nickname;
                private String uid;

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

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }
            }
        }
    }
}
