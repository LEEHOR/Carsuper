package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/12/29
 * on 18:21
 */
public class MyDataAdList {

    /**
     * code : 0
     * msg : success
     * jdata : [{"id":"26","adv_title":"强强联手 直惠车友2019","adv_img":"https://shop.carsuper.cn/Data/Advertise/201812291751548499.png","adv_url":"cid=165&type=commodity","add_time":"1546077114","adv_sort_id":"6","position_id":"6"},{"id":"18","adv_title":"强强联手 直惠车友","adv_img":"https://shop.carsuper.cn/Data/Advertise/201812141603263302.png","adv_url":"http://shop.ksuper.coahr.com/home/index/share","add_time":"1544774606","adv_sort_id":"6","position_id":"6"}]
     */

    private int code;
    private String msg;
    private List<JdataBean> jdata;

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

    public List<JdataBean> getJdata() {
        return jdata;
    }

    public void setJdata(List<JdataBean> jdata) {
        this.jdata = jdata;
    }

    public static class JdataBean {
        /**
         * id : 26
         * adv_title : 强强联手 直惠车友2019
         * adv_img : https://shop.carsuper.cn/Data/Advertise/201812291751548499.png
         * adv_url : cid=165&type=commodity
         * add_time : 1546077114
         * adv_sort_id : 6
         * position_id : 6
         */

        private String id;
        private String adv_title;
        private String adv_img;
        private String adv_url;
        private String add_time;
        private String adv_sort_id;
        private String position_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdv_title() {
            return adv_title;
        }

        public void setAdv_title(String adv_title) {
            this.adv_title = adv_title;
        }

        public String getAdv_img() {
            return adv_img;
        }

        public void setAdv_img(String adv_img) {
            this.adv_img = adv_img;
        }

        public String getAdv_url() {
            return adv_url;
        }

        public void setAdv_url(String adv_url) {
            this.adv_url = adv_url;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAdv_sort_id() {
            return adv_sort_id;
        }

        public void setAdv_sort_id(String adv_sort_id) {
            this.adv_sort_id = adv_sort_id;
        }

        public String getPosition_id() {
            return position_id;
        }

        public void setPosition_id(String position_id) {
            this.position_id = position_id;
        }
    }
}
