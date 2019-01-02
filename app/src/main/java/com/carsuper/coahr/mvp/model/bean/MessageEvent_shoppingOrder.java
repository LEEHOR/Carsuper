package com.carsuper.coahr.mvp.model.bean;

/**
 * Created by Leehor
 * on 2018/11/2
 * on 14:31
 */
public class MessageEvent_shoppingOrder {


  private String coupon_id;
    private String coupon_name;
    private String discount;
    private String amount;
    private String type;

    public MessageEvent_shoppingOrder(String coupon_id, String coupon_name, String discount, String amount, String type) {
        this.coupon_id = coupon_id;
        this.coupon_name = coupon_name;
        this.discount = discount;
        this.amount = amount;
        this.type = type;
    }

    public MessageEvent_shoppingOrder() {
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
