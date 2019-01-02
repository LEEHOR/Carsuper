package com.carsuper.coahr.utils.imageLoader;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.DrawableCrossFadeFactory;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.view.base.BaseApplication;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;


/**
 * Author： hengzwd on 2017/10/11.
 * Email：hengzwdhengzwd@qq.com
 */

public class Imageloader {
 private  static  ObjectAnimator anim;
    private static Bitmap bitmap;

    public static void loadImage(String path, final ImageView targetImage) {
      //  setAnimator(targetImage);
        //R.drawable.image_loading_anim_progress
        Glide.with(BaseApplication.mContext).load(path).crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).thumbnail(0.1f).placeholder(R.mipmap.primary).error(R.mipmap.loadimage_err).override(500,500).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
               // anim.cancel();
              //  stopAnim(targetImage);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
              //  anim.cancel();
              //  stopAnim(targetImage);
                return false;
            }
        }).into(targetImage);
    }

    public static void loadGif(int resId, final ImageView targetImage) {
        Glide.with(BaseApplication.mContext).load(resId).asGif().crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.mipmap.primary).error(R.mipmap.loadimage_err).into(targetImage);
    }

    public static void loadImage(Uri path, final ImageView targetImage) {
        // Glide.with(BaseApplication.mContext).load(path).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.mipmap.primary).into(targetImage);

        //
       // setAnimator(targetImage);
        Glide.with(BaseApplication.mContext).load(path).crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).thumbnail(0.1f).placeholder(R.mipmap.primary).error(R.mipmap.loadimage_err).override(500,500).listener(new RequestListener<Uri, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
               // anim.cancel();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
               // anim.cancel();
                return false;
            }
        }).into(targetImage);

    }

    /**
     * 加载圆形图片
     * @param path
     * @param targetImage
     */
    public static void loadCircularImage(Uri path, final ImageView targetImage) {
        Glide.with(BaseApplication.mContext).load(path).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).thumbnail(0.1f).placeholder(R.mipmap.primary).error(R.mipmap.loadimage_err).override(500,500).into(new BitmapImageViewTarget(targetImage){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(BaseApplication.mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                targetImage.setImageDrawable(circularBitmapDrawable);

            }
        });
    }

    /**
     * 加载圆形图片
     * @param path
     * @param targetImage
     */
    public static void loadCircularImage(String path, final ImageView targetImage) {
        Glide.with(BaseApplication.mContext).load(path).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).thumbnail(0.1f).placeholder(R.mipmap.primary).error(R.mipmap.loadimage_err).override(500,500).into(new BitmapImageViewTarget(targetImage){
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(BaseApplication.mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                targetImage.setImageDrawable(circularBitmapDrawable);

            }
        });
    }
    /**
     * 根据图片的url路径获得Bitmap对象
     */
    public static Bitmap getBitMap(Uri path) {
        Glide.with(BaseApplication.mContext).load(path).asBitmap().override(500, 500).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.mipmap.primary).error(R.mipmap.loadimage_err).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                bitmap = resource;
            }

        });

        return bitmap;
    }

    /**
     * 根据图片的url路径获得Bitmap对象
     */
    public static Bitmap getBitMap(String path) {
        //  final Bitmap[] bitmap = new Bitmap[1];
        Glide.with(BaseApplication.mContext).load(path).asBitmap().override(500, 500).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.mipmap.primary).error(R.mipmap.loadimage_err).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                //final Bitmap bitmap1=resource;
                bitmap = resource;
            }

        });
        return bitmap;
    }

    public static Bitmap getBitMap(int res) {
        Glide.with(BaseApplication.mContext).load(res).asBitmap().override(500,500).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.mipmap.primary).error(R.mipmap.loadimage_err).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                //final Bitmap bitmap1=resource;
                bitmap = resource;
            }

        });
        return bitmap;
    }

    public static void loadBitMap(Bitmap bitmap,ImageView imageView){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes=baos.toByteArray();
        Glide.with(BaseApplication.mContext).load(bytes).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.mipmap.primary).into(imageView);
    }

    /**
     * 加载动画
     * @param img
     */
    private static void setAnimator(ImageView img){
        anim = ObjectAnimator.ofInt(img, "ImageLevel", 0,100);
        anim.setDuration(800);
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.start();
    }

    private static void stopAnim(ImageView img){
        anim.cancel();

    }

    /**
     * 大图加载
     * @param path
     * @param targetImage
     */
    public static void loadImage_larger(String path, final ImageView targetImage) {
        setAnimator(targetImage);
        Glide.with(BaseApplication.mContext).load(path).crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).thumbnail(0.1f).placeholder(R.mipmap.primary).error(R.mipmap.loadimage_err).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).into(targetImage);
    }

    public static void loadImage_larger(Uri path, final ImageView targetImage) {
        setAnimator(targetImage);
        Glide.with(BaseApplication.mContext).load(path).crossFade().diskCacheStrategy(DiskCacheStrategy.RESULT).thumbnail(0.1f).placeholder(R.mipmap.primary).error(R.mipmap.loadimage_err).listener(new RequestListener<Uri, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).into(targetImage);
    }


    /**
     * 设置控件大小
     * @param view
     * @param imageWidth
     * @param imageHeight
     */
    public static void setViewSize(View view, int imageWidth, int imageHeight, ImageView.ScaleType type){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width =imageWidth;
        params.height=imageHeight;
        view.setLayoutParams(params);
        if (view instanceof ImageView){
            if (type !=null){
                ((ImageView) view).setScaleType(type);
            }
        }
    }

    /**
     * 设置控件大小
     * @param child
     * @param width
     * @param height
     * @param type
     *  父布局类型
     */
    public static void setViewSize(View child,int width,int height,int type){
        if (type ==1){
            LinearLayout.LayoutParams params =(LinearLayout.LayoutParams)child.getLayoutParams();
            params.width =width;
            params.height=height;
            child.setLayoutParams(params);
        } else if (type ==2){
            RelativeLayout.LayoutParams params =(RelativeLayout.LayoutParams)child.getLayoutParams();
            params.width =width;
            params.height=height;
            child.setLayoutParams(params);
        } else if (type ==3){
            ViewGroup.LayoutParams params =(ViewGroup.LayoutParams)child.getLayoutParams();
            params.width =width;
            params.height=height;
            child.setLayoutParams(params);
        }


    }
}
