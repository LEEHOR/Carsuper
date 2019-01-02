package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/24.
 * Email：hengzwdhengzwd@qq.com
 */
public class ShoppingMallBean {


    /**
     * code : 0
     * jdata : {"brand":[{"b_id":"53","b_name":"马牌"},{"b_id":"54","b_name":"普利司通"},{"b_id":"55","b_name":"美孚"},{"b_id":"56","b_name":"固特异"},{"b_id":"45","b_name":"马勒"}],"commodity":[{"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_price":"579.00","c_price_old":"599.00","c_sold_real":"0","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png"},{"c_id":"67","c_name":"道达尔汇通（Total快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00","c_price_old":"359.00","c_sold_real":"0","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180704/5b3c35d08b3c8.png"}],"sort":[{"s_id":"75","s_name":"配件"},{"s_id":"53","s_name":"机油"},{"s_id":"73","s_name":"轮胎"}]}
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
        private List<BrandEntity> brand;
        private List<CommodityEntity> commodity;
        private List<SortEntity> sort;

        public List<BrandEntity> getBrand() {
            return brand;
        }

        public void setBrand(List<BrandEntity> brand) {
            this.brand = brand;
        }

        public List<CommodityEntity> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<CommodityEntity> commodity) {
            this.commodity = commodity;
        }

        public List<SortEntity> getSort() {
            return sort;
        }

        public void setSort(List<SortEntity> sort) {
            this.sort = sort;
        }

        public static class BrandEntity {
            /**
             * b_id : 53
             * b_name : 马牌
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String b_name) {
                this.name = name;
            }
        }

        public static class CommodityEntity {
            /**
             * c_id : 76
             * c_name : 米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦
             * c_price : 579.00
             * c_price_old : 599.00
             * c_sold_real : 0
             * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png
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

        public static class SortEntity {
            /**
             * s_id : 75
             * s_name : 配件
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String s_id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String s_name) {
                this.name = name;
            }
        }
    }
}
