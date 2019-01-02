package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/12/29
 * on 10:06
 */
public class ExchangeMallList {

    /**
     * code : 0
     * msg : success
     * jdata : {"commodity":[{"c_id":"150","c_name":"打火机","c_thumbnail":"https://shop.carsuper.cn/Data/BonusCommodity/Thumbnail/p_icon_1545978827_578344.jpeg","c_price":"50.00"},{"c_id":"151","c_name":"救援绳","c_thumbnail":"https://shop.carsuper.cn/Data/BonusCommodity/Thumbnail/p_icon_1545978920_668652.jpeg","c_price":"80.00"},{"c_id":"152","c_name":"茶杯","c_thumbnail":"https://shop.carsuper.cn/Data/BonusCommodity/Thumbnail/p_icon_1545978701_367352.jpeg","c_price":"30.00"}]}
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
        private List<CommodityBean> commodity;

        public List<CommodityBean> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<CommodityBean> commodity) {
            this.commodity = commodity;
        }

        public static class CommodityBean {
            /**
             * c_id : 150
             * c_name : 打火机
             * c_thumbnail : https://shop.carsuper.cn/Data/BonusCommodity/Thumbnail/p_icon_1545978827_578344.jpeg
             * c_price : 50.00
             */

            private String c_id;
            private String c_name;
            private String c_thumbnail;
            private String c_price;

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
        }
    }
}
