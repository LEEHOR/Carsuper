package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/26.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreBean {


    /**
     * code : 0
     * jdata : {"station":[{"distance":"56","level_score":"3.0","pic1":"https://shop.carsuper.cn/Data/Station/5b70441893f94.jpg","s_address":"湖北省孝感市福银（汉十）高速孝感服务区北区","s_id":"130","s_latitude":"31.008608","s_longitude":"114.076921","s_name":"卡速宝孝感服务区店（北区）","s_type":"0"},{"distance":"59","level_score":"0.0","pic1":"https://shop.carsuper.cn/Data/Station/5b70440be0499.jpg","s_address":"湖北省孝感市福银（汉十）高速孝感服务区南区","s_id":"131","s_latitude":"31.020065","s_longitude":"113.987746","s_name":"卡速宝孝感服务区店（南区）","s_type":"0"},{"distance":"97","level_score":"0.0","pic1":"https://shop.carsuper.cn/Data/Station/5b704274f3ff3.jpg","s_address":"湖北省黄冈市麻城市中馆驿服务区(沪蓉高速公路南)","s_id":"200","s_latitude":"31.141441","s_longitude":"114.869049","s_name":"中馆驿服务区（南）","s_type":"0"},{"distance":"97","level_score":"0.0","pic1":"https://shop.carsuper.cn/Data/Station/5b704267dd210.jpg","s_address":"湖北省黄冈市麻城市中馆驿服务区(沪蓉高速公路北) ","s_id":"201","s_latitude":"31.142509","s_longitude":"114.868299","s_name":"中馆驿服务区（北）","s_type":"0"},{"distance":"144","level_score":"0.0","pic1":"https://shop.carsuper.cn/Data/Station/5b70428b22276.jpg","s_address":"湖北省黄冈市麻城市木子店服务区(沪蓉高速公路南) ","s_id":"198","s_latitude":"31.240176","s_longitude":"115.422208","s_name":"木子店服务区（南）","s_type":"0"},{"distance":"145","level_score":"3.5","pic1":"https://shop.carsuper.cn/Data/Station/5b704280ab7fd.jpg","s_address":"湖北省黄冈市麻城市木子店服务区(沪蓉高速公路北)","s_id":"199","s_latitude":"31.243313","s_longitude":"115.424311","s_name":"木子店服务区（北）","s_type":"0"},{"distance":"175","level_score":"0.0","pic1":"https://shop.carsuper.cn/Data/Station/5b704443e403c.jpg","s_address":"湖北省随州市福银（汉十）高速随州服务区北区","s_id":"128","s_latitude":"31.805203","s_longitude":"113.128048","s_name":"卡速宝随州服务区店（北区）","s_type":"0"},{"distance":"175","level_score":"0.0","pic1":"https://shop.carsuper.cn/Data/Station/5b7044323c321.jpg","s_address":"湖北省随州市福银（汉十）高速随州服务区南区","s_id":"129","s_latitude":"31.804604","s_longitude":"113.126467","s_name":"卡速宝随州服务区店（南区）","s_type":"0"},{"distance":"295","level_score":"0.0","pic1":"https://shop.carsuper.cn/Data/Station/5b7042cf169a8.jpg","s_address":"沪渝高速","s_id":"156","s_latitude":"30.576984","s_longitude":"111.089085","s_name":"卡速宝高家堰服务区(北区)","s_type":"0"},{"distance":"296","level_score":"0.0","pic1":"https://shop.carsuper.cn/Data/Station/5b7042e212c26.jpg","s_address":"沪渝高速","s_id":"155","s_latitude":"30.576027","s_longitude":"111.088363","s_name":"卡速宝高家堰服务区（南区）","s_type":"0"}]}
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
        private List<StationEntity> station;

        public List<StationEntity> getStation() {
            return station;
        }

        public void setStation(List<StationEntity> station) {
            this.station = station;
        }

        public static class StationEntity {
            /**
             * distance : 56
             * level_score : 3.0
             * pic1 : https://shop.carsuper.cn/Data/Station/5b70441893f94.jpg
             * s_address : 湖北省孝感市福银（汉十）高速孝感服务区北区
             * s_id : 130
             * s_latitude : 31.008608
             * s_longitude : 114.076921
             * s_name : 卡速宝孝感服务区店（北区）
             * s_type : 0
             */

            private String distance;
            private String level_score;
            private String pic1;
            private String s_address;
            private String s_id;
            private String s_latitude;
            private String s_longitude;
            private String s_name;
            private String s_type;

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getLevel_score() {
                return level_score;
            }

            public void setLevel_score(String level_score) {
                this.level_score = level_score;
            }

            public String getPic1() {
                return pic1;
            }

            public void setPic1(String pic1) {
                this.pic1 = pic1;
            }

            public String getS_address() {
                return s_address;
            }

            public void setS_address(String s_address) {
                this.s_address = s_address;
            }

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

            public String getS_type() {
                return s_type;
            }

            public void setS_type(String s_type) {
                this.s_type = s_type;
            }
        }
    }
}
