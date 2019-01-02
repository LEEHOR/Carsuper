package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/8/31.
 * Email：hengzwdhengzwd@qq.com
 */
public class QuickPayBean {


    /**
     * code : 0
     * jdata : {"order_json":{"appid":"wx89f3b1477df1aa39","mch_id":"1512629621","nonce_str":"PRj6Eij7cIEGli2w","prepay_id":"wx31114814281749544752ecc81657778237","result_code":"SUCCESS","return_code":"SUCCESS","return_msg":"OK","sign":"0E3C418CCD4CCA7ED067C0D97F679F38","trade_type":"APP"},"order_string":""}
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
         * order_json : {"appid":"wx89f3b1477df1aa39","mch_id":"1512629621","nonce_str":"PRj6Eij7cIEGli2w","prepay_id":"wx31114814281749544752ecc81657778237","result_code":"SUCCESS","return_code":"SUCCESS","return_msg":"OK","sign":"0E3C418CCD4CCA7ED067C0D97F679F38","trade_type":"APP"}
         * order_string :
         */

        private OrderJsonEntity order_json;
        private String order_string;

        public OrderJsonEntity getOrder_json() {
            return order_json;
        }

        public void setOrder_json(OrderJsonEntity order_json) {
            this.order_json = order_json;
        }

        public String getOrder_string() {
            return order_string;
        }

        public void setOrder_string(String order_string) {
            this.order_string = order_string;
        }

    }
}
