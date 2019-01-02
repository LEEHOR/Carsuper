package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/7/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class PhoneMessageBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"verify_code":123456}
     */

    private int code;
    private String msg;
    private JdataEntity jdata;

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

    public JdataEntity getJdata() {
        return jdata;
    }

    public void setJdata(JdataEntity jdata) {
        this.jdata = jdata;
    }

    public static class JdataEntity {
        /**
         * verify_code : 123456
         */

        private int verify_code;

        public int getVerify_code() {
            return verify_code;
        }

        public void setVerify_code(int verify_code) {
            this.verify_code = verify_code;
        }
    }
}
