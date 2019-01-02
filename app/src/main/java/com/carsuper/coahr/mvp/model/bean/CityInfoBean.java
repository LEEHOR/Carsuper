package com.carsuper.coahr.mvp.model.bean;

import android.os.TokenWatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author： hengzwd on 2018/7/18.
 * Email：hengzwdhengzwd@qq.com
 */
public class CityInfoBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"city_hot":[{"c_id":"3","c_name":"武汉","c_code":"W"},{"c_id":"7","c_name":"宜昌","c_code":"Y"},{"c_id":"9","c_name":"贵阳","c_code":"G"},{"c_id":"13","c_name":"黄冈","c_code":"H"},{"c_id":"14","c_name":"恩施","c_code":"E"},{"c_id":"20","c_name":"重庆","c_code":"C"},{"c_id":"27","c_name":"昆明","c_code":"K"}],"city_list":[{"c_code":"A","city":[{"c_id":"22","c_name":"安顺","c_code":"A"}]},{"c_code":"B","city":[{"c_id":"1","c_name":"北京","c_code":"B"},{"c_id":"25","c_name":"毕节","c_code":"B"}]},{"c_code":"C","city":[{"c_id":"20","c_name":"重庆","c_code":"C"}]},{"c_code":"E","city":[{"c_id":"14","c_name":"恩施","c_code":"E"}]},{"c_code":"F","city":[{"c_id":"17","c_name":"佛山","c_code":"F"}]},{"c_code":"G","city":[{"c_id":"9","c_name":"贵阳","c_code":"G"},{"c_id":"10","c_name":"赣州","c_code":"G"},{"c_id":"11","c_name":"贵州","c_code":"G"}]},{"c_code":"H","city":[{"c_id":"13","c_name":"黄冈","c_code":"H"}]},{"c_code":"K","city":[{"c_id":"27","c_name":"昆明","c_code":"K"}]},{"c_code":"L","city":[{"c_id":"24","c_name":"六盘水","c_code":"L"}]},{"c_code":"Q","city":[{"c_id":"28","c_name":"曲靖","c_code":"Q"},{"c_id":"26","c_name":"黔东南苗族侗族自治州","c_code":"Q"},{"c_id":"23","c_name":"黔南布依族苗族自治州","c_code":"Q"},{"c_id":"30","c_name":"黔西南布依苗族自治州","c_code":"Q"}]},{"c_code":"R","city":[{"c_id":"15","c_name":"瑞金","c_code":"R"}]},{"c_code":"S","city":[{"c_id":"5","c_name":"随州","c_code":"S"},{"c_id":"2","c_name":"上海","c_code":"S"}]},{"c_code":"W","city":[{"c_id":"3","c_name":"武汉","c_code":"W"}]},{"c_code":"X","city":[{"c_id":"6","c_name":"咸宁","c_code":"X"},{"c_id":"16","c_name":"寻乌","c_code":"X"},{"c_id":"29","c_name":"孝感","c_code":"X"}]},{"c_code":"Y","city":[{"c_id":"7","c_name":"宜昌","c_code":"Y"}]},{"c_code":"Z","city":[{"c_id":"18","c_name":"中山","c_code":"Z"},{"c_id":"21","c_name":"遵义","c_code":"Z"},{"c_id":"19","c_name":"珠海","c_code":"Z"}]}]}
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
        private List<CityListEntity.CityEntity> city_hot;
        private List<CityListEntity> city_list;

        public List<CityListEntity.CityEntity> getCity_hot() {
            return city_hot;
        }

        public void setCity_hot(List<CityListEntity.CityEntity> city_hot) {
            this.city_hot = city_hot;
        }

        public List<CityListEntity> getCity_list() {
            return city_list;
        }

        public void setCity_list(List<CityListEntity> city_list) {
            this.city_list = city_list;
        }

        public List<CityListEntity.CityEntity> getSortEntity() {
            Collections.sort(city_list, new Comparator<CityListEntity>() {
                @Override
                public int compare(CityListEntity o1, CityListEntity o2) {
                    return o1.getC_code().compareTo(o2.getC_code());

                }
            });

            List<CityListEntity.CityEntity> cityEntities = new ArrayList<CityListEntity.CityEntity>();
            for (CityListEntity entity : city_list) {
                cityEntities.addAll(entity.getCity());
            }
            return cityEntities;
        }
        public static class CityListEntity {
            /**
             * c_code : A
             * city : [{"c_id":"22","c_name":"安顺","c_code":"A"}]
             */

            private String c_code;
            private List<CityEntity> city;

            public String getC_code() {
                return c_code;
            }

            public void setC_code(String c_code) {
                this.c_code = c_code;
            }

            public List<CityEntity> getCity() {
                return city;
            }

            public void setCity(List<CityEntity> city) {
                this.city = city;
            }

            public static class CityEntity {
                /**
                 * c_id : 22
                 * c_name : 安顺
                 * c_code : A
                 */

                public CityEntity(String c_id, String c_name, String c_code) {
                    this.c_id = c_id;
                    this.c_name = c_name;
                    this.c_code = c_code;
                }

                private String c_id;
                private String c_name;
                private String c_code;

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

                public String getC_code() {
                    return c_code;
                }

                public void setC_code(String c_code) {
                    this.c_code = c_code;
                }
            }
        }
    }
}
