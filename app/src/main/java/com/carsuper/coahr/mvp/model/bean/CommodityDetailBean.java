package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/25.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityDetailBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"commodity":{"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_brand":"44","c_sort":"74","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png","c_price":"580.00","c_price_old":"0.01"},"address":{"ua_id":"786","address":"身体还是它带给你打给你的功能"},"comment":{"id":"1087","comment":"宝贝不错～～～","level_score":"4","create_time":"2018-07-16 16:33:29","comment_pic4":null,"uid":"864","nickname":"张亮","userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132","comment_pic":["https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg","https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg"],"comment_count":"2"},"recommend_commodity":[{"c_id":"136","c_name":"1343","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1534835009_830590.jpeg","c_price":"12.00","c_price_old":"0.00","c_sold_real":"0"},{"c_id":"135","c_name":"11","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1534834922_781399.jpeg","c_price":"100.00","c_price_old":"0.00","c_sold_real":"0"},{"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png","c_price":"580.00","c_price_old":"0.01","c_sold_real":"0"}],"commodity_description":{"text":"<p><br/><\/p><p><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359833431823.jpg\" title=\"1531359833431823.jpg\" alt=\"2.jpg\"/><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359840724655.jpg\" title=\"1531359840724655.jpg\" alt=\"5.jpg\"/><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359845894458.jpg\" title=\"1531359845894458.jpg\" alt=\"6.jpg\"/><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359848132193.jpg\" title=\"1531359848132193.jpg\" alt=\"7.jpg\"/><\/p><p><br/><\/p><p><br/><\/p><p><br/><\/p><p><br/><\/p><p><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359854222216.jpg\" title=\"1531359854222216.jpg\" alt=\"9.jpg\"/><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359857931293.jpg\" title=\"1531359857931293.jpg\" alt=\"10.jpg\"/><\/p>","des_pic":[{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359833431823.jpg","width":750,"height":530},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359840724655.jpg","width":750,"height":472},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359845894458.jpg","width":750,"height":463},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359848132193.jpg","width":750,"height":888},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359854222216.jpg","width":750,"height":671},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359857931293.jpg","width":750,"height":553}]}}
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
         * commodity : {"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_brand":"44","c_sort":"74","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png","c_price":"580.00","c_price_old":"0.01"}
         * address : {"ua_id":"786","address":"身体还是它带给你打给你的功能"}
         * comment : {"id":"1087","comment":"宝贝不错～～～","level_score":"4","create_time":"2018-07-16 16:33:29","comment_pic4":null,"uid":"864","nickname":"张亮","userheadimg":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132","comment_pic":["https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg","https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg"],"comment_count":"2"}
         * recommend_commodity : [{"c_id":"136","c_name":"1343","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1534835009_830590.jpeg","c_price":"12.00","c_price_old":"0.00","c_sold_real":"0"},{"c_id":"135","c_name":"11","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1534834922_781399.jpeg","c_price":"100.00","c_price_old":"0.00","c_sold_real":"0"},{"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png","c_price":"580.00","c_price_old":"0.01","c_sold_real":"0"}]
         * commodity_description : {"text":"<p><br/><\/p><p><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359833431823.jpg\" title=\"1531359833431823.jpg\" alt=\"2.jpg\"/><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359840724655.jpg\" title=\"1531359840724655.jpg\" alt=\"5.jpg\"/><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359845894458.jpg\" title=\"1531359845894458.jpg\" alt=\"6.jpg\"/><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359848132193.jpg\" title=\"1531359848132193.jpg\" alt=\"7.jpg\"/><\/p><p><br/><\/p><p><br/><\/p><p><br/><\/p><p><br/><\/p><p><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359854222216.jpg\" title=\"1531359854222216.jpg\" alt=\"9.jpg\"/><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359857931293.jpg\" title=\"1531359857931293.jpg\" alt=\"10.jpg\"/><\/p>","des_pic":[{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359833431823.jpg","width":750,"height":530},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359840724655.jpg","width":750,"height":472},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359845894458.jpg","width":750,"height":463},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359848132193.jpg","width":750,"height":888},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359854222216.jpg","width":750,"height":671},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359857931293.jpg","width":750,"height":553}]}
         */

        private CommodityEntity commodity;
        private AddressEntity address;
        private CommentEntity comment;
        private CommodityDescriptionEntity commodity_description;
        private List<RecommendCommodityEntity> recommend_commodity;

        public CommodityEntity getCommodity() {
            return commodity;
        }

        public void setCommodity(CommodityEntity commodity) {
            this.commodity = commodity;
        }

        public AddressEntity getAddress() {
            return address;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public CommentEntity getComment() {
            return comment;
        }

        public void setComment(CommentEntity comment) {
            this.comment = comment;
        }

        public CommodityDescriptionEntity getCommodity_description() {
            return commodity_description;
        }

        public void setCommodity_description(CommodityDescriptionEntity commodity_description) {
            this.commodity_description = commodity_description;
        }

        public List<RecommendCommodityEntity> getRecommend_commodity() {
            return recommend_commodity;
        }

        public void setRecommend_commodity(List<RecommendCommodityEntity> recommend_commodity) {
            this.recommend_commodity = recommend_commodity;
        }

        public static class CommodityEntity {
            /**
             * c_id : 76
             * c_name : 米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦
             * c_brand : 44
             * c_sort : 74
             * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png
             * c_price : 580.00
             * c_price_old : 0.01
             */

            private String c_id;
            private String c_name;
            private String c_brand;
            private String c_sort;
            private String c_thumbnail;
            private String c_price;
            private String c_price_old;
            private List<String> cp_path;

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

            public String getC_brand() {
                return c_brand;
            }

            public void setC_brand(String c_brand) {
                this.c_brand = c_brand;
            }

            public String getC_sort() {
                return c_sort;
            }

            public void setC_sort(String c_sort) {
                this.c_sort = c_sort;
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

            public List<String> getCp_path() {
                return cp_path;
            }

            public void setCp_path(List<String> cp_path) {
                this.cp_path = cp_path;
            }
        }

        public static class AddressEntity {
            /**
             * ua_id : 786
             * address : 身体还是它带给你打给你的功能
             */

            private String ua_id;
            private String address;

            public String getUa_id() {
                return ua_id;
            }

            public void setUa_id(String ua_id) {
                this.ua_id = ua_id;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public static class CommentEntity {
            /**
             * id : 1087
             * comment : 宝贝不错～～～
             * level_score : 4
             * create_time : 2018-07-16 16:33:29
             * comment_pic4 : null
             * uid : 864
             * nickname : 张亮
             * userheadimg : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIStbRtibGOfVbY1dUth8jP6clnGKJAGgapfll6GibfDp5qVnQXh0cNwI0ABVyhOTODdI366ynvVp7g/132
             * comment_pic : ["https://shop.carsuper.cn/Data/comment/5b5004202eda1.jpg","https://shop.carsuper.cn/Data/comment/5b5002bc32aaa.jpg"]
             * comment_count : 2
             */

            private String id;
            private String comment;
            private String level_score;
            private String create_time;
            private Object comment_pic4;
            private String uid;
            private String nickname;
            private String userheadimg;
            private String comment_count;
            private List<String> comment_pic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public Object getComment_pic4() {
                return comment_pic4;
            }

            public void setComment_pic4(Object comment_pic4) {
                this.comment_pic4 = comment_pic4;
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

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public List<String> getComment_pic() {
                return comment_pic;
            }

            public void setComment_pic(List<String> comment_pic) {
                this.comment_pic = comment_pic;
            }
        }

        public static class CommodityDescriptionEntity {
            /**
             * text : <p><br/></p><p><img src="https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359833431823.jpg" title="1531359833431823.jpg" alt="2.jpg"/><img src="https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359840724655.jpg" title="1531359840724655.jpg" alt="5.jpg"/><img src="https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359845894458.jpg" title="1531359845894458.jpg" alt="6.jpg"/><img src="https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359848132193.jpg" title="1531359848132193.jpg" alt="7.jpg"/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><img src="https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359854222216.jpg" title="1531359854222216.jpg" alt="9.jpg"/><img src="https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359857931293.jpg" title="1531359857931293.jpg" alt="10.jpg"/></p>
             * des_pic : [{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359833431823.jpg","width":750,"height":530},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359840724655.jpg","width":750,"height":472},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359845894458.jpg","width":750,"height":463},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359848132193.jpg","width":750,"height":888},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359854222216.jpg","width":750,"height":671},{"pic":"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359857931293.jpg","width":750,"height":553}]
             */

            private String text;
            private List<DesPicEntity> des_pic;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public List<DesPicEntity> getDes_pic() {
                return des_pic;
            }

            public void setDes_pic(List<DesPicEntity> des_pic) {
                this.des_pic = des_pic;
            }

            public static class DesPicEntity {
                /**
                 * pic : https://shop.ksuper.coahr.com//ueditor/php/upload/image/20180712/1531359833431823.jpg
                 * width : 750
                 * height : 530
                 */

                private String pic;
                private int width;
                private int height;

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }

        public static class RecommendCommodityEntity {
            /**
             * c_id : 136
             * c_name : 1343
             * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1534835009_830590.jpeg
             * c_price : 12.00
             * c_price_old : 0.00
             * c_sold_real : 0
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
    }
}
