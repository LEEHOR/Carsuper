package com.carsuper.coahr.mvp.model.bean;

/**
 * Created by Leehor
 * on 2018/11/2
 * on 14:31
 */
public class MessageEvent_coupon {


    private int left_count;
    private int right_count;

    public MessageEvent_coupon() {
    }

    public MessageEvent_coupon(int left_count, int right_count){
        this.left_count=left_count;
        this.right_count=right_count;
    }

    public int getLeft_count() {
        return left_count;
    }

    public void setLeft_count(int left_count) {
        this.left_count = left_count;
    }

    public int getRight_count() {
        return right_count;
    }

    public void setRight_count(int right_count) {
        this.right_count = right_count;
    }
}
