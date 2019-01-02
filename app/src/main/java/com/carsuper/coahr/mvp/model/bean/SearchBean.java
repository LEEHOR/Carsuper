package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/23.
 * Email：hengzwdhengzwd@qq.com
 */
public class SearchBean {


    /**
     * code : 0
     * jdata : {"commodity":[{"c_id":"67","c_name":"道达尔（Total快驰9000 全合成机油 5W30 SN/GF-5级 4L","type":"commodity"}],"result":[{"id":"67","name":"道达尔（Total快驰9000 全合成机油 5W30 SN/GF-5级 4L","type":"commodity"}],"station":[{"s_id":"111","s_name":"卡速宝会昌服务区店（北区）","type":"commodity"}]}
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
        private List<SearchEntity> commodity;
        private List<SearchEntity> result;
        private List<SearchEntity> station;

        public List<SearchEntity> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<SearchEntity> commodity) {
            this.commodity = commodity;
        }

        public List<SearchEntity> getResult() {
            return result;
        }

        public void setResult(List<SearchEntity> result) {
            this.result = result;
        }

        public List<SearchEntity> getStation() {
            return station;
        }

        public void setStation(List<SearchEntity> station) {
            this.station = station;
        }

        public static class SearchEntity {

            public SearchEntity(String id, String name, String type) {
                this.id = id;
                this.name = name;
                this.type = type;
            }

            /**
             * c_id : 67
             * c_name : 道达尔（Total快驰9000 全合成机油 5W30 SN/GF-5级 4L
             * type : commodity
             */

            private String id;
            private String name;
            private String type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

    }
}
