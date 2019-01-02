package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarHorsePowerBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"car_detail":[{"id":"965","horsepower":"170"},{"id":"966","horsepower":"190"}]}
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
        private List<CarDetailBean> car_detail;

        public List<CarDetailBean> getCar_detail() {
            return car_detail;
        }

        public void setCar_detail(List<CarDetailBean> car_detail) {
            this.car_detail = car_detail;
        }

        public static class CarDetailBean {
            /**
             * id : 965
             * horsepower : 170
             */

            private String id;
            private String horsepower;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getHorsepower() {
                return horsepower;
            }

            public void setHorsepower(String horsepower) {
                this.horsepower = horsepower;
            }
        }
    }
}
