package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/8/28.
 * Email：hengzwdhengzwd@qq.com
 */
public class RefundApplyBean {


    /**
     * code : 0
     * jdata : {"commodity":{"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_num":"1","c_price":"580.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png","o_id":"2018082118164389597946"}}
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
         * commodity : {"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_num":"1","c_price":"580.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png","o_id":"2018082118164389597946"}
         */

        private CommodityEntity commodity;

        public CommodityEntity getCommodity() {
            return commodity;
        }

        public void setCommodity(CommodityEntity commodity) {
            this.commodity = commodity;
        }

        public static class CommodityEntity {
            /**
             * c_id : 76
             * c_name : 米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦
             * c_num : 1
             * c_price : 580.00
             * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png
             * o_id : 2018082118164389597946
             */

            private String c_id;
            private String c_name;
            private String c_num;
            private String c_price;
            private String c_thumbnail;
            private String o_id;

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

            public String getO_id() {
                return o_id;
            }

            public void setO_id(String o_id) {
                this.o_id = o_id;
            }
        }
    }
}
