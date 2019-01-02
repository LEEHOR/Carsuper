package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class MaintanceOrderBean {


    /**
     * code : 0
     * jdata : {"order":[{"address":"洪明明","appoint_date":"2018-08-13","appoint_time":"16:49:00","assignment":1,"city":"武汉市","district":"江岸区","o_status":"1","order_id":"2018081355505510","province":"湖北省","s_phone":"","telephone":""},{"address":"流量监控","appoint_date":"2018-08-13","appoint_time":"16:16:00","assignment":3,"city":"武汉市","district":"江岸区","o_status":1,"order_id":"2018081310149101","province":"湖北省"},{"address":"明你几级了","appoint_date":"2018-08-13","appoint_time":"16:13:00","assignment":3,"city":"武汉市","district":"江岸区","o_status":1,"order_id":"2018081310297521","province":"湖北省"},{"address":"关键时期","appoint_date":"2018-08-13","appoint_time":"15:09:00","assignment":1,"city":"武汉市","district":"江岸区","o_status":"1","order_id":"2018081349534951","province":"湖北省","s_phone":"","telephone":""},{"address":"关键时期","appoint_date":"2018-08-13","appoint_time":"15:09:00","assignment":3,"city":"武汉市","district":"江岸区","o_status":1,"order_id":"2018081349995197","province":"湖北省"},{"address":"农民工","appoint_date":"2018-08-13","appoint_time":"15:06:00","assignment":0,"city":"武汉市","district":"江岸区","o_status":"0","order_id":"2018081310199485","province":"湖北省","s_phone":"","telephone":""},{"address":"农民工","appoint_date":"2018-08-14","appoint_time":"00:00:00","assignment":0,"city":"武汉市","district":"江岸区","o_status":"-1","order_id":"2018081352545257","province":"湖北省","s_phone":"","telephone":""},{"address":"高科技利人利己","appoint_date":"2018-08-08","appoint_time":"16:21:00","assignment":1,"city":"武汉市","district":"桥口区","o_status":"1","order_id":"2018080852101102","province":"湖北省","s_phone":"","telephone":""},{"address":"排比吃柴哈库拉","appoint_date":"2018-08-08","appoint_time":"16:21:00","assignment":1,"city":"武汉市","district":"桥口区","o_status":"1","order_id":"2018080857514951","province":"湖北省","s_phone":"","telephone":""},{"address":"排比吃柴哈库拉","appoint_date":"2018-08-08","appoint_time":"16:21:00","assignment":1,"city":"武汉市","district":"桥口区","o_status":"1","order_id":"2018080898514951","province":"湖北省","s_phone":"","telephone":""}]}
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
        private List<OrderEntity> order;

        public List<OrderEntity> getOrder() {
            return order;
        }

        public void setOrder(List<OrderEntity> order) {
            this.order = order;
        }

        public static class OrderEntity {
            /**
             * address : 洪明明
             * appoint_date : 2018-08-13
             * appoint_time : 16:49:00
             * assignment : 1
             * city : 武汉市
             * district : 江岸区
             * o_status : 1
             * order_id : 2018081355505510
             * province : 湖北省
             * s_phone :
             * telephone :
             */

            private String address;
            private String appoint_date;
            private String appoint_time;
            private int assignment;
            private String city;
            private String district;
            private String o_status;
            private String order_id;
            private String province;
            private String s_phone;
            private String telephone;

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

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
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

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getS_phone() {
                return s_phone;
            }

            public void setS_phone(String s_phone) {
                this.s_phone = s_phone;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }
        }
    }
}
