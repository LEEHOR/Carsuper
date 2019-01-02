package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/7/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class LoginBean {








    /**
     * code : 0
     * msg : success
     * jdata : {"token":"\u201dabcde\u201d","uid":456,"wxid":"\u201dabcdef\u201d","phone":"\u201d456\u201d"}
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
         * token : ”abcde”
         * uid : 456
         * wxid : ”abcdef”
         * phone : ”456”
         */

        private String token;
        private int uid;
        private String wxid;
        private String phone;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getWxid() {
            return wxid;
        }

        public void setWxid(String wxid) {
            this.wxid = wxid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
