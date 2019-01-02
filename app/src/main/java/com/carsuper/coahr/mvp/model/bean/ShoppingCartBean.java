package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/20.
 * Email：hengzwdhengzwd@qq.com
 */
public class ShoppingCartBean {


    /**
     * code : 0
     * jdata : {"commodity":[{"c_id":"119","c_name":" 道达尔(Total)红运6600 柴机油 15W40 SN/GF-5级 4L ","c_num":"1","c_price":"119.00","c_thumbnail":"https://shop.carsuper.cn/./Data/Commodity/Thumbnail/p_icon_1533720355_878244.jpeg"}],"recommend":[{"c_id":"94","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00","c_sold_real":"4","c_thumbnail":"/Data/Commodity/Thumbnail/20180803/5b6413c3af5e5.png"},{"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_price":"580.00","c_sold_real":"0","c_thumbnail":"/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png"}]}
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
        private List<CommodityEntity> commodity;
        private List<RecommendEntity> recommend;

        public List<CommodityEntity> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<CommodityEntity> commodity) {
            this.commodity = commodity;
        }

        public List<RecommendEntity> getRecommend() {
            return recommend;
        }

        public void setRecommend(List<RecommendEntity> recommend) {
            this.recommend = recommend;
        }

        public static class CommodityEntity {
            /**
             * c_id : 119
             * c_name :  道达尔(Total)红运6600 柴机油 15W40 SN/GF-5级 4L
             * c_num : 1
             * c_price : 119.00
             * c_thumbnail : https://shop.carsuper.cn/./Data/Commodity/Thumbnail/p_icon_1533720355_878244.jpeg
             */

            private String c_id;
            private String c_name;
            private String c_num;
            private String c_price;
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

            public String getC_num() {
                return c_num;
            }

            public void setC_num(String c_num) {
                this.c_num = c_num;
            }

            public String getC_price() {
                return c_price;
            }

            public void setC_price(String c_price) {
                this.c_price = c_price;
            }

            public String getC_thumbnail() {
                return c_thumbnail;
            }

            public void setC_thumbnail(String c_thumbnail) {
                this.c_thumbnail = c_thumbnail;
            }
        }


    }
}
