package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/8/8.
 * Email：hengzwdhengzwd@qq.com
 */
public class ConfirmOrderBean {


    /**
     * code : 0
     * jdata : {"order":"ksb201808291641399704","order_json":{"appid":"wx89f3b1477df1aa39","mch_id":"1512629621","nonce_str":"dDM0E4DhT2xDsMJd","prepay_id":"wx29164140290714916a90cf7a3883421205","result_code":"SUCCESS","return_code":"SUCCESS","return_msg":"OK","sign":"B8C0A6A4740913A2126EEB3360440DCC","trade_type":"APP"},"order_string":""}
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
         * order : ksb201808291641399704
         * order_json : {"appid":"wx89f3b1477df1aa39","mch_id":"1512629621","nonce_str":"dDM0E4DhT2xDsMJd","prepay_id":"wx29164140290714916a90cf7a3883421205","result_code":"SUCCESS","return_code":"SUCCESS","return_msg":"OK","sign":"B8C0A6A4740913A2126EEB3360440DCC","trade_type":"APP"}
         * order_string :
         */

        private String order;
        private OrderJsonEntity order_json;
        private String order_string;

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

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
