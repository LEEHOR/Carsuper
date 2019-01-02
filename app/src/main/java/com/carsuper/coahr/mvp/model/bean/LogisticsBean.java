package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/23.
 * Email：hengzwdhengzwd@qq.com
 */
public class LogisticsBean {


    /**
     * code : 0
     * jdata : {"address":{"address":"后悔了5名哦","telephone":"1524245455454","username":"哈哈"},"logistics":[{"ftime":"2018-06-29 19:47:00","message":"[武汉市]已签收(丰巢签收),感谢使用顺丰,期待再次为您服务"},{"ftime":"2018-06-29 18:03:00","message":"[武汉市]快件派送至【丰巢智能快递柜】,请您凭取件码至怡康苑北区301栋一单元右侧架空层丰巢快递柜领取您的快递."},{"ftime":"2018-06-29 17:47:00","message":"[武汉市]快件交给肖振盍，正在派送途中（联系电话：18671247306）"},{"ftime":"2018-06-29 16:49:00","message":"[武汉市]正在派送途中,请您准备签收(派件人:袁朝军,电话:18696160856)"},{"ftime":"2018-06-29 16:47:00","message":"[武汉市]快件到达 【武汉江岸区后湖营业点】"},{"ftime":"2018-06-29 15:32:00","message":"[武汉市]快件已发车"},{"ftime":"2018-06-29 15:04:00","message":"[武汉市]快件在【武汉吴家山集散中心】已装车,准备发往下一站"},{"ftime":"2018-06-29 13:29:00","message":"[武汉市]快件到达 【武汉吴家山集散中心】"},{"ftime":"2018-06-29 00:56:00","message":"[上海市]快件已发车"},{"ftime":"2018-06-28 23:02:00","message":"[上海市]快件在【上海华新集散中心】已装车,准备发往下一站"},{"ftime":"2018-06-28 21:31:00","message":"[上海市]快件到达 【上海华新集散中心】"},{"ftime":"2018-06-28 20:08:00","message":"[上海市]快件已发车"},{"ftime":"2018-06-28 19:59:00","message":"[上海市]快件在【上海闸北彭浦营业点】已装车,准备发往下一站"},{"ftime":"2018-06-28 18:50:00","message":"[上海市]顺丰速运 已收取快件"},{"ftime":"2018-06-28 18:46:00","message":"[上海市]顺丰速运 已收取快件"}],"logistics_company":{"company":"顺丰快递","logo":"https://shop.carsuper.cn/Data/Ligistics/5b1f8bf347573.png","phone":"\t95338"}}
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
         * address : {"address":"后悔了5名哦","telephone":"1524245455454","username":"哈哈"}
         * logistics : [{"ftime":"2018-06-29 19:47:00","message":"[武汉市]已签收(丰巢签收),感谢使用顺丰,期待再次为您服务"},{"ftime":"2018-06-29 18:03:00","message":"[武汉市]快件派送至【丰巢智能快递柜】,请您凭取件码至怡康苑北区301栋一单元右侧架空层丰巢快递柜领取您的快递."},{"ftime":"2018-06-29 17:47:00","message":"[武汉市]快件交给肖振盍，正在派送途中（联系电话：18671247306）"},{"ftime":"2018-06-29 16:49:00","message":"[武汉市]正在派送途中,请您准备签收(派件人:袁朝军,电话:18696160856)"},{"ftime":"2018-06-29 16:47:00","message":"[武汉市]快件到达 【武汉江岸区后湖营业点】"},{"ftime":"2018-06-29 15:32:00","message":"[武汉市]快件已发车"},{"ftime":"2018-06-29 15:04:00","message":"[武汉市]快件在【武汉吴家山集散中心】已装车,准备发往下一站"},{"ftime":"2018-06-29 13:29:00","message":"[武汉市]快件到达 【武汉吴家山集散中心】"},{"ftime":"2018-06-29 00:56:00","message":"[上海市]快件已发车"},{"ftime":"2018-06-28 23:02:00","message":"[上海市]快件在【上海华新集散中心】已装车,准备发往下一站"},{"ftime":"2018-06-28 21:31:00","message":"[上海市]快件到达 【上海华新集散中心】"},{"ftime":"2018-06-28 20:08:00","message":"[上海市]快件已发车"},{"ftime":"2018-06-28 19:59:00","message":"[上海市]快件在【上海闸北彭浦营业点】已装车,准备发往下一站"},{"ftime":"2018-06-28 18:50:00","message":"[上海市]顺丰速运 已收取快件"},{"ftime":"2018-06-28 18:46:00","message":"[上海市]顺丰速运 已收取快件"}]
         * logistics_company : {"company":"顺丰快递","logo":"https://shop.carsuper.cn/Data/Ligistics/5b1f8bf347573.png","phone":"\t95338"}
         */

        private AddressEntity address;
        private LogisticsCompanyEntity logistics_company;
        private List<LogisticsEntity> logistics;

        public AddressEntity getAddress() {
            return address;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public LogisticsCompanyEntity getLogistics_company() {
            return logistics_company;
        }

        public void setLogistics_company(LogisticsCompanyEntity logistics_company) {
            this.logistics_company = logistics_company;
        }

        public List<LogisticsEntity> getLogistics() {
            return logistics;
        }

        public void setLogistics(List<LogisticsEntity> logistics) {
            this.logistics = logistics;
        }

        public static class AddressEntity {
            /**
             * address : 后悔了5名哦
             * telephone : 1524245455454
             * username : 哈哈
             */

            private String address;
            private String telephone;
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public static class LogisticsCompanyEntity {
            /**
             * company : 顺丰快递
             * logo : https://shop.carsuper.cn/Data/Ligistics/5b1f8bf347573.png
             * phone : 	95338
             */

            private String company;
            private String logo;
            private String phone;

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }

    }
}
