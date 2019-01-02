package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/8/24.
 * Email：hengzwdhengzwd@qq.com
 */
public  class LogisticsEntity {
    /**
     * ftime : 2018-06-29 19:47:00
     * message : [武汉市]已签收(丰巢签收),感谢使用顺丰,期待再次为您服务
     */

    private String ftime;
    private String message;

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
