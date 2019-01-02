package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/12/29
 * on 10:11
 */
public class ExchangeDetail {

    /**
     * code : 0
     * msg : success
     * jdata : {"commodity":{"c_id":"150","c_name":"打火机","c_thumbnail":"https://shop.carsuper.cn/Data/BonusCommodity/Thumbnail/p_icon_1545978827_578344.jpeg","c_price":"50.00","cp_path":["https://shop.carsuper.cn/Data/BonusCommodity/Detail/20181228/5c25c3d24b4d6.jpg","https://shop.carsuper.cn/Data/BonusCommodity/Detail/20181228/5c25c3d25e59e.jpg"],"o_status":null},"commodity_description":{"text":"<p><img src=\"https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978840111237.jpg\" title=\"1545978840111237.jpg\" alt=\"timg (2).jpg\"/><img src=\"https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978842111297.jpg\" title=\"1545978842111297.jpg\" alt=\"u=2555484987,1876385797&amp;fm=26&amp;gp=0.jpg\"/><\/p>","des_pic":[{"pic":"https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978840111237.jpg","width":750,"height":517},{"pic":"https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978842111297.jpg","width":750,"height":1071}]},"obj":[]}
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
         * commodity : {"c_id":"150","c_name":"打火机","c_thumbnail":"https://shop.carsuper.cn/Data/BonusCommodity/Thumbnail/p_icon_1545978827_578344.jpeg","c_price":"50.00","cp_path":["https://shop.carsuper.cn/Data/BonusCommodity/Detail/20181228/5c25c3d24b4d6.jpg","https://shop.carsuper.cn/Data/BonusCommodity/Detail/20181228/5c25c3d25e59e.jpg"],"o_status":null}
         * commodity_description : {"text":"<p><img src=\"https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978840111237.jpg\" title=\"1545978840111237.jpg\" alt=\"timg (2).jpg\"/><img src=\"https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978842111297.jpg\" title=\"1545978842111297.jpg\" alt=\"u=2555484987,1876385797&amp;fm=26&amp;gp=0.jpg\"/><\/p>","des_pic":[{"pic":"https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978840111237.jpg","width":750,"height":517},{"pic":"https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978842111297.jpg","width":750,"height":1071}]}
         * obj : []
         */

        private CommodityBean commodity;
        private CommodityDescriptionBean commodity_description;
        private List<?> obj;

        public CommodityBean getCommodity() {
            return commodity;
        }

        public void setCommodity(CommodityBean commodity) {
            this.commodity = commodity;
        }

        public CommodityDescriptionBean getCommodity_description() {
            return commodity_description;
        }

        public void setCommodity_description(CommodityDescriptionBean commodity_description) {
            this.commodity_description = commodity_description;
        }

        public List<?> getObj() {
            return obj;
        }

        public void setObj(List<?> obj) {
            this.obj = obj;
        }

        public static class CommodityBean {
            /**
             * c_id : 150
             * c_name : 打火机
             * c_thumbnail : https://shop.carsuper.cn/Data/BonusCommodity/Thumbnail/p_icon_1545978827_578344.jpeg
             * c_price : 50.00
             * cp_path : ["https://shop.carsuper.cn/Data/BonusCommodity/Detail/20181228/5c25c3d24b4d6.jpg","https://shop.carsuper.cn/Data/BonusCommodity/Detail/20181228/5c25c3d25e59e.jpg"]
             * o_status : null
             */

            private String c_id;
            private String c_name;
            private String c_thumbnail;
            private String c_price;
            private String o_status;
            private List<String> cp_path;

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

            public String getC_thumbnail() {
                return c_thumbnail;
            }

            public void setC_thumbnail(String c_thumbnail) {
                this.c_thumbnail = c_thumbnail;
            }

            public String getC_price() {
                return c_price;
            }

            public void setC_price(String c_price) {
                this.c_price = c_price;
            }

            public String getO_status() {
                return o_status;
            }

            public void setO_status(String o_status) {
                this.o_status = o_status;
            }

            public List<String> getCp_path() {
                return cp_path;
            }

            public void setCp_path(List<String> cp_path) {
                this.cp_path = cp_path;
            }
        }

        public static class CommodityDescriptionBean {
            /**
             * text : <p><img src="https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978840111237.jpg" title="1545978840111237.jpg" alt="timg (2).jpg"/><img src="https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978842111297.jpg" title="1545978842111297.jpg" alt="u=2555484987,1876385797&amp;fm=26&amp;gp=0.jpg"/></p>
             * des_pic : [{"pic":"https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978840111237.jpg","width":750,"height":517},{"pic":"https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978842111297.jpg","width":750,"height":1071}]
             */

            private String text;
            private List<DesPicBean> des_pic;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public List<DesPicBean> getDes_pic() {
                return des_pic;
            }

            public void setDes_pic(List<DesPicBean> des_pic) {
                this.des_pic = des_pic;
            }

            public static class DesPicBean {
                /**
                 * pic : https://shop.carsuper.cn//ueditor/php/upload/image/20181228/1545978840111237.jpg
                 * width : 750
                 * height : 517
                 */

                private String pic;
                private int width;
                private int height;

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }
    }
}
