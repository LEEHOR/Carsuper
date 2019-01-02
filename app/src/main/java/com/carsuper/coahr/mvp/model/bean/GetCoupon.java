package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/10/16
 * on 18:39
 */
public class GetCoupon {


    /**
     * code : 0
     * msg : success
     * jdata : {"coupon":[{"id":"5","name":"50优惠券","coupon_des":"满500减50优惠券","discount":"50.00","amount":"500.00","coupon_type":"1","type":"1","expiretime":"2018-12-31","has_coupon":1}]}
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
        private List<CouponBean> coupon;

        public List<CouponBean> getCoupon() {
            return coupon;
        }

        public void setCoupon(List<CouponBean> coupon) {
            this.coupon = coupon;
        }

        public static class CouponBean {
            /**
             * id : 5
             * name : 50优惠券
             * coupon_des : 满500减50优惠券
             * discount : 50.00
             * amount : 500.00
             * coupon_type : 1
             * type : 1
             * expiretime : 2018-12-31
             * has_coupon : 1
             */

            private String id;
            private String name;
            private String coupon_des;
            private String discount;
            private String amount;
            private String coupon_type;
            private String type;
            private String expiretime;
            private int has_coupon;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCoupon_des() {
                return coupon_des;
            }

            public void setCoupon_des(String coupon_des) {
                this.coupon_des = coupon_des;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getCoupon_type() {
                return coupon_type;
            }

            public void setCoupon_type(String coupon_type) {
                this.coupon_type = coupon_type;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getExpiretime() {
                return expiretime;
            }

            public void setExpiretime(String expiretime) {
                this.expiretime = expiretime;
            }

            public int getHas_coupon() {
                return has_coupon;
            }

            public void setHas_coupon(int has_coupon) {
                this.has_coupon = has_coupon;
            }
        }
    }
}
