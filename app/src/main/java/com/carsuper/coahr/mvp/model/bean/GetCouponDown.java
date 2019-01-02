package com.carsuper.coahr.mvp.model.bean;

/**
 * Created by Leehor
 * on 2018/10/16
 * on 19:06
 * 优惠券领取成功接口
 */
public class GetCouponDown {

    /**
     * code : 0
     * msg : success
     * jdata : {"jmsg":"领取成功"}
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
         * jmsg : 领取成功
         */

        private String jmsg;

        public String getJmsg() {
            return jmsg;
        }

        public void setJmsg(String jmsg) {
            this.jmsg = jmsg;
        }
    }
}
