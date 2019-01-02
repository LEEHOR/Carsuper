package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/9/3.
 * Email：hengzwdhengzwd@qq.com
 */
public class SaveCommentBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"id":"2018081310297521"}
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
         * id : 2018081310297521
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
