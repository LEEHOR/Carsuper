package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/17.
 * Email：hengzwdhengzwd@qq.com
 */
public class ConfirmCommodityOrderBean {


    /**
     * code : 0
     * jdata : {"commodity":[{"c_id":"94","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b6413c3af5e5.png"}],"fee":0,"total":94,"userinfo":{"address":"山西省 太原市 市辖区111","telephone":"13554050555","ua_id":"775","username":"hd"}}
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
         * commodity : [{"c_id":"94","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b6413c3af5e5.png"}]
         * fee : 0
         * total : 94
         * userinfo : {"address":"山西省 太原市 市辖区111","telephone":"13554050555","ua_id":"775","username":"hd"}
         */

        private float fee;
        private float total;
        private UserinfoEntity userinfo;
        private List<CommodityEntity> commodity;

        public float getFee() {
            return fee;
        }

        public void setFee(float fee) {
            this.fee = fee;
        }

        public float getTotal() {
            return total;
        }

        public void setTotal(float total) {
            this.total = total;
        }

        public UserinfoEntity getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoEntity userinfo) {
            this.userinfo = userinfo;
        }

        public List<CommodityEntity> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<CommodityEntity> commodity) {
            this.commodity = commodity;
        }

        public static class UserinfoEntity {
            /**
             * address : 山西省 太原市 市辖区111
             * telephone : 13554050555
             * ua_id : 775
             * username : hd
             */

            private String address;
            private String telephone;
            private String ua_id;
            private String username;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getUa_id() {
                return ua_id;
            }

            public void setUa_id(String ua_id) {
                this.ua_id = ua_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public static class CommodityEntity {
            /**
             * c_id : 94
             * c_name : 道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L
             * c_price : 329.00
             * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180803/5b6413c3af5e5.png
             */

            private String c_id;
            private String c_name;
            private String c_price;
            private String c_thumbnail;
            private String c_num;

            public String getC_num() {
                return c_num;
            }
            public void setC_num(String c_num) {
                this.c_num = c_num;
            }
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
    }
}
