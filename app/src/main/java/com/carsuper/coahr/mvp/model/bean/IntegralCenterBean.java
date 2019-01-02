package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/24.
 * Email：hengzwdhengzwd@qq.com
 */
public class IntegralCenterBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"points":"2","list":[{"point":"+2","type":"1","description":"签到成功","addtime":"2018-08-24 15:09:47"}]}
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
        /**
         * points : 2
         * list : [{"point":"+2","type":"1","description":"签到成功","addtime":"2018-08-24 15:09:47"}]
         */

        private String points;
        private List<ListEntity> list;

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity {
            /**
             * point : +2
             * type : 1
             * description : 签到成功
             * addtime : 2018-08-24 15:09:47
             */

            private String point;
            private String type;
            private String description;
            private String addtime;

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }
    }
}
