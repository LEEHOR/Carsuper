package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/8/3.
 * Email：hengzwdhengzwd@qq.com
 */
public class RecommendServiceBean {


    /**
     * code : 0
     * jdata : {"commodity":{"c_id":"119","c_name":" 道达尔(Total)红运6600 柴机油 15W40 SN/GF-5级 4L ","c_price":"119.00","c_thumbnail":"https://shop.carsuper.cn/./Data/Commodity/Thumbnail/p_icon_1533720355_878244.jpeg"},"mycar":{"brand":"奔驰(进口)","car_id":"139","pro_time":"2018","serials":"奔驰Actros","volume":"2.0"}}
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
        /**
         * commodity : {"c_id":"119","c_name":" 道达尔(Total)红运6600 柴机油 15W40 SN/GF-5级 4L ","c_price":"119.00","c_thumbnail":"https://shop.carsuper.cn/./Data/Commodity/Thumbnail/p_icon_1533720355_878244.jpeg"}
         * mycar : {"brand":"奔驰(进口)","car_id":"139","pro_time":"2018","serials":"奔驰Actros","volume":"2.0"}
         */

        private CommodityEntity commodity;
        private MycarEntity mycar;
        /**
         * commodity_filter : {"c_id":"160","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1539765279_945315.jpeg","c_name":"弗列加LF9009机油滤清器","c_price":"124.99","c_addtime":"1539765348"}
         */

        private CommodityFilterBean commodity_filter;

        public CommodityEntity getCommodity() {
            return commodity;
        }

        public void setCommodity(CommodityEntity commodity) {
            this.commodity = commodity;
        }

        public MycarEntity getMycar() {
            return mycar;
        }

        public void setMycar(MycarEntity mycar) {
            this.mycar = mycar;
        }

        public CommodityFilterBean getCommodity_filter() {
            return commodity_filter;
        }

        public void setCommodity_filter(CommodityFilterBean commodity_filter) {
            this.commodity_filter = commodity_filter;
        }
    }

        public static class CommodityEntity {
            /**
             * c_id : 119
             * c_name :  道达尔(Total)红运6600 柴机油 15W40 SN/GF-5级 4L
             * c_price : 119.00
             * c_thumbnail : https://shop.carsuper.cn/./Data/Commodity/Thumbnail/p_icon_1533720355_878244.jpeg
             */

            private String c_id;
            private String c_name;
            private String c_price;
            private String c_thumbnail;

            public String getC_id() {
                return c_id;
            }

            public void setC_id(String c_id) {
                this.c_id = c_id;
            }

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getC_price() {
                return c_price;
            }

            public void setC_price(String c_price) {
                this.c_price = c_price;
            }

            public String getC_thumbnail() {
                return c_thumbnail;
            }

            public void setC_thumbnail(String c_thumbnail) {
                this.c_thumbnail = c_thumbnail;
            }
        }

        public static class MycarEntity {
            /**
             * brand : 奔驰(进口)
             * car_id : 139
             * pro_time : 2018
             * serials : 奔驰Actros
             * volume : 2.0
             * 	"car_id":"525",
             *     			"brand":"东风商用车",
             *     			"serials":"天龙",
             *     			"motor":"东风康明斯",
             *     			"horsepower":"430"
             */

            private String brand;
            private String car_id;
            private String horsepower;
            private String serials;
            private String motor;

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getCar_id() {
                return car_id;
            }

            public void setCar_id(String car_id) {
                this.car_id = car_id;
            }

            public String getSerials() {
                return serials;
            }

            public void setSerials(String serials) {
                this.serials = serials;
            }

            public String getHorsepower() {
                return horsepower;
            }

            public void setHorsepower(String horsepower) {
                this.horsepower = horsepower;
            }

            public String getMotor() {
                return motor;
            }

            public void setMotor(String motor) {
                this.motor = motor;
            }

        }
    public static class CommodityFilterBean {
        /**
         * c_id : 160
         * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1539765279_945315.jpeg
         * c_name : 弗列加LF9009机油滤清器
         * c_price : 124.99
         * c_addtime : 1539765348
         */

        private String c_id;
        private String c_thumbnail;
        private String c_name;
        private String c_price;
        private String c_addtime;

        public String getC_id() {
            return c_id;
        }

        public void setC_id(String c_id) {
            this.c_id = c_id;
        }

        public String getC_thumbnail() {
            return c_thumbnail;
        }

        public void setC_thumbnail(String c_thumbnail) {
            this.c_thumbnail = c_thumbnail;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getC_price() {
            return c_price;
        }

        public void setC_price(String c_price) {
            this.c_price = c_price;
        }

        public String getC_addtime() {
            return c_addtime;
        }

        public void setC_addtime(String c_addtime) {
            this.c_addtime = c_addtime;
        }
    }



}
