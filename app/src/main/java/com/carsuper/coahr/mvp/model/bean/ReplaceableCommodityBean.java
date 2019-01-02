package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/6.
 * Email：hengzwdhengzwd@qq.com
 */
public class ReplaceableCommodityBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"commodity":[{"c_id":"100","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180805/5b66acf14a967.png","c_name":" 道达尔(Total)快驰5000 矿物油 5W30 SN/GF-5级 4L ","c_price":"189.00"},{"c_id":"99","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b644be360c3e.png","c_name":"道达尔(Total)快驰9000 全合成机油 5W40 SN/GF-5级 1L","c_price":"99.00"},{"c_id":"98","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b644a411f5d0.png","c_name":"道达尔(Total)快驰9000 全合成机油 5W40 SN/GF-5级 4L","c_price":"329.00"},{"c_id":"97","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b64484dcb065.png","c_name":"道达尔(Total)快驰9000 全合成机油 0W30 SN/GF-5级 1L","c_price":"109.00"},{"c_id":"96","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b6420bfbad00.png","c_name":"道达尔(Total)快驰9000 全合成机油 0W30 SN/GF-5级 4L","c_price":"399.00"},{"c_id":"95","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b641c0303b50.png","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 1L","c_price":"99.00"},{"c_id":"94","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b6413c3af5e5.png","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00"}]}
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
        private List<CommodityEntity> commodity;

        public List<CommodityEntity> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<CommodityEntity> commodity) {
            this.commodity = commodity;
        }

        public static class CommodityEntity {
            /**
             * c_id : 100
             * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180805/5b66acf14a967.png
             * c_name :  道达尔(Total)快驰5000 矿物油 5W30 SN/GF-5级 4L
             * c_price : 189.00
             */

            private String c_id;
            private String c_thumbnail;
            private String c_name;
            private String c_price;

            public String getC_id() {
                return c_id;
            }

            public void setC_id(String c_id) {
                this.c_id = c_id;
            }

            public String getC_thumbnail() {
                return c_thumbnail;
            }

            public void setC_thumbnail(String c_thumbnail) {
                this.c_thumbnail = c_thumbnail;
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
        }
    }
}
