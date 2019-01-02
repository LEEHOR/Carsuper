package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Date：2018/10/11
 * Time：15:16
 * Created by Leehor
 */
public class CouponBean {
    /**
     * code : 0
     * msg : success
     * jdata : {"coupon_enable":[{"id":"3","discount":"0.00","amount":"0.00","name":"服务全免","coupon_des":"服务全免","expiretime":"2018-12-01","e_status":1},{"id":"3","discount":"0.00","amount":"0.00","name":"服务全免","coupon_des":"服务全免","expiretime":"2018-12-01","e_status":1}],"coupon_disable":[{"id":"3","discount":"0.00","amount":"0.00","name":"服务全免","coupon_des":"服务全免","expiretime":"2018-12-01","e_status":0}]}
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
        private List<CouponEnableBean> coupon_enable;
        private List<CouponDisableBean> coupon_disable;

        public List<CouponEnableBean> getCoupon_enable() {
            return coupon_enable;
        }

        public void setCoupon_enable(List<CouponEnableBean> coupon_enable) {
            this.coupon_enable = coupon_enable;
        }

        public List<CouponDisableBean> getCoupon_disable() {
            return coupon_disable;
        }

        public void setCoupon_disable(List<CouponDisableBean> coupon_disable) {
            this.coupon_disable = coupon_disable;
        }

        public static class CouponEnableBean {
            /**
             * id : 3
             * discount : 0.00
             * amount : 0.00
             * name : 服务全免
             * coupon_des : 服务全免
             * expiretime : 2018-12-01
             * e_status : 1
             */

            private String id;
            private String discount;
            private String amount;
            private String name;
            private String coupon_des;
            private String expiretime;
            private int e_status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getExpiretime() {
                return expiretime;
            }

            public void setExpiretime(String expiretime) {
                this.expiretime = expiretime;
            }

            public int getE_status() {
                return e_status;
            }

            public void setE_status(int e_status) {
                this.e_status = e_status;
            }
        }

        public static class CouponDisableBean {
            /**
             * id : 3
             * discount : 0.00
             * amount : 0.00
             * name : 服务全免
             * coupon_des : 服务全免
             * expiretime : 2018-12-01
             * e_status : 0
             */

            private String id;
            private String discount;
            private String amount;
            private String name;
            private String coupon_des;
            private String expiretime;
            private int e_status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getExpiretime() {
                return expiretime;
            }

            public void setExpiretime(String expiretime) {
                this.expiretime = expiretime;
            }

            public int getE_status() {
                return e_status;
            }

            public void setE_status(int e_status) {
                this.e_status = e_status;
            }
        }
    }


}
