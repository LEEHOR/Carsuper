package com.carsuper.coahr.mvp.model.bean;

/**
 * Date：2018/10/12
 * Time：9:49
 * Created by Leehor
 * 预约确认订单页面服务站
 */
public class StationRecommend {

    /**
     * code : 0
     * msg : success
     * jdata : {"station":{"s_id":"111","s_latitude":"25.857771","s_longitude":"115.85256","s_name":"卡速宝会昌服务区店（北区）","s_address":"江西省赣州市会昌县厦蓉高速会昌服务区北区","pic1":"https://shop.carsuper.cn/Data/Station/5b7044f25f338.jpg","delta":"0","distance":0},"fee_other":0}
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
         * station : {"s_id":"111","s_latitude":"25.857771","s_longitude":"115.85256","s_name":"卡速宝会昌服务区店（北区）","s_address":"江西省赣州市会昌县厦蓉高速会昌服务区北区","pic1":"https://shop.carsuper.cn/Data/Station/5b7044f25f338.jpg","delta":"0","distance":0}
         * fee_other : 0
         */

        private StationBean station;
        private String fee_other;

        public StationBean getStation() {
            return station;
        }

        public void setStation(StationBean station) {
            this.station = station;
        }

        public String getFee_other() {
            return fee_other;
        }

        public void setFee_other(String fee_other) {
            this.fee_other = fee_other;
        }

        public static class StationBean {
            /**
             * s_id : 111
             * s_latitude : 25.857771
             * s_longitude : 115.85256
             * s_name : 卡速宝会昌服务区店（北区）
             * s_address : 江西省赣州市会昌县厦蓉高速会昌服务区北区
             * pic1 : https://shop.carsuper.cn/Data/Station/5b7044f25f338.jpg
             * delta : 0
             * distance : 0
             */

            private String s_id;
            private String s_latitude;
            private String s_longitude;
            private String s_name;
            private String s_address;
            private String pic1;
            private String delta;
            private String level_score;
            private double distance;

            public String getS_id() {
                return s_id;
            }

            public void setS_id(String s_id) {
                this.s_id = s_id;
            }

            public String getS_latitude() {
                return s_latitude;
            }

            public void setS_latitude(String s_latitude) {
                this.s_latitude = s_latitude;
            }

            public String getS_longitude() {
                return s_longitude;
            }

            public void setS_longitude(String s_longitude) {
                this.s_longitude = s_longitude;
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

            public String getDelta() {
                return delta;
            }

            public void setDelta(String delta) {
                this.delta = delta;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public String getLevel_score() {
                return level_score;
            }

            public void setLevel_score(String level_score) {
                this.level_score = level_score;
            }
        }
    }
}
