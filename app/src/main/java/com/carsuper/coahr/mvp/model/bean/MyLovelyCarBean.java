package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/15.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyLovelyCarBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"mycar":[{"car_id":"536","brand":"北奔重卡","motor":"潍柴","horsepower":null,"serials":"V3MT","default_check":"1","pic":"https://shop.carsuper.cn/Data/Car/20181019/5bc94f1051d23.jpg"},{"car_id":"525","brand":"东风商用车","motor":null,"horsepower":null,"serials":"天龙","default_check":"-1","pic":"https://shop.carsuper.cn/Data/Car/20181019/5bc94ec00bbf3.png"},{"car_id":"526","brand":"北奔重卡","motor":null,"horsepower":null,"serials":"V3HT","default_check":"-1","pic":"https://shop.carsuper.cn/Data/Car/20181019/5bc94f1051d23.jpg"},{"car_id":"527","brand":"一汽解放","motor":null,"horsepower":null,"serials":"J6M","default_check":"-1","pic":"https://shop.carsuper.cn/Data/Car/20181019/5bc94f298853e.jpg"},{"car_id":"528","brand":"一汽解放","motor":null,"horsepower":null,"serials":"J6M","default_check":"-1","pic":"https://shop.carsuper.cn/Data/Car/20181019/5bc94f298853e.jpg"},{"car_id":"529","brand":"一汽解放","motor":null,"horsepower":null,"serials":"J6L","default_check":"-1","pic":"https://shop.carsuper.cn/Data/Car/20181019/5bc94f298853e.jpg"},{"car_id":"530","brand":"长安商用","motor":null,"horsepower":null,"serials":"长安星卡","default_check":"-1","pic":"https://shop.carsuper.cn/Data/Car/20181019/5bc94fbbbafff.jpg"},{"car_id":"532","brand":"长安跨越","motor":null,"horsepower":null,"serials":"跨越王","default_check":"-1","pic":"https://shop.carsuper.cn/Data/Car/20181019/5bc94fb60ed12.jpg"},{"car_id":"533","brand":"北奔重卡","motor":null,"horsepower":null,"serials":"V3MT","default_check":"-1","pic":"https://shop.carsuper.cn/Data/Car/20181019/5bc94f1051d23.jpg"},{"car_id":"534","brand":"北奔重卡","motor":null,"horsepower":null,"serials":"V3","default_check":"-1","pic":"https://shop.carsuper.cn/Data/Car/20181019/5bc94f1051d23.jpg"},{"car_id":"535","brand":"长安跨越","motor":"小康动力","horsepower":null,"serials":"跨越王","default_check":"-1","pic":"https://shop.carsuper.cn/Data/Car/20181019/5bc94fb60ed12.jpg"}]}
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
        private List<MycarBean> mycar;

        public List<MycarBean> getMycar() {
            return mycar;
        }

        public void setMycar(List<MycarBean> mycar) {
            this.mycar = mycar;
        }

        public static class MycarBean {
            /**
             * car_id : 536
             * brand : 北奔重卡
             * motor : 潍柴
             * horsepower : 380
             * serials : V3MT
             * default_check : 1
             * pic : https://shop.carsuper.cn/Data/Car/20181019/5bc94f1051d23.jpg
             */

            private String car_id;
            private String brand;
            private String motor;
            private String horsepower;
            private String serials;
            private String default_check;
            private String pic;

            public String getCar_id() {
                return car_id;
            }

            public void setCar_id(String car_id) {
                this.car_id = car_id;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
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

            public String getSerials() {
                return serials;
            }

            public void setSerials(String serials) {
                this.serials = serials;
            }

            public String getDefault_check() {
                return default_check;
            }

            public void setDefault_check(String default_check) {
                this.default_check = default_check;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
