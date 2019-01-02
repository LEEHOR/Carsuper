package com.carsuper.coahr.mvp.model.bean;

/**
 * Created by Leehor
 * on 2018/10/16
 * on 9:49
 */
public class Coupon_Used {


    /**
     * code : 0
     * msg : success
     * jdata : {"coupon":{"id":"53","name":"200优惠券","description":"满600减100优惠券","discount":"100.00","coupon_type":"1","type":"1"}}
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
        /**
         * coupon : {"id":"53","name":"200优惠券","description":"满600减100优惠券","discount":"100.00","coupon_type":"1","type":"1"}
         */

        private CouponBean coupon;

        public CouponBean getCoupon() {
            return coupon;
        }

        public void setCoupon(CouponBean coupon) {
            this.coupon = coupon;
        }

        public static class CouponBean {
            /**
             * id : 53
             * name : 200优惠券
             * description : 满600减100优惠券
             * discount : 100.00
             * coupon_type : 1
             * type : 1
             */

            private String id;
            private String name;
            private String description;
            private String discount;
            private String amount;
            private String coupon_type;
            private String type;

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
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

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }
        }
    }
}
