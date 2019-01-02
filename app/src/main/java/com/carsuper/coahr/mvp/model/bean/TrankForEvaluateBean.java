package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/27.
 * Email：hengzwdhengzwd@qq.com
 */
public class TrankForEvaluateBean {


    /**
     * code : 0
     * jdata : {"commodity":[{"c_id":"137","c_name":"米其林","c_price":"10.00","c_sold_real":"0","c_thumbnail":"/Data/Commodity/Thumbnail/p_icon_1535530211_980938.jpeg"}],"order":[{"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png","order_id":"2018082118145219387511"}]}
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
        private List<RecommendEntity> commodity;
        private List<OrderEntity> order;

        public List<RecommendEntity> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<RecommendEntity> commodity) {
            this.commodity = commodity;
        }

        public List<OrderEntity> getOrder() {
            return order;
        }

        public void setOrder(List<OrderEntity> order) {
            this.order = order;
        }
//
//        public static class CommodityEntity {
//            /**
//             * c_id : 137
//             * c_name : 米其林
//             * c_price : 10.00
//             * c_sold_real : 0
//             * c_thumbnail : /Data/Commodity/Thumbnail/p_icon_1535530211_980938.jpeg
//             */
//
//            private String c_id;
//            private String c_name;
//            private String c_price;
//            private String c_sold_real;
//            private String c_thumbnail;
//
//            public String getC_id() {
//                return c_id;
//            }
//
//            public void setC_id(String c_id) {
//                this.c_id = c_id;
//            }
//
//            public String getC_name() {
//                return c_name;
//            }
//
//            public void setC_name(String c_name) {
//                this.c_name = c_name;
//            }
//
//            public String getC_price() {
//                return c_price;
//            }
//
//            public void setC_price(String c_price) {
//                this.c_price = c_price;
//            }
//
//            public String getC_sold_real() {
//                return c_sold_real;
//            }
//
//            public void setC_sold_real(String c_sold_real) {
//                this.c_sold_real = c_sold_real;
//            }
//
//            public String getC_thumbnail() {
//                return c_thumbnail;
//            }
//
//            public void setC_thumbnail(String c_thumbnail) {
//                this.c_thumbnail = c_thumbnail;
//            }
//        }

        public static class OrderEntity {
            /**
             * c_id : 76
             * c_name : 米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦
             * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png
             * order_id : 2018082118145219387511
             */

            private String c_id;
            private String c_name;
            private String c_thumbnail;
            private String order_id;
            private String type;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

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

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }
        }
    }
}
