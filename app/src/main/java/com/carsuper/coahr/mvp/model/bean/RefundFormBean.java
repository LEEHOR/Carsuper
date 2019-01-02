package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/8/28.
 * Email：hengzwdhengzwd@qq.com
 */
public class RefundFormBean {


    /**
     * code : 0
     * jdata : {"address":{"address":"后悔了5名哦","id":"777","telephone":"1524245455454","username":"哈哈"},"commodity":{"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_num":"1","c_price":"580.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png","o_id":"2018082118154533914378"},"refund":"580.00","refund_type":"3"}
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
         * address : {"address":"后悔了5名哦","id":"777","telephone":"1524245455454","username":"哈哈"}
         * commodity : {"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_num":"1","c_price":"580.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png","o_id":"2018082118154533914378"}
         * refund : 580.00
         * refund_type : 3
         */

        private AddressEntity address;
        private CommodityEntity commodity;
        private String refund;
        private String refund_type;

        public AddressEntity getAddress() {
            return address;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public CommodityEntity getCommodity() {
            return commodity;
        }

        public void setCommodity(CommodityEntity commodity) {
            this.commodity = commodity;
        }

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }

        public String getRefund_type() {
            return refund_type;
        }

        public void setRefund_type(String refund_type) {
            this.refund_type = refund_type;
        }

        public static class AddressEntity {
            /**
             * address : 后悔了5名哦
             * id : 777
             * telephone : 1524245455454
             * username : 哈哈
             */

            private String address;
            private String id;
            private String telephone;
            private String username;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
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
             * c_id : 76
             * c_name : 米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦
             * c_num : 1
             * c_price : 580.00
             * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png
             * o_id : 2018082118154533914378
             */

            private String c_id;
            private String c_name;
            private String c_num;
            private String c_price;
            private String c_thumbnail;
            private String o_id;

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

            public String getO_id() {
                return o_id;
            }

            public void setO_id(String o_id) {
                this.o_id = o_id;
            }
        }
    }
}
