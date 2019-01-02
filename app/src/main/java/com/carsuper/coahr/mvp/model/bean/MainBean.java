package com.carsuper.coahr.mvp.model.bean;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/18.
 * Email：hengzwdhengzwd@qq.com
 */
public class MainBean {


    /**
     * code : 0
     * jdata : {"allDatas":{"activity":[{"adv_img":"https://shop.carsuper.cn/Data/Advertise/20180702/5b398d5081b32.png","adv_title":"爆款1","adv_url":"","id":"14"}],"choose":[{"c_id":"67","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00","c_price_old":"359.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180704/5b3c35d08b3c8.png"}],"goodsType1":[{"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_price":"580.00","c_price_old":"0.01","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png"},{"c_id":"93","c_name":"TEST  TYRE123","c_price":"44.00","c_price_old":"0.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png"},{"c_id":"91","c_name":"TEST  TYRE1","c_price":"22.00","c_price_old":"0.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png"}],"goodsType2":[{"c_id":"67","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00","c_price_old":"359.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180704/5b3c35d08b3c8.png"}],"goodsType3":[{"c_id":"67","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00","c_price_old":"359.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180704/5b3c35d08b3c8.png"}],"news":[{"n_id":"5","title":"奥迪e-tron内饰官图发布 标配空气悬架"},{"n_id":"4","title":"奔驰新款V级/CLS 300上市"},{"n_id":"3","title":"奥迪全新Q5L上市前瞻"}]},"headImages":[{"adv_img":"https://shop.carsuper.cn/Data/Advertise/20180702/5b398d41aba95.png","adv_title":"轮播图3","adv_url":"","id":"13"},{"adv_img":"https://shop.carsuper.cn/Data/Advertise/20180702/5b398d3016e36.png","adv_title":"轮播图2","adv_url":"","id":"12"},{"adv_img":"https://shop.carsuper.cn/Data/Advertise/20180702/5b398cf7e1113.png","adv_title":"轮播图1","adv_url":"","id":"11"}]}
     * msg : 获取数据成功
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
         * allDatas : {"activity":[{"adv_img":"https://shop.carsuper.cn/Data/Advertise/20180702/5b398d5081b32.png","adv_title":"爆款1","adv_url":"","id":"14"}],"choose":[{"c_id":"67","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00","c_price_old":"359.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180704/5b3c35d08b3c8.png"}],"goodsType1":[{"c_id":"76","c_name":"米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦 ","c_price":"580.00","c_price_old":"0.01","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png"},{"c_id":"93","c_name":"TEST  TYRE123","c_price":"44.00","c_price_old":"0.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png"},{"c_id":"91","c_name":"TEST  TYRE1","c_price":"22.00","c_price_old":"0.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png"}],"goodsType2":[{"c_id":"67","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00","c_price_old":"359.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180704/5b3c35d08b3c8.png"}],"goodsType3":[{"c_id":"67","c_name":"道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L","c_price":"329.00","c_price_old":"359.00","c_thumbnail":"https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180704/5b3c35d08b3c8.png"}],"news":[{"n_id":"5","title":"奥迪e-tron内饰官图发布 标配空气悬架"},{"n_id":"4","title":"奔驰新款V级/CLS 300上市"},{"n_id":"3","title":"奥迪全新Q5L上市前瞻"}]}
         * headImages : [{"adv_img":"https://shop.carsuper.cn/Data/Advertise/20180702/5b398d41aba95.png","adv_title":"轮播图3","adv_url":"","id":"13"},{"adv_img":"https://shop.carsuper.cn/Data/Advertise/20180702/5b398d3016e36.png","adv_title":"轮播图2","adv_url":"","id":"12"},{"adv_img":"https://shop.carsuper.cn/Data/Advertise/20180702/5b398cf7e1113.png","adv_title":"轮播图1","adv_url":"","id":"11"}]
         */

        private AllDatasEntity allDatas;
        private List<HeadImagesEntity> headImages;

        public AllDatasEntity getAllDatas() {
            return allDatas;
        }

        public void setAllDatas(AllDatasEntity allDatas) {
            this.allDatas = allDatas;
        }

        public List<HeadImagesEntity> getHeadImages() {
            return headImages;
        }

        public void setHeadImages(List<HeadImagesEntity> headImages) {
            this.headImages = headImages;
        }

        public static class AllDatasEntity {
            private List<ActivityEntity> activity;
            private List<ChooseEntity> choose;
            private List<GoodsTypeEntity> goodsType1;
            private List<GoodsTypeEntity> goodsType2;
            private List<GoodsTypeEntity> goodsType3;
            private List<NewsEntity> news;

            public List<ActivityEntity> getActivity() {
                return activity;
            }

            public void setActivity(List<ActivityEntity> activity) {
                this.activity = activity;
            }

            public List<ChooseEntity> getChoose() {
                return choose;
            }

            public void setChoose(List<ChooseEntity> choose) {
                this.choose = choose;
            }

            public List<GoodsTypeEntity> getGoodsType1() {
                return goodsType1;
            }

            public void setGoodsType1(List<GoodsTypeEntity> goodsType1) {
                this.goodsType1 = goodsType1;
            }

            public List<GoodsTypeEntity> getGoodsType2() {
                return goodsType2;
            }

            public void setGoodsType2(List<GoodsTypeEntity> goodsType2) {
                this.goodsType2 = goodsType2;
            }

            public List<GoodsTypeEntity> getGoodsType3() {
                return goodsType3;
            }

            public void setGoodsType3(List<GoodsTypeEntity> goodsType3) {
                this.goodsType3 = goodsType3;
            }

            public List<NewsEntity> getNews() {
                return news;
            }

            public void setNews(List<NewsEntity> news) {
                this.news = news;
            }

            public static class ActivityEntity {
                /**
                 * adv_img : https://shop.carsuper.cn/Data/Advertise/20180702/5b398d5081b32.png
                 * adv_title : 爆款1
                 * adv_url :
                 * id : 14
                 */

                private String adv_img;
                private String adv_title;
                private String adv_url;
                private String id;

                public String getAdv_img() {
                    return adv_img;
                }

                public void setAdv_img(String adv_img) {
                    this.adv_img = adv_img;
                }

                public String getAdv_title() {
                    return adv_title;
                }

                public void setAdv_title(String adv_title) {
                    this.adv_title = adv_title;
                }

                public String getAdv_url() {
                    return adv_url;
                }

                public void setAdv_url(String adv_url) {
                    this.adv_url = adv_url;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }

            public static class ChooseEntity {
                /**
                 * c_id : 67
                 * c_name : 道达尔(Total)快驰9000 全合成机油 5W30 SN/GF-5级 4L
                 * c_price : 329.00
                 * c_price_old : 359.00
                 * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180704/5b3c35d08b3c8.png
                 */

                private String c_id;
                private String c_name;
                private String c_price;
                private String c_price_old;
                private String c_thumbnail;

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

                public String getC_price() {
                    return c_price;
                }

                public void setC_price(String c_price) {
                    this.c_price = c_price;
                }

                public String getC_price_old() {
                    return c_price_old;
                }

                public void setC_price_old(String c_price_old) {
                    this.c_price_old = c_price_old;
                }

                public String getC_thumbnail() {
                    return c_thumbnail;
                }

                public void setC_thumbnail(String c_thumbnail) {
                    this.c_thumbnail = c_thumbnail;
                }
            }

            public static class GoodsTypeEntity {
                /**
                 * c_id : 76
                 * c_name : 米其林(MICHELIN)轮胎205/55R16 91V ENERGY XM2韧悦
                 * c_price : 580.00
                 * c_price_old : 0.01
                 * c_thumbnail : https://shop.carsuper.cn/Data/Commodity/Thumbnail/20180712/5b46b1be3567e.png
                 */

                private String c_id;
                private String c_name;
                private String c_price;
                private String c_price_old;
                private String c_thumbnail;

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

                public String getC_price() {
                    return c_price;
                }

                public void setC_price(String c_price) {
                    this.c_price = c_price;
                }

                public String getC_price_old() {
                    return c_price_old;
                }

                public void setC_price_old(String c_price_old) {
                    this.c_price_old = c_price_old;
                }

                public String getC_thumbnail() {
                    return c_thumbnail;
                }

                public void setC_thumbnail(String c_thumbnail) {
                    this.c_thumbnail = c_thumbnail;
                }
            }
            public static class NewsEntity {
                /**
                 * n_id : 5
                 * title : 奥迪e-tron内饰官图发布 标配空气悬架
                 */

                private String n_id;
                private String title;

                public String getN_id() {
                    return n_id;
                }

                public void setN_id(String n_id) {
                    this.n_id = n_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }

        public static class HeadImagesEntity {
            /**
             * adv_img : https://shop.carsuper.cn/Data/Advertise/20180702/5b398d41aba95.png
             * adv_title : 轮播图3
             * adv_url :
             * id : 13
             */

            private String adv_img;
            private String adv_title;
            private String adv_url;
            private String id;

            public String getAdv_img() {
                return adv_img;
            }

            public void setAdv_img(String adv_img) {
                this.adv_img = adv_img;
            }

            public String getAdv_title() {
                return adv_title;
            }

            public void setAdv_title(String adv_title) {
                this.adv_title = adv_title;
            }

            public String getAdv_url() {
                return adv_url;
            }

            public void setAdv_url(String adv_url) {
                this.adv_url = adv_url;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
