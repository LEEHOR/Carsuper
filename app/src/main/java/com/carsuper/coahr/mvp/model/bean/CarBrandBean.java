package com.carsuper.coahr.mvp.model.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author： hengzwd on 2018/8/6.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarBrandBean {


    /**
     * code : 0
     * jdata : {"brand":[{"brand":[{"b_name":"安凯客车","cb_id":"42cbd7387a05499b86e6dd21acc19b11","first_letter":"A","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181014082121.jpg"},{"b_name":"奥驰","cb_id":"b15dc9a40bea4797a3530b8e654db7d4","first_letter":"A","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181013105547.jpg"}],"brand_letter":"A"},{"brand":[{"b_name":"北奔重卡","cb_id":"03becb3e675f46c18027560221576ac8","first_letter":"B","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181014564047.jpg"},{"b_name":"奔驰(进口)","cb_id":"0eba8e7da89642fd896be097e831d8a4","first_letter":"B","hot":"1","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101016228070.jpg"},{"b_name":"北汽威旺","cb_id":"1b238f47bedb45a5a96d41861cd03b8a","first_letter":"B","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101007133879.jpg"},{"b_name":"铂骏","cb_id":"43d16a9ef863472aad07a07d71692f5a","first_letter":"B","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181519000022.jpg"},{"b_name":"北汽威旺","cb_id":"5ef89902d1c9416995adc826f3a22173","first_letter":"B","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201702141555404848.jpg"},{"b_name":"北汽制造","cb_id":"65d43a0563154761974949efcaadc161","first_letter":"B","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201706061355202652.jpg"},{"b_name":"比亚迪","cb_id":"a6694c1bc34e402681d97fa0f096cb96","first_letter":"B","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101015291934.jpg"},{"b_name":"标致(进口)","cb_id":"ecadec5af3724a8a9628aab1f3ecb8db","first_letter":"B","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201701122115018763.png"},{"b_name":"北汽幻速","cb_id":"f9ff37ced167421d9ee2ac0ce4cd5cd8","first_letter":"B","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101017061572.jpg"}],"brand_letter":"B"},{"brand":[{"b_name":"长安商用","cb_id":"8e95cc0daeb04f5ab8fb9c2c957abea4","first_letter":"C","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181520387362.jpg"},{"b_name":"长安跨越","cb_id":"9545a69aaae947469f7557d6ee8af29c","first_letter":"C","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181520088218.jpg"},{"b_name":"长城","cb_id":"b584b05db7474823956619c3d47a8cb2","first_letter":"C","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181522319573.jpg"},{"b_name":"川交汽车","cb_id":"b7e26e9f8b44438e8299a92c7c21dd     2d","first_letter":"C","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707031417357268.jpg"},{"b_name":"长征汽车","cb_id":"cbe7a4aabf8b45dd825f2833a40b9c4d","first_letter":"C","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707031703440574.jpg"},{"b_name":"昌河","cb_id":"d1f8602e1fde4d2dbe174a8d799f1e7b","first_letter":"C","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101021146179.jpg"}],"brand_letter":"C"},{"brand":[{"b_name":"东沃","cb_id":"03bb98e7f32f4447aacbaec93bcc8716","first_letter":"D","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707031711074379.jpg"},{"b_name":"东风商用车","cb_id":"4503613d2e624555a20ae5f9d447573c","first_letter":"D","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201701122116387712.png"},{"b_name":"东风御风","cb_id":"466c27be7a5f4f47af152d8ca5887cc4","first_letter":"D","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181525364925.jpg"},{"b_name":"东风特专","cb_id":"4ea87d1902f14e9b8eee169b2a56e7d1","first_letter":"D","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181523392298.jpg"},{"b_name":"达夫","cb_id":"5ed9d8df4a0848e8bff876ed9688033a","first_letter":"D","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181527400353.jpg"},{"b_name":"大众(进口)","cb_id":"812f418bbac445368606445bebcc5718","first_letter":"D","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101350016175.jpg"},{"b_name":"东风股份","cb_id":"85cffb81bea8427ca3c4f6ec04f03c7a","first_letter":"D","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707030951524617.jpg"},{"b_name":"东风","cb_id":"a09f9b38ec2a4a3ea028d7b8af0e9243","first_letter":"D","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201706061351162453.jpg"},{"b_name":"东风柳汽","cb_id":"a1bc9f8d8baf425f8b8fc1e4c4661a24","first_letter":"D","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181524467628.jpg"},{"b_name":"大运汽车","cb_id":"bce6387cfea8405fba52ba4336c75fd5","first_letter":"D","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181526591122.jpg"},{"b_name":"东南汽车","cb_id":"bed24c2f32b44f8bbef80a37b48f29ef","first_letter":"D","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101127160223.jpg"}],"brand_letter":"D"},{"brand":[{"b_name":"福田乘用车","cb_id":"725151b371bf4f0cb0d740f80d97936d","first_letter":"F","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708091024293888.jpg"},{"b_name":"福建奔驰","cb_id":"80dd0358713d4a65a5da7fccce853898","first_letter":"F","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201702141600578722.jpg"},{"b_name":"福田","cb_id":"8cec391f30be4858ba35838d7faa6784","first_letter":"F","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181608526201.jpg"},{"b_name":"丰田(进口)","cb_id":"a256779296564d04b9354ddd9188e197","first_letter":"F","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101346391968.jpg"},{"b_name":"福特(进口)","cb_id":"fb876c27a89748898d68361a03662aee","first_letter":"F","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101019182138.jpg"}],"brand_letter":"F"},{"brand":[{"b_name":"GMC","cb_id":"6ad175c7e8ec486980470a3d489355ba","first_letter":"G","pic":"http://android.woyaokanche     .com:8082/51kanche/upload/file/201708181529140134.jpg"},{"b_name":"广汽日野","cb_id":"c568b744eda743cbb015377d8cbe2710","first_letter":"G","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181528325957.jpg"}],"brand_letter":"G"},{"brand":[{"b_name":"海格","cb_id":"0795569b07eb49bb88ea206ff6daf7b6","first_letter":"H","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201703061108569255.jpg"},{"b_name":"黄海汽车","cb_id":"0970922144a84316a032d53f4bfd27ff","first_letter":"H","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708091501243607.jpg"},{"b_name":"华菱重卡","cb_id":"5994f50cb7f04074ac7d26a7239e860b","first_letter":"H","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181530320286.jpg"}],"brand_letter":"H"},{"brand":[{"b_name":"江铃福特","cb_id":"0859ec82195947c5ae2e87885d86ec25","first_letter":"J","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101019506356.jpg"},{"b_name":"江西五十铃","cb_id":"0d1c2b76f99544f58387e80ca78c129f","first_letter":"J","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101423119273.jpg"},{"b_name":"九龙汽车","cb_id":"31e6487ac5a14441b4a0cd34ff35df59","first_letter":"J","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201703061108141381.jpg"},{"b_name":"江淮","cb_id":"45dfd11ef7234ca2955daf4ec71bd780","first_letter":"J","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181531423527.jpg"},{"b_name":"金杯","cb_id":"6def50a9e60c443c89fbe298f21bc8fb","first_letter":"J","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181533071831.jpg"},{"b_name":"江铃","cb_id":"e33437014d0543689dcf6cf5941a911d","first_letter":"J","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181532224740.jpg"}],"brand_letter":"J"},{"brand":[{"b_name":"开瑞汽车","cb_id":"14f7e17c98944c40b9642efb2c69ea29","first_letter":"K","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708091616403511.jpg"},{"b_name":"卡威汽车","cb_id":"71b58e985d8046c9a2a4e93ced219df4","first_letter":"K","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181533494652.jpg"},{"b_name":"凯马","cb_id":"7d4aad742d984befb88be42254135b46","first_letter":"K","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707071650280851.jpg"}],"brand_letter":"K"},{"brand":[{"b_name":"雷诺(进口)","cb_id":"197f400b0cf64333b09cc80bd9121d1c","first_letter":"L","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181535070724.jpg"},{"b_name":"力帆骏马","cb_id":"5e7481bd112a4a3b9a1c19dc0e2365c3","first_letter":"L","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707131528277143.jpg"},{"b_name":"猎豹","cb_id":"c725d87b718648eea1faab81db12ea6f","first_letter":"L","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708091653333341.jpg"},{"b_name":"联合卡车","cb_id":"ec2f8354f8a647a5977278f4c5153012","first_letter":"L","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181540043377.jpg"}],"brand_letter":"L"},{"brand":[{"b_name":"曼","cb_id":"c1ce36475a374c778d60ceba79f73548","first_letter":"M","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181540468552.jpg"}],"brand_letter":"M"},{"brand":[{"b_name":"南京依维科","cb_id":"8e814bd0ea87490888cede7d597310fc","first_letter":"N","pic":"http://android.woyaok     anche.com:8082/51kanche/upload/file/201703032048201114.jpg"},{"b_name":"南京依维柯","cb_id":"97898f74648d498faad62cd1dc53c2f4","first_letter":"N","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181541232412.jpg"},{"b_name":"南京金龙","cb_id":"e4a8d1d531cc4df58902421a825cf0f8","first_letter":"N","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201703061112140916.jpg"}],"brand_letter":"N"},{"brand":[{"b_name":"庆铃汽车","cb_id":"2aa4282346064cbc9fe7b96baf5daa43","first_letter":"Q","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181542010458.jpg"}],"brand_letter":"Q"},{"brand":[{"b_name":"日产(进口)","cb_id":"857f1d677fcf49bd8a6e47a487b9ba6e","first_letter":"R","hot":"1","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101135392691.jpg"}],"brand_letter":"R"},{"brand":[{"b_name":"时代汽车","cb_id":"082dd3d634954b6a8eb0a84bed465d07","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707201617515123.jpg"},{"b_name":"上汽跃进","cb_id":"1b486e342d224a4caff20c40709af526","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181542470170.jpg"},{"b_name":"三环十通","cb_id":"26789b34b1ee4605aa07af971b77725b","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181555256542.jpg"},{"b_name":"时风","cb_id":"466920fcd5964d32886f1980b583aafd","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181543442561.jpg"},{"b_name":"三一重工","cb_id":"6137f369770540cc94a6ca84a3640fea","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707141146279462.jpg"},{"b_name":"上汽红岩","cb_id":"67f544a11ca04297a3bbd3c19f8f8175","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181554193050.jpg"},{"b_name":"上汽大通","cb_id":"745f0be053484655ab39294434711412","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101339280974.jpg"},{"b_name":"四川现代","cb_id":"765b5c8695e247b2809155a104850279","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181607146264.jpg"},{"b_name":"斯堪尼亚","cb_id":"974edaf699aa4affa8c0c63483953f10","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181547137637.jpg"},{"b_name":"陕汽通家","cb_id":"9d086cfcd2c042a0a07952113f242383","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181545290728.jpg"},{"b_name":"陕汽","cb_id":"a227690f54a541fb813a3717e5cc3ff5","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181545346933.jpg"},{"b_name":"上汽通用五菱","cb_id":"ccf68f37a6b240d999519143fd02bc75","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181558557026.jpg"},{"b_name":"上汽通用五菱","cb_id":"f066a4ee039940c4b8fb27adce497706","first_letter":"S","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201703032049472940.jpg"}],"brand_letter":"S"},{"brand":[{"b_name":"唐骏汽车","cb_id":"ecbaf0a3dd2b49da9bad116755946359","first_letter":"T","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181556181101.jpg"}],"brand_letter":"T"},{"brand":[{"b_name":"沃尔沃(进口)","cb_id":"4679c300ce514552bfc087bb5e0fc51d","first_letter":"W","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101511561753.jpg"},{"b_name":"威麟","cb_id":"d5c13e58bb924730bda179d20cc4e0f2","first_letter":"W","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201703061106128857.jpg"}],"brand_letter":"W"},{"brand":[{"b_name":"厦门金龙","cb_id":"488c71cc46174d69b35f893e05d98d92","first_letter":"X","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201702071103192857.png"},{"b_name":"厦门金旅","cb_id":"6bddee972e7f469b95a940ced37a9bf6","first_letter":"X","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201703061109466308.jpg"},{"b_name":"徐工集团","cb_id":"bae57fe81f1f4fc893e7488641d3928c","first_letter":"X","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707211530137137.jpg"}],"brand_letter":"X"},{"brand":[{"b_name":"一汽丰田","cb_id":"377982a45c5848d8bad9a491bda93dac","first_letter":"Y","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708081711005362.jpg"},{"b_name":"一汽通用","cb_id":"50705896de0049ebb132a8d6d3502c63","first_letter":"Y","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201704181622290408.jpg"},{"b_name":"一汽解放","cb_id":"74c4227b7f094ed0b0f23b5cc473ca31","first_letter":"Y","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708281447129016.jpg"},{"b_name":"一汽柳特","cb_id":"9710761485c245e199e854b9d3a1bed5","first_letter":"Y","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707241457201290.jpg"},{"b_name":"一汽解放 ","cb_id":"9a75084a844546e3a79f2bd4b5644ebb","first_letter":"Y","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201702071051497486.png"},{"b_name":"一汽凌源","cb_id":"bce7d7be4b3a417d9a976608928f55ff","first_letter":"Y","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707211631127970.jpg"},{"b_name":"一汽红塔","cb_id":"e8b8f81cb0e247048d09eb333dad2599","first_letter":"Y","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707241512420342.jpg"},{"b_name":"一拖重卡","cb_id":"f4b6ee783f1346cba47fa07169990860","first_letter":"Y","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707211538327288.jpg"},{"b_name":"一汽吉林","cb_id":"fdebd1be0b4f49d1b61996df1046b6ef","first_letter":"Y","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201707241425013158.jpg"}],"brand_letter":"Y"},{"brand":[{"b_name":"郑州日产","cb_id":"1e7c2f596817484492fae13b6d346756","first_letter":"Z","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201702141610487389.jpg"},{"b_name":"中国重汽","cb_id":"7f1760ead381432bb1d9c980a11e5f67","first_letter":"Z","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181608077262.jpg"},{"b_name":"中国重汽·汕德卡","cb_id":"c47cd69fd940438bbe50dd8e529e1e05","first_letter":"Z","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181607298389.jpg"},{"b_name":"中兴汽车","cb_id":"d818071bd2b84b9d91fdd34601785eba","first_letter":"Z","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101452083529.jpg"}],"brand_letter":"Z"}],"hot":[{"b_name":"奔驰(进口)","cb_id":"0eba8e7da89642fd896be097e831d8a4","first_letter":"B","hot":"1","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101016228070.jpg"},{"b_name":"日产(进口)","cb_id":"857f1d677fcf49bd8a6e47a487b9ba6e","first_letter":"R","hot":"1","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708101135392691.jpg"}]}
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

        private List<BrandEntityX> brand;
        private List<HotEntity> hot;

        public List<BrandEntityX> getBrand() {
            return brand;
        }

        public void setBrand(List<BrandEntityX> brand) {
            this.brand = brand;
        }

        public List<HotEntity> getHot() {
            return hot;
        }

        public void setHot(List<HotEntity> hot) {
            this.hot = hot;
        }


        public List<JdataEntity.BrandEntityX.BrandEntity> getSortEntity() {
            Collections.sort(brand, new Comparator<BrandEntityX>() {
                @Override
                public int compare(JdataEntity.BrandEntityX o1, JdataEntity.BrandEntityX o2) {
                    return o1.getBrand_letter().compareTo(o2.getBrand_letter());
                }
            });


            List<JdataEntity.BrandEntityX.BrandEntity> brandEntities = new ArrayList<>();
            for (JdataEntity.BrandEntityX entity : brand) {
                brandEntities.addAll(entity.getBrand());
            }
            return brandEntities;
        }

        public static class BrandEntityX {
            /**
             * brand : [{"b_name":"安凯客车","cb_id":"42cbd7387a05499b86e6dd21acc19b11","first_letter":"A","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181014082121.jpg"},{"b_name":"奥驰","cb_id":"b15dc9a40bea4797a3530b8e654db7d4","first_letter":"A","pic":"http://android.woyaokanche.com:8082/51kanche/upload/file/201708181013105547.jpg"}]
             * brand_letter : A
             */

            private String brand_letter;
            private List<BrandEntity> brand;

            public String getBrand_letter() {
                return brand_letter;
            }

            public void setBrand_letter(String brand_letter) {
                this.brand_letter = brand_letter;
            }

            public List<BrandEntity> getBrand() {
                return brand;
            }

            public void setBrand(List<BrandEntity> brand) {
                this.brand = brand;
            }

            public static class BrandEntity {
                /**
                 * b_name : 安凯客车
                 * cb_id : 42cbd7387a05499b86e6dd21acc19b11
                 * first_letter : A
                 * pic : http://android.woyaokanche.com:8082/51kanche/upload/file/201708181014082121.jpg
                 */

                public BrandEntity(String first_letter){
                        this.first_letter = first_letter;
                }

                private String b_name;
                private String cb_id;
                private String first_letter;
                private String pic;

                public String getB_name() {
                    return b_name;
                }

                public void setB_name(String b_name) {
                    this.b_name = b_name;
                }

                public String getCb_id() {
                    return cb_id;
                }

                public void setCb_id(String cb_id) {
                    this.cb_id = cb_id;
                }

                public String getFirst_letter() {
                    return first_letter;
                }

                public void setFirst_letter(String first_letter) {
                    this.first_letter = first_letter;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }
            }
        }

        public static class HotEntity {
            /**
             * b_name : 奔驰(进口)
             * cb_id : 0eba8e7da89642fd896be097e831d8a4
             * first_letter : B
             * hot : 1
             * pic : http://android.woyaokanche.com:8082/51kanche/upload/file/201708101016228070.jpg
             */

            private String b_name;
            private String cb_id;
            private String first_letter;
            private String hot;
            private String pic;

            public String getB_name() {
                return b_name;
            }

            public void setB_name(String b_name) {
                this.b_name = b_name;
            }

            public String getCb_id() {
                return cb_id;
            }

            public void setCb_id(String cb_id) {
                this.cb_id = cb_id;
            }

            public String getFirst_letter() {
                return first_letter;
            }

            public void setFirst_letter(String first_letter) {
                this.first_letter = first_letter;
            }

            public String getHot() {
                return hot;
            }

            public void setHot(String hot) {
                this.hot = hot;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}

