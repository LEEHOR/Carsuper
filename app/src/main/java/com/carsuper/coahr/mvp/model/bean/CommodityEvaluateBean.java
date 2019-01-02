package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/8.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityEvaluateBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"count_all":"1","count_pic":"1","count_low_score":"0","comment":[{"nickname":"张亮","userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132","uid":"864","so_id":"1087","level_score":"4","praise":"18","comment":"宝贝不错～～～","cteate_time":"2018/07/16 16:33:29","praise_status":0,"comment_count":"2","reply":{"uid":"910","nickname":"余伟","comment":"6666"},"comment_pic":["https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg","https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg"]}]}
     */

    private int code;
    private String msg;
    private JdataEntity jdata;

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

    public JdataEntity getJdata() {
        return jdata;
    }

    public void setJdata(JdataEntity jdata) {
        this.jdata = jdata;
    }

    public static class JdataEntity {
        /**
         * count_all : 1
         * count_pic : 1
         * count_low_score : 0
         * comment : [{"nickname":"张亮","userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132","uid":"864","so_id":"1087","level_score":"4","praise":"18","comment":"宝贝不错～～～","cteate_time":"2018/07/16 16:33:29","praise_status":0,"comment_count":"2","reply":{"uid":"910","nickname":"余伟","comment":"6666"},"comment_pic":["https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg","https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg"]}]
         */

        private String count_all;
        private String count_pic;
        private String count_low_score;
        private List<CommentEntity> comment;

        public String getCount_all() {
            return count_all;
        }

        public void setCount_all(String count_all) {
            this.count_all = count_all;
        }

        public String getCount_pic() {
            return count_pic;
        }

        public void setCount_pic(String count_pic) {
            this.count_pic = count_pic;
        }

        public String getCount_low_score() {
            return count_low_score;
        }

        public void setCount_low_score(String count_low_score) {
            this.count_low_score = count_low_score;
        }

        public List<CommentEntity> getComment() {
            return comment;
        }

        public void setComment(List<CommentEntity> comment) {
            this.comment = comment;
        }

        public static class CommentEntity {
            /**
             * nickname : 张亮
             * userheadimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132
             * uid : 864
             * so_id : 1087
             * level_score : 4
             * praise : 18
             * comment : 宝贝不错～～～
             * cteate_time : 2018/07/16 16:33:29
             * praise_status : 0
             * comment_count : 2
             * reply : {"uid":"910","nickname":"余伟","comment":"6666"}
             * comment_pic : ["https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg","https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg"]
             */

            private String nickname;
            private String userheadimg;
            private String uid;
            private String so_id;
            private String level_score;
            private String praise;
            private String comment;
            private String create_time;
            private int praise_status;
            private String comment_count;
            private ReplyEntity reply;
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

            public String getSo_id() {
                return so_id;
            }

            public void setSo_id(String so_id) {
                this.so_id = so_id;
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

            public int getPraise_status() {
                return praise_status;
            }

            public void setPraise_status(int praise_status) {
                this.praise_status = praise_status;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public ReplyEntity getReply() {
                return reply;
            }

            public void setReply(ReplyEntity reply) {
                this.reply = reply;
            }

            public List<String> getComment_pic() {
                return comment_pic;
            }

            public void setComment_pic(List<String> comment_pic) {
                this.comment_pic = comment_pic;
            }

            public static class ReplyEntity {
                /**
                 * uid : 910
                 * nickname : 余伟
                 * comment : 6666
                 */

                private String uid;
                private String nickname;
                private String comment;

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

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }
            }
        }
    }
}
