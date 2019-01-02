package com.carsuper.coahr.utils.QRCode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class QRCode {
    /**
     * 不加logo的二维码
     *
     * @param path
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getQRCodeImage(String path, int width, int height) {
        Bitmap qr = CodeUtils.createImage(path, width, height, null);
        return qr;
    }

    /**
     * 带有logo的二维码
     *
     * @param path
     * @param width
     * @param height
     * @param imageResource
     * @return
     */
    public static Bitmap getQRCodeAddLogoImage(String path, int width, int height, int imageResource) {
        Bitmap qr = CodeUtils.createImage(path, width, height, BitmapFactory.decodeResource(BaseApplication.mContext.getResources(), imageResource));
        return qr;
    }
}
