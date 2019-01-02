package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/8/15.
 * Email：hengzwdhengzwd@qq.com
 */
public class ResultBean {//统一格式的简单json格式网络返回

    /**
     * code : 0
     * msg : success
     * jdata : {"jmsg":"删除成功"}
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
         * jmsg : 删除成功
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
