package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class MaintanceOrderDetailBean {

    /**
     * code : 0
     * jdata : {"appoint":{"address":"农民工","appoint_date":"2018-08-13","appoint_time":"15:06:00","assignment":0,"car":"奔驰(进口)    奔驰Actros    2.0    2018","nickname":"徐恒","o_status":"0","phone":""},"commodity":[{"c_id":"119","c_name":" 道达尔(Total)红运6600 柴机油 15W40 SN/GF-5级 4L ","c_num":"1","c_price":"119.00","c_thumbnail":"https://shop.carsuper.cn/./Data/Commodity/Thumbnail/p_icon_1533720355_878244.jpeg"}],"order":{"create_time":"2018-08-13 15:06:54","fee":200,"o_status":"0","order_id":"2018081310199485","total":369}}
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
         * appoint : {"address":"农民工","appoint_date":"2018-08-13","appoint_time":"15:06:00","assignment":0,"car":"奔驰(进口)    奔驰Actros    2.0    2018","nickname":"徐恒","o_status":"0","phone":""}
         * commodity : [{"c_id":"119","c_name":" 道达尔(Total)红运6600 柴机油 15W40 SN/GF-5级 4L ","c_num":"1","c_price":"119.00","c_thumbnail":"https://shop.carsuper.cn/./Data/Commodity/Thumbnail/p_icon_1533720355_878244.jpeg"}]
         * order : {"create_time":"2018-08-13 15:06:54","fee":200,"o_status":"0","order_id":"2018081310199485","total":369}
         */

        private AppointEntity appoint;
        private OrderEntity order;
        private List<CommodityEntity> commodity;
        private Station station;
        public AppointEntity getAppoint() {
            return appoint;
        }

        public void setAppoint(AppointEntity appoint) {
            this.appoint = appoint;
        }

        public OrderEntity getOrder() {
            return order;
        }

        public void setOrder(OrderEntity order) {
            this.order = order;
        }

        public List<CommodityEntity> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<CommodityEntity> commodity) {
            this.commodity = commodity;
        }

        public Station getStation() {
            return station;
        }

        public void setStation(Station station) {
            this.station = station;
        }

        public static class AppointEntity {
            /**
             * address : 农民工
             * appoint_date : 2018-08-13
             * appoint_time : 15:06:00
             * assignment : 0
             * car : 奔驰(进口)    奔驰Actros    2.0    2018
             * nickname : 徐恒
             * o_status : 0
             * phone :
             */

            private String address;
            private String appoint_date;
            private String appoint_time;
            private int assignment;
            private String car;
            private String nickname;
            private String o_status;
            private String phone;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAppoint_date() {
                return appoint_date;
            }

            public void setAppoint_date(String appoint_date) {
                this.appoint_date = appoint_date;
            }

            public String getAppoint_time() {
                return appoint_time;
            }

            public void setAppoint_time(String appoint_time) {
                this.appoint_time = appoint_time;
            }

            public int getAssignment() {
                return assignment;
            }

            public void setAssignment(int assignment) {
                this.assignment = assignment;
            }

            public String getCar() {
                return car;
            }

            public void setCar(String car) {
                this.car = car;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getO_status() {
                return o_status;
            }

            public void setO_status(String o_status) {
                this.o_status = o_status;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }

        public static class OrderEntity {
            /**
             * create_time : 2018-08-13 15:06:54
             * fee : 200
             * o_status : 0
             * order_id : 2018081310199485
             * total : 369
             */

            private String create_time;
            private float fee;
            private String o_status;
            private String order_id;
            private float total;

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public float getFee() {
                return fee;
            }

            public void setFee(float fee) {
                this.fee = fee;
            }

            public String getO_status() {
                return o_status;
            }

            public void setO_status(String o_status) {
                this.o_status = o_status;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public float getTotal() {
                return total;
            }

            public void setTotal(float total) {
                this.total = total;
            }
        }

        public static class CommodityEntity {
            /**
             * c_id : 119
             * c_name :  道达尔(Total)红运6600 柴机油 15W40 SN/GF-5级 4L
             * c_num : 1
             * c_price : 119.00
             * c_thumbnail : https://shop.carsuper.cn/./Data/Commodity/Thumbnail/p_icon_1533720355_878244.jpeg
             */

            private String c_id;
            private String c_name;
            private String c_num;
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

            public String getC_num() {
                return c_num;
            }

            public void setC_num(String c_num) {
                this.c_num = c_num;
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

        public static class Station{
           /* "station": {
                    "s_id": "199",
                    "s_latitude": "31.243313",
                    "s_longitude": "115.424311",
                    "s_name": "木子店服务区（北）",
                    "level_score":"3"
                    "s_address": "湖北省黄冈市麻城市木子店服务区(沪蓉高速公路北)",
                    "pic1": "https://shop.carsuper.cn5b704280ab7fd.jpg"*/

            private String s_id;
           // private String s_latitude;
          //  private String s_longitude;
            private String level_score;
            private String s_name;
            private String s_address;
            private String pic1;

            public String getS_id() {
                return s_id;
            }

            public void setS_id(String s_id) {
                this.s_id = s_id;
            }

            public String getLevel_score() {
                return level_score;
            }

            public void setLevel_score(String level_score) {
                this.level_score = level_score;
            }

            public String getS_name() {
                return s_name;
            }

            public void setS_name(String s_name) {
                this.s_name = s_name;
            }

            public String getS_address() {
                return s_address;
            }

            public void setS_address(String s_address) {
                this.s_address = s_address;
            }

            public String getPic1() {
                return pic1;
            }

            public void setPic1(String pic1) {
                this.pic1 = pic1;
            }
        }

    }


}
