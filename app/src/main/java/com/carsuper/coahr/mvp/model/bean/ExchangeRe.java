package com.carsuper.coahr.mvp.model.bean;

/**
 * Created by Leehor
 * on 2018/12/29
 * on 10:23
 */
public class ExchangeRe {

    /**
     * code : 10105
     * msg : 领取状态异常!请检查后重试!
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
    }
}
