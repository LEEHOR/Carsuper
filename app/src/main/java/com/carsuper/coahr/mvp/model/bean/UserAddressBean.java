package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/15.
 * Email：hengzwdhengzwd@qq.com
 */
public class UserAddressBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"address":[{"id":"562","username":"引吭高歌","telephone":"15722620367","address":"天津 天津市 河东区共和国非常纯纯粹粹 v 哈哈哈","selected":"1"},{"id":"563","username":"购物图","telephone":"18186523476","address":"天津 天津市 和平区酷酷兔通知我","selected":"1"},{"id":"564","username":"木林森","telephone":"15722620367","address":"辽宁省 沈阳市 市辖区木林森","selected":"1"},{"id":"565","username":"龚必健","telephone":"13554060508","address":"湖北省 武汉市 江岸区百步亭花园怡康苑北区","selected":"1"},{"id":"566","username":"杨俊","telephone":"13628638765","address":"湖北省 武汉市 汉阳区东合中心B905","selected":"1"},{"id":"567","username":"凡","telephone":"18571614506","address":"湖北省 武汉市 蔡甸区东合","selected":"0"},{"id":"699","username":"v哈哈","telephone":"15668887756","address":"河北省 唐山市 路南区股海护航","selected":"1"},{"id":"770","username":"欧几","telephone":"14235345353","address":"吉林省 四平市 市辖区1234","selected":"1"},{"id":"771","username":"公共","telephone":"15666487979","address":"黑龙江省 鸡西市 市辖区4757","selected":"0"},{"id":"773","username":"张三","telephone":"13333333333","address":"北京 北京市 东城区11","selected":"1"},{"id":"774","username":"龚","telephone":"13554050555","address":"山西省 太原市 市辖区111","selected":"1"},{"id":"769","username":"hd","telephone":"15245355252","address":"辽宁省 鞍山市 市辖区4212","selected":"1"},{"id":"772","username":"龚","telephone":"13554060555","address":"吉林省 四平市 市辖区1234","selected":"1"}]}
     */

    private int code;
    private String msg;
    private JdataEntity jdata;

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

    public JdataEntity getJdata() {
        return jdata;
    }

    public void setJdata(JdataEntity jdata) {
        this.jdata = jdata;
    }

    public static class JdataEntity {
        private List<AddressEntity> address;

        public List<AddressEntity> getAddress() {
            return address;
        }

        public void setAddress(List<AddressEntity> address) {
            this.address = address;
        }

        public static class AddressEntity {
            /**
             * id : 562
             * username : 引吭高歌
             * telephone : 15722620367
             * address : 天津 天津市 河东区共和国非常纯纯粹粹 v 哈哈哈
             * selected : 1
             */

            private String id;
            private String username;
            private String telephone;
            private String address;
            private String selected;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getSelected() {
                return selected;
            }

            public void setSelected(String selected) {
                this.selected = selected;
            }
        }
    }
}
