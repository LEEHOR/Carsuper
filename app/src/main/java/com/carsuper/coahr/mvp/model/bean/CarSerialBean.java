package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarSerialBean {


    /**
     * code : 0
     * jdata : {"serial":[{"cs_id":"25","cs_name":"奥驰T系轻卡"},{"cs_id":"188","cs_name":"奥驰D系轻卡"},{"cs_id":"198","cs_name":"奥驰微卡"},{"cs_id":"205","cs_name":"奥驰轻卡"},{"cs_id":"242","cs_name":"奥驰V系轻卡"},{"cs_id":"254","cs_name":"奥驰M系微卡"}]}
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
        private List<SerialEntity> serial;

        public List<SerialEntity> getSerial() {
            return serial;
        }

        public void setSerial(List<SerialEntity> serial) {
            this.serial = serial;
        }

        public static class SerialEntity {
            /**
             * cs_id : 25
             * cs_name : 奥驰T系轻卡
             */

            private String cs_id;
            private String cs_name;

            public String getCs_id() {
                return cs_id;
            }

            public void setCs_id(String cs_id) {
                this.cs_id = cs_id;
            }

            public String getCs_name() {
                return cs_name;
            }

            public void setCs_name(String cs_name) {
                this.cs_name = cs_name;
            }
        }
    }
}
