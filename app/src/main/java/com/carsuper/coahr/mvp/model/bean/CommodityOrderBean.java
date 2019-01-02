package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/9.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityOrderBean {


    /**
     * code : 0
     * jdata : {"order_list":[{"commodity":[{"c_id":"97","c_name":"道达尔(Total)快驰9000 全合成机油 0W30 SN/GF-5级 1L","c_num":"2","c_price":"11.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b64484dcb065.png"},{"c_id":"95","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 1L","c_num":"3","c_price":"22.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b641c0303b50.png"}],"create_time":"2018-08-07 17:50:39","o_status":"0","order_id":"ksb2018080717011242","total":"222.00"},{"commodity":[{"c_id":"94","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_num":"4","c_price":"33.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b6413c3af5e5.png"}],"create_time":"2018-08-07 17:50:39","o_status":"1","order_id":"2018080717011246","total":"333.00"},{"commodity":[{"c_id":"98","c_name":"道达尔(Total)快驰9000 全合成机油 5W40 SN/GF-5级 4L","c_num":"6","c_price":"66.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180807/5b696125321ff.png"}],"create_time":"2018-08-07 17:50:39","o_status":"4","order_id":"2018080717011249","total":"555.00"},{"commodity":[{"c_id":"100","c_name":" 道达尔(Total)快驰5000 矿物油 5W30 SN/GF-5级 4L ","c_num":"8","c_price":"88.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180805/5b66acf14a967.png"}],"create_time":"2018-08-07 17:50:39","o_status":"5","order_id":"2018080717011250","total":"666.00"},{"commodity":[{"c_id":"99","c_name":"道达尔(Total)快驰9000 全合成机油 5W40 SN/GF-5级 1L","c_num":"7","c_price":"77.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b644be360c3e.png"}],"create_time":"2018-08-07 17:50:39","o_status":"4","order_id":"2018080717011251","total":"777.00"},{"commodity":[{"c_id":"98","c_name":"道达尔(Total)快驰9000 全合成机油 5W40 SN/GF-5级 4L","c_num":"1","c_price":"21.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180807/5b696125321ff.png"}],"create_time":"2018-08-07 17:50:39","o_status":"-1","order_id":"2018080717011243","total":"111.00"}]}
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
        private List<OrderListEntity> order_list;

        public List<OrderListEntity> getOrder_list() {
            return order_list;
        }

        public void setOrder_list(List<OrderListEntity> order_list) {
            this.order_list = order_list;
        }

        public static class OrderListEntity {
            /**
             * commodity : [{"c_id":"97","c_name":"道达尔(Total)快驰9000 全合成机油 0W30 SN/GF-5级 1L","c_num":"2","c_price":"11.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b64484dcb065.png"},{"c_id":"95","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 1L","c_num":"3","c_price":"22.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b641c0303b50.png"}]
             * create_time : 2018-08-07 17:50:39
             * o_status : 0
             * order_id : ksb2018080717011242
             * total : 222.00
             */

            private String create_time;
            private String o_status;
            private String order_id;
            private String total;
            private List<CommodityEntity> commodity;

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getO_status() {
                return o_status;
            }

            public void setO_status(String o_status) {
                this.o_status = o_status;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public List<CommodityEntity> getCommodity() {
                return commodity;
            }

            public void setCommodity(List<CommodityEntity> commodity) {
                this.commodity = commodity;
            }

            public static class CommodityEntity {
                /**
                 * c_id : 97
                 * c_name : 道达尔(Total)快驰9000 全合成机油 0W30 SN/GF-5级 1L
                 * c_num : 2
                 * c_price : 11.00
                 * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b64484dcb065.png
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
}
