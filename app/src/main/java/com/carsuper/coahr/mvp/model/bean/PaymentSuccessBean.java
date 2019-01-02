package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/9/11.
 * Email：hengzwdhengzwd@qq.com
 */
public class PaymentSuccessBean {


    /**
     * code : 0
     * jdata : {"commodity":[{"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_price":"579.99","c_sold_real":"9","c_thumbnail":"/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png"},{"c_id":"139","c_name":"测试使用，请勿购买（轮胎无货）","c_price":"0.01","c_sold_real":"0","c_thumbnail":"/Data/Commodity/Thumbnail/20180905/5b8f38b45274e.png"}]}
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
        private List<RecommendEntity> commodity;

        public List<RecommendEntity> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<RecommendEntity> commodity) {
            this.commodity = commodity;
        }

    }
}
