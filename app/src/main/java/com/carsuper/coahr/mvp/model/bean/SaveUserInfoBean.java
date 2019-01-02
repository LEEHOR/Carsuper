package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/8/20.
 * Email：hengzwdhengzwd@qq.com
 */
public class SaveUserInfoBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"userheadimg":"https://shop.carsuper.cn/Data/User/pic1_5b7a81773b06e.jpg"}
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
         * userheadimg : https://shop.carsuper.cn/Data/User/pic1_5b7a81773b06e.jpg
         */

        private String userheadimg;

        public String getUserheadimg() {
            return userheadimg;
        }

        public void setUserheadimg(String userheadimg) {
            this.userheadimg = userheadimg;
        }
    }
}
