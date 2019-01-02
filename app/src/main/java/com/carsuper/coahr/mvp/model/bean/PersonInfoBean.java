package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/7/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class PersonInfoBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"user":{"uid":"1152","wxid":"oP0R81ZoRuMrYwwRFoRNV89cOq7g","appopenid":null,"openid":"oESiI1N_gAkROB6-h8HaJd9-jVco","nickname":"李浩","phone":"18571512117","addtime":"1540802814","update":null,"password":null,"sinaid":null,"status":"1","identity_flag":null,"licence_pic":null,"station_id":null,"verify_flag":null,"service_flag":null,"userheadimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132","points":"8","bonus_status":1},"order_count":{"status_a":"15","status_b":"1","status_c":"1","status_d":"0","status_e":"0"}}
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
         * user : {"uid":"1152","wxid":"oP0R81ZoRuMrYwwRFoRNV89cOq7g","appopenid":null,"openid":"oESiI1N_gAkROB6-h8HaJd9-jVco","nickname":"李浩","phone":"18571512117","addtime":"1540802814","update":null,"password":null,"sinaid":null,"status":"1","identity_flag":null,"licence_pic":null,"station_id":null,"verify_flag":null,"service_flag":null,"userheadimg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132","points":"8","bonus_status":1}
         * order_count : {"status_a":"15","status_b":"1","status_c":"1","status_d":"0","status_e":"0"}
         */

        private UserBean user;
        private OrderCountBean order_count;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public OrderCountBean getOrder_count() {
            return order_count;
        }

        public void setOrder_count(OrderCountBean order_count) {
            this.order_count = order_count;
        }

        public static class UserBean {
            /**
             * uid : 1152
             * wxid : oP0R81ZoRuMrYwwRFoRNV89cOq7g
             * appopenid : null
             * openid : oESiI1N_gAkROB6-h8HaJd9-jVco
             * nickname : 李浩
             * phone : 18571512117
             * addtime : 1540802814
             * update : null
             * password : null
             * sinaid : null
             * status : 1
             * identity_flag : null
             * licence_pic : null
             * station_id : null
             * verify_flag : null
             * service_flag : null
             * userheadimg : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJavOtdnicKTHYhCRX1rzySrsQCKe5Tbs3f0opK9637u6AsbTro3pv28lbMicFH4MDOHytRdf1LocMA/132
             * points : 8
             * bonus_status : 1
             */

            private String uid;
            private String wxid;
            private Object appopenid;
            private String openid;
            private String nickname;
            private String phone;
            private String addtime;
            private Object update;
            private Object password;
            private Object sinaid;
            private String status;
            private Object identity_flag;
            private Object licence_pic;
            private Object station_id;
            private Object verify_flag;
            private Object service_flag;
            private String userheadimg;
            private String points;
            private int bonus_status;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getWxid() {
                return wxid;
            }

            public void setWxid(String wxid) {
                this.wxid = wxid;
            }

            public Object getAppopenid() {
                return appopenid;
            }

            public void setAppopenid(Object appopenid) {
                this.appopenid = appopenid;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public Object getUpdate() {
                return update;
            }

            public void setUpdate(Object update) {
                this.update = update;
            }

            public Object getPassword() {
                return password;
            }

            public void setPassword(Object password) {
                this.password = password;
            }

            public Object getSinaid() {
                return sinaid;
            }

            public void setSinaid(Object sinaid) {
                this.sinaid = sinaid;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getIdentity_flag() {
                return identity_flag;
            }

            public void setIdentity_flag(Object identity_flag) {
                this.identity_flag = identity_flag;
            }

            public Object getLicence_pic() {
                return licence_pic;
            }

            public void setLicence_pic(Object licence_pic) {
                this.licence_pic = licence_pic;
            }

            public Object getStation_id() {
                return station_id;
            }

            public void setStation_id(Object station_id) {
                this.station_id = station_id;
            }

            public Object getVerify_flag() {
                return verify_flag;
            }

            public void setVerify_flag(Object verify_flag) {
                this.verify_flag = verify_flag;
            }

            public Object getService_flag() {
                return service_flag;
            }

            public void setService_flag(Object service_flag) {
                this.service_flag = service_flag;
            }

            public String getUserheadimg() {
                return userheadimg;
            }

            public void setUserheadimg(String userheadimg) {
                this.userheadimg = userheadimg;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public int getBonus_status() {
                return bonus_status;
            }

            public void setBonus_status(int bonus_status) {
                this.bonus_status = bonus_status;
            }
        }

        public static class OrderCountBean {
            /**
             * status_a : 15
             * status_b : 1
             * status_c : 1
             * status_d : 0
             * status_e : 0
             */

            private String status_a;
            private String status_b;
            private String status_c;
            private String status_d;
            private String status_e;

            public String getStatus_a() {
                return status_a;
            }

            public void setStatus_a(String status_a) {
                this.status_a = status_a;
            }

            public String getStatus_b() {
                return status_b;
            }

            public void setStatus_b(String status_b) {
                this.status_b = status_b;
            }

            public String getStatus_c() {
                return status_c;
            }

            public void setStatus_c(String status_c) {
                this.status_c = status_c;
            }

            public String getStatus_d() {
                return status_d;
            }

            public void setStatus_d(String status_d) {
                this.status_d = status_d;
            }

            public String getStatus_e() {
                return status_e;
            }

            public void setStatus_e(String status_e) {
                this.status_e = status_e;
            }
        }
    }
}
