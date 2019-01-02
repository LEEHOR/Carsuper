package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/9.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityOrderDetailBean {


    /**
     * code : 0
     * jdata : {"address":{"address":"后悔了5名哦","telephone":"1524245455454","username":"哈哈"},"commodity":[{"c_id":"119","c_name":" 道达尔(Total)红运6600 柴机油 15W40 SN/GF-5级 4L ","c_num":"1","c_price":"119.00","c_thumbnail":"https://shop.carsuper.cn/./Data/Commodity/Thumbnail/p_icon_1533720355_878244.jpeg"}],"logistics":[],"order":{"create_time":"2018-08-21 17:02:03","fee":0,"o_status":"5","order_id":"2018082117020364072370","total":"119.00"},"refund_order":{"addtime":"2018-08-30 10:03:59","des_pic":[],"id":"2018083010035916272814","reason":"未按约定时间发货","refund":"119.00","status":"1"}}
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
         * address : {"address":"后悔了5名哦","telephone":"1524245455454","username":"哈哈"}
         * commodity : [{"c_id":"119","c_name":" 道达尔(Total)红运6600 柴机油 15W40 SN/GF-5级 4L ","c_num":"1","c_price":"119.00","c_thumbnail":"https://shop.carsuper.cn/./Data/Commodity/Thumbnail/p_icon_1533720355_878244.jpeg"}]
         * logistics : []
         * order : {"create_time":"2018-08-21 17:02:03","fee":0,"o_status":"5","order_id":"2018082117020364072370","total":"119.00"}
         * refund_order : {"addtime":"2018-08-30 10:03:59","des_pic":[],"id":"2018083010035916272814","reason":"未按约定时间发货","refund":"119.00","status":"1"}
         */

        private AddressEntity address;
        private OrderEntity order;
        private RefundOrderEntity refund_order;
        private List<CommodityEntity> commodity;
        private List<LogisticsEntity> logistics;

        public AddressEntity getAddress() {
            return address;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public OrderEntity getOrder() {
            return order;
        }

        public void setOrder(OrderEntity order) {
            this.order = order;
        }

        public RefundOrderEntity getRefund_order() {
            return refund_order;
        }

        public void setRefund_order(RefundOrderEntity refund_order) {
            this.refund_order = refund_order;
        }

        public List<CommodityEntity> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<CommodityEntity> commodity) {
            this.commodity = commodity;
        }

        public List<LogisticsEntity> getLogistics() {
            return logistics;
        }

        public void setLogistics(List<LogisticsEntity> logistics) {
            this.logistics = logistics;
        }

        public static class AddressEntity {
            /**
             * address : 后悔了5名哦
             * telephone : 1524245455454
             * username : 哈哈
             */

            private String address;
            private String telephone;
            private String username;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public static class OrderEntity {
            /**
             * create_time : 2018-08-21 17:02:03
             * fee : 0
             * o_status : 5
             * order_id : 2018082117020364072370
             * total : 119.00
             */

            private String create_time;
            private int fee;
            private String o_status;
            private String order_id;
            private String total;

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
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
        }

        public static class RefundOrderEntity {
            /**
             * addtime : 2018-08-30 10:03:59
             * des_pic : []
             * id : 2018083010035916272814
             * reason : 未按约定时间发货
             * refund : 119.00
             * status : 1
             */

            private String addtime;
            private String id;
            private String reason;
            private String refund;
            private String status;
            private List<String> des_pic;

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getRefund() {
                return refund;
            }

            public void setRefund(String refund) {
                this.refund = refund;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<String> getDes_pic() {
                return des_pic;
            }

            public void setDes_pic(List<String> des_pic) {
                this.des_pic = des_pic;
            }
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
