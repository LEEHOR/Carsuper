package com.carsuper.coahr.mvp.model.bean;

/**
 * Author： hengzwd on 2018/9/3.
 * Email：hengzwdhengzwd@qq.com
 */
public class ServiceOrderCopyBean {


    /**
     * code : 0
     * msg : success
     * jdata : {"order":{"car_id":"632","service_type":null,"province":"湖北省","city":"武汉市","district":null,"telephone":"18571512117","address":"毕竟你","longitude":"114.170299","latitude":"30.512182"},"commodity":{"commodity":{"c_id":"159","c_name":"道达尔红运7900机油15W40 CJ-4 18L","c_sort":"82","c_price":"468.99","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1539761057_423245.jpeg","num":"1"},"commodity_filter":{"c_id":"160","c_name":"弗列加LF9009机油滤清器","c_sort":"84","c_price":"124.99","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1539765279_945315.jpeg","num":"1"}}}
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
         * order : {"car_id":"632","service_type":null,"province":"湖北省","city":"武汉市","district":null,"telephone":"18571512117","address":"毕竟你","longitude":"114.170299","latitude":"30.512182"}
         * commodity : {"commodity":{"c_id":"159","c_name":"道达尔红运7900机油15W40 CJ-4 18L","c_sort":"82","c_price":"468.99","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1539761057_423245.jpeg","num":"1"},"commodity_filter":{"c_id":"160","c_name":"弗列加LF9009机油滤清器","c_sort":"84","c_price":"124.99","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1539765279_945315.jpeg","num":"1"}}
         */

        private OrderBean order;
        private CommodityBeanX commodity;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public CommodityBeanX getCommodity() {
            return commodity;
        }

        public void setCommodity(CommodityBeanX commodity) {
            this.commodity = commodity;
        }

        public static class OrderBean {
            /**
             * car_id : 632
             * service_type : null
             * province : 湖北省
             * city : 武汉市
             * district : null
             * telephone : 18571512117
             * address : 毕竟你
             * longitude : 114.170299
             * latitude : 30.512182
             */

            private String car_id;
            private Object service_type;
            private String province;
            private String city;
            private Object district;
            private String telephone;
            private String address;
            private String longitude;
            private String latitude;

            public String getCar_id() {
                return car_id;
            }

            public void setCar_id(String car_id) {
                this.car_id = car_id;
            }

            public Object getService_type() {
                return service_type;
            }

            public void setService_type(Object service_type) {
                this.service_type = service_type;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public Object getDistrict() {
                return district;
            }

            public void setDistrict(Object district) {
                this.district = district;
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

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }
        }

        public static class CommodityBeanX {
            /**
             * commodity : {"c_id":"159","c_name":"道达尔红运7900机油15W40 CJ-4 18L","c_sort":"82","c_price":"468.99","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1539761057_423245.jpeg","num":"1"}
             * commodity_filter : {"c_id":"160","c_name":"弗列加LF9009机油滤清器","c_sort":"84","c_price":"124.99","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1539765279_945315.jpeg","num":"1"}
             */

            private CommodityBean commodity;
            private CommodityFilterBean commodity_filter;

            public CommodityBean getCommodity() {
                return commodity;
            }

            public void setCommodity(CommodityBean commodity) {
                this.commodity = commodity;
            }

            public CommodityFilterBean getCommodity_filter() {
                return commodity_filter;
            }

            public void setCommodity_filter(CommodityFilterBean commodity_filter) {
                this.commodity_filter = commodity_filter;
            }

            public static class CommodityBean {
                /**
                 * c_id : 159
                 * c_name : 道达尔红运7900机油15W40 CJ-4 18L
                 * c_sort : 82
                 * c_price : 468.99
                 * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1539761057_423245.jpeg
                 * num : 1
                 */

                private String c_id;
                private String c_name;
                private String c_sort;
                private String c_price;
                private String c_thumbnail;
                private String num;

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

                public String getC_sort() {
                    return c_sort;
                }

                public void setC_sort(String c_sort) {
                    this.c_sort = c_sort;
                }

                public String getC_price() {
                    return c_price;
                }

                public void setC_price(String c_price) {
                    this.c_price = c_price;
                }

                public String getC_thumbnail() {
                    return c_thumbnail;
                }

                public void setC_thumbnail(String c_thumbnail) {
                    this.c_thumbnail = c_thumbnail;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }

            public static class CommodityFilterBean {
                /**
                 * c_id : 160
                 * c_name : 弗列加LF9009机油滤清器
                 * c_sort : 84
                 * c_price : 124.99
                 * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/p_icon_1539765279_945315.jpeg
                 * num : 1
                 */

                private String c_id;
                private String c_name;
                private String c_sort;
                private String c_price;
                private String c_thumbnail;
                private String num;

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

                public String getC_sort() {
                    return c_sort;
                }

                public void setC_sort(String c_sort) {
                    this.c_sort = c_sort;
                }

                public String getC_price() {
                    return c_price;
                }

                public void setC_price(String c_price) {
                    this.c_price = c_price;
                }

                public String getC_thumbnail() {
                    return c_thumbnail;
                }

                public void setC_thumbnail(String c_thumbnail) {
                    this.c_thumbnail = c_thumbnail;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }
        }
    }
}
