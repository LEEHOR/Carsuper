package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/8/3.
 * Email：hengzwdhengzwd@qq.com
 */
public class SaveUserCarBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"car_id":"524","motor":"云内动力","horsepower":"170","brand":"江淮骏铃","serials":"骏铃V9L","pic":"https://shop.carsuper.cn/Data/Car/20181019/5bc94f741d756.jpg"}
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
         * car_id : 524
         * motor : 云内动力
         * horsepower : 170
         * brand : 江淮骏铃
         * serials : 骏铃V9L
         * pic : https://shop.carsuper.cn/Data/Car/20181019/5bc94f741d756.jpg
         */

        private String car_id;
        private String motor;
        private String horsepower;
        private String brand;
        private String serials;
        private String pic;

        public String getCar_id() {
            return car_id;
        }

        public void setCar_id(String car_id) {
            this.car_id = car_id;
        }

        public String getMotor() {
            return motor;
        }

        public void setMotor(String motor) {
            this.motor = motor;
        }

        public String getHorsepower() {
            return horsepower;
        }

        public void setHorsepower(String horsepower) {
            this.horsepower = horsepower;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getSerials() {
            return serials;
        }

        public void setSerials(String serials) {
            this.serials = serials;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
