package com.carsuper.coahr.wxapi;

import android.content.Context;
import android.graphics.Bitmap;

import com.carsuper.coahr.common.Constants;
import com.socks.library.KLog;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;

public class WXShareUtils {
    private IWXAPI api;
    private Context context;
private static  WXShareUtils weChatShareUtil;
    public static WXShareUtils getInstance (Context context) {
        if (weChatShareUtil == null) {
            weChatShareUtil = new WXShareUtils();
        }
        if (weChatShareUtil.api != null) {
            weChatShareUtil.api.unregisterApp();
        }
        weChatShareUtil.context = context;
        weChatShareUtil.regToWx();
        return weChatShareUtil;
    }

    //注册到微信
    public void regToWx(){
        //通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(context, Constants.wx_app_id, false);
        //将应用的appId注册到微信
        api.registerApp(Constants.wx_app_id);

    }
    public IWXAPI getApi() {
        return api;
    }
    /**
     * 分享文字到朋友圈或者好友
     *
     * @param text  文本内容
     * @param scene 分享方式：好友还是朋友圈
     */
    public void shareText(String title,String text, int scene) {
        //初始化一个WXTextObject对象，填写分享的文本对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;
         share(textObj,title,text,scene);
    }

    /**
     * 分享图片到朋友圈或者好友
     *
     * @param bmp   图片的Bitmap对象
     * @param scene 分享方式：好友还是朋友圈
     */
    public void sharePic(Bitmap bmp, int scene) {
        //初始化一个WXImageObject对象
        WXImageObject imageObj = new WXImageObject(bmp);
        //设置缩略图
        Bitmap thumb = Bitmap.createScaledBitmap(bmp, 60, 60, true);
        bmp.recycle();
         sharePic_url(imageObj,null,thumb,null,scene);
    }
    /**
     * 分享网页到朋友圈或者好友，视频和音乐的分享和网页大同小异，只是创建的对象不同。
     * 详情参考官方文档：
     * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419317340&token=&lang=zh_CN
     *
     * @param url         网页的url
     * @param title       显示分享网页的标题
     * @param description 对网页的描述
     * @param scene       分享方式：好友还是朋友圈
     */
    public void shareUrl(String url, String title, Bitmap thumb, String description, int scene) {
        //初试话一个WXWebpageObject对象，填写url
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = url;

         sharePic_url(webPage,title,thumb,description,scene);
    }

    /**
     * 分享图片的方法
     * @param mediaObject
     * @param title
     * @param thumb
     * @param description
     * @param scene
     * @return
     */
    private void sharePic_url(WXMediaMessage.IMediaObject mediaObject,String title, Bitmap thumb,String description,  int scene) {
         shareAll(mediaObject, title, thumb, description, scene);
    }

    /**
     * 分享文字或链接
     * @param mediaObject
     * @param title
     * @param description
     * @param scene
     * @return
     */
    private void share(WXMediaMessage.IMediaObject mediaObject,String title, String description, int scene) {
         shareAll(mediaObject, title, null, description, scene);
    }

    /**
     *  总方法
     * @param mediaObject
     * @param title
     * @param thumb
     * @param description
     * @param scene
     * @return
     */
    private  void shareAll(final WXMediaMessage.IMediaObject mediaObject, final String title, final Bitmap thumb, final String description, final int scene) {

        Runnable shareRunnable = new Runnable() {
            @Override
            public void run() {
                //初始化一个WXMediaMessage对象，填写标题、描述
                WXMediaMessage msg = new WXMediaMessage(mediaObject);
                if (title != null) {
                    msg.title = title;
                }
                if (description != null) {
                    msg.description = description;
                }
                if (thumb != null) {
                    //msg.setThumbImage(thumb);
                    msg.thumbData = bmpToByteArray(thumb, true);
                }
                //构造一个Req
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;
                req.scene = scene;
                Constants.ShareNow="share";
                api.sendReq(req);
            }
        };
        Thread shareThread = new Thread(shareRunnable);
        shareThread.start();
    }


    //检查微信是否安装
    public boolean isWXAppInstalled() {
        return api.isWXAppInstalled();
    }


        private byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }





}
