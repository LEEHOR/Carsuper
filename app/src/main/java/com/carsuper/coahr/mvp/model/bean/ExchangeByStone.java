package com.carsuper.coahr.mvp.model.bean;

/**
 * Created by Leehor
 * on 2018/12/29
 * on 10:16
 */
public class ExchangeByStone {

    /**
     * code : 10101
     * msg : 对不起!您的宝石不够兑换!
     * jdata : {}
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
        private String o_status;

        public String getO_status() {
            return o_status;
        }

        public void setO_status(String o_status) {
            this.o_status = o_status;
        }
    }
}
