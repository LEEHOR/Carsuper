# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile



#-----------基本配置--------------
# 代码混淆压缩比，在0~7之间，默认为5，一般不需要改
-optimizationpasses 5

# 混淆时不使用大小写混合，混淆后的类名为小写
-dontusemixedcaseclassnames

# 指定不去忽略非公共的库的类
-dontskipnonpubliclibraryclasses

# 指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers

# 不做预校验，可加快混淆速度
# preverify是proguard的4个步骤之一
# Android不需要preverify，去掉这一步可以加快混淆速度
-dontpreverify

# 不优化输入的类文件
-dontoptimize

# 混淆时生成日志文件，即映射文件
-verbose

# 指定映射文件的名称
-printmapping proguardMapping.txt

#混淆时所采用的算法


# 保护代码中的Annotation不被混淆
-keepattributes *Annotation*

# 忽略警告
-ignorewarning

# 保护泛型不被混淆
-keepattributes Signature

# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

#-----------需要保留的东西--------------
# 保留所有的本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保留了继承自Activity、Application、Fragment这些类的子类
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService

# support-v4
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
# support-v7
-dontwarn android.support.v7.**                                             #去掉警告
-keep class android.support.v7.** { *; }                                    #过滤android.support.v7
-keep interface android.support.v7.app.** { *; }
-keep public class * extends android.support.v7.**

#----------------保护指定的类和类的成员，但条件是所有指定的类和类成员是要存在------------------------------------
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保持自定义控件类不被混淆，指定格式的构造方法不去混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保持自定义控件类不被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
    *** get*();
}
# 保留在Activity中的方法参数是View的方法
# 从而我们在layout里边编写onClick就不会被影响
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

# 保留枚举 enum 类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# 保留 Serializable 不被混淆
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 不混淆资源类
-keepclassmembers class **.R$* { *; }

# 对于带有回调函数onXXEvent()的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
}
# WebView
#-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
#   public *;
#}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String);
}

# 保留实体类和成员不被混淆(根据具体情况修改entity的路径)
-keep class com.smart.tvpos.bean.**{*;}



#eventbus
-keepattributes *Annotation*

-keepclassmembers class ** {
    @de.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum de.greenrobot.eventbus.ThreadMode { *; }
#过滤fastjson
-keep class com.alibaba.fastjson.** { *; }
-dontwarn com.alibaba.fastjson.**

#过滤okhttp
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

#过滤glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#retrofit
-dontwarn okio.**
-dontwarn javax.annotation.**
#解决使用Retrofit+rxJava联网时，在6.0系统出现java.lang.InternalError奔溃的问题:http://blog.csdn.net/mp624183768/article/details/79242147
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
#-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
#    rx.internal.util.atomic.LinkedQueueNode producerNode;
#}
#-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
#    rx.internal.util.atomic.LinkedQueueNode consumerNode;
#}


#过滤butterknife项目
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#微信支付混淆
-keep class com.tencent.mm.opensdk.** {
*;
}
-keep class com.tencent.wxop.** {
*;
}
-keep class com.tencent.mm.sdk.** {
*;
}
#--------------------------------------第三方sdk------------------------------------------

#友盟分享需要混淆的处理
 -dontshrink
 -dontoptimize
 -dontwarn com.google.android.maps.**
 -dontwarn android.webkit.WebView
 -dontwarn com.umeng.**
 -dontwarn com.tencent.weibo.sdk.**
 -dontwarn com.facebook.**
 -keep public class javax.**
 -keep public class android.webkit.**
 -dontwarn android.support.v4.**
 -keep enum com.facebook.**
 -keepattributes Exceptions,InnerClasses,Signature
 -keepattributes *Annotation*
 -keepattributes SourceFile,LineNumberTable

 -keep public interface com.facebook.**
 -keep public interface com.tencent.**
 -keep public interface com.umeng.socialize.**
 -keep public interface com.umeng.socialize.sensor.**
 -keep public interface com.umeng.scrshot.**

 -keep public class com.umeng.socialize.* {*;}


 -keep class com.facebook.**
 -keep class com.facebook.** { *; }
 -keep class com.umeng.scrshot.**
 -keep public class com.tencent.** {*;}
 -keep class com.umeng.socialize.sensor.**
 -keep class com.umeng.socialize.handler.**
 -keep class com.umeng.socialize.handler.*
 -keep class com.umeng.weixin.handler.**
 -keep class com.umeng.weixin.handler.*
 -keep class com.umeng.qq.handler.**
 -keep class com.umeng.qq.handler.*
 -keep class com.umeng.socialize.handler.UMMoreHandler{*;}
 -keep class com.tencent.mm.opensdk.modelmsg.WXMediaMessage {*;}
 -keep class com.tencent.mm.opensdk.modelmsg.** implements com.tencent.mm.opensdk.modelmsg.WXMediaMessage$IMediaObject {*;}
 -keep class com.tencent.mm.sdk.** {
    *;
 }
 -keep class com.tencent.mm.opensdk.** {
    *;
 }
 -keep class com.tencent.wxop.** {
    *;
 }
 -keep class com.tencent.mm.sdk.** {
    *;
 }
 -dontwarn twitter4j.**
 -keep class twitter4j.** { *; }

 -keep class com.tencent.** {*;}
 -dontwarn com.tencent.**
 -keep class com.kakao.** {*;}
 -dontwarn com.kakao.**
 -keep public class com.umeng.com.umeng.soexample.R$*{
     public static final int *;
 }
 -keep public class com.linkedin.android.mobilesdk.R$*{
     public static final int *;
 }
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

# -keep class com.tencent.open.TDialog$*
# -keep class com.tencent.open.TDialog$* {*;}
# -keep class com.tencent.open.PKDialog
# -keep class com.tencent.open.PKDialog {*;}
# -keep class com.tencent.open.PKDialog$*
# -keep class com.tencent.open.PKDialog$* {*;}
# -keep class com.umeng.socialize.impl.ImageImpl {*;}
# -keep class com.sina.** {*;}
# -dontwarn com.sina.**
# -keep class  com.alipay.share.sdk.** {
#    *;
# }

 -keepnames class * implements android.os.Parcelable {
     public static final ** CREATOR;
 }

 -keep class com.linkedin.** { *; }
 -keep class com.android.dingtalk.share.ddsharemodule.** { *; }
 -keepattributes Signature

# 友盟统计
-keep class com.umeng.commonsdk.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep public class [com.zongxueguan.naochanle_android].R$*{
public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#-------------------------------------------------腾讯X5浏览器-----------------------------------------------------
# ------------------ Keep LineNumbers and properties ---------------- #
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
# --------------------------------------------------------------------------

# Addidional for x5.sdk classes for apps

-keep class com.tencent.smtt.export.external.**{
    *;
}

-keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {
	*;
}

-keep class com.tencent.smtt.sdk.CacheManager {
	public *;
}

-keep class com.tencent.smtt.sdk.CookieManager {
	public *;
}

-keep class com.tencent.smtt.sdk.WebHistoryItem {
	public *;
}

-keep class com.tencent.smtt.sdk.WebViewDatabase {
	public *;
}

-keep class com.tencent.smtt.sdk.WebBackForwardList {
	public *;
}

-keep public class com.tencent.smtt.sdk.WebView {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
	public static final <fields>;
	public java.lang.String getExtra();
	public int getType();
}

-keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebView$PictureListener {
	public <fields>;
	public <methods>;
}


-keepattributes InnerClasses

-keep public enum com.tencent.smtt.sdk.WebSettings$** {
    *;
}

-keep public enum com.tencent.smtt.sdk.QbSdk$** {
    *;
}

-keep public class com.tencent.smtt.sdk.WebSettings {
    public *;
}


-keepattributes Signature
-keep public class com.tencent.smtt.sdk.ValueCallback {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebViewClient {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.DownloadListener {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebChromeClient {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {
	public <fields>;
	public <methods>;
}

-keep class com.tencent.smtt.sdk.SystemWebChromeClient{
	public *;
}
# 1. extension interfaces should be apparent
-keep public class com.tencent.smtt.export.external.extension.interfaces.* {
	public protected *;
}

# 2. interfaces should be apparent
-keep public class com.tencent.smtt.export.external.interfaces.* {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.WebViewCallbackClient {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebIconDatabase {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebStorage {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.DownloadListener {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.QbSdk {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.CookieSyncManager {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.Tbs* {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.LogFileUtils {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.TbsLog {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.TbsLogClient {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.CookieSyncManager {
	public <fields>;
	public <methods>;
}

# Added for game demos
#-keep public class com.tencent.smtt.sdk.TBSGamePlayer {
#	public <fields>;
#	public <methods>;
#}
#
#-keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
#	public <fields>;
#	public <methods>;
#}
#
#-keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
#	public <fields>;
#	public <methods>;
#}
#
#-keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
#	public <fields>;
#	public <methods>;
#}

-keep public class com.tencent.smtt.utils.Apn {
	public <fields>;
	public <methods>;
}
-keep class com.tencent.smtt.** {
	*;
}
# end


-keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
	public <fields>;
	public <methods>;
}

-keep class MTT.ThirdAppInfoNew {
	*;
}

#-keep class com.tencent.mtt.MttTraceEvent {
#	*;
#}

# Game related
-keep public class com.tencent.smtt.gamesdk.* {
	public protected *;
}

#-keep public class com.tencent.smtt.sdk.TBSGameBooter {
#        public <fields>;
#        public <methods>;
#}

#-keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {
#	public protected *;
#}
#
#-keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {
#	public protected *;
#}
#
#-keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {
#	public *;
#}

#------------------  下方是共性的排除项目         ----------------
# 方法名中含有“JNI”字符的，认定是Java Native Interface方法，自动排除
# 方法名中含有“JRI”字符的，认定是Java Reflection Interface方法，自动排除

-keepclasseswithmembers class * {
    ... *JNI*(...);
}

-keepclasseswithmembernames class * {
	... *JRI*(...);
}

-keep class **JNI* {*;}


#------------------------------------阿里支付--------------------------------------------------------
#-libraryjars libs/alipaySdk-15.5.9-20181123210601.aar
#-keep class com.alipay.android.app.IAlixPay{*;}
#-keep class com.alipay.android.app.IAlixPay$Stub{*;}
#-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
#-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
#-keep class com.alipay.sdk.app.PayTask{ public *;}
#-keep class com.alipay.sdk.app.AuthTask{ public *;}