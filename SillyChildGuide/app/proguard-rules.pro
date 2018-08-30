# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

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

#支付宝
-dontwarn android.net.**
-dontwarn com.alipay.**
-keep class com.alipay.** { *;}
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
-keep class android.net.SSLCertificateSocketFactory{*;}
-keep class com.alipay.sdk.sys.** { *; }
-keep class android.net.** { *; }
-keepattributes EnclosingMethod
-keepattributes InnerClasses
#-target platform,Add-on

#保留注解参数
-keepattributes *Annotation*
-keep class * extends java.lang.annotation.Annotation { *; }

#友盟
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
-keep class UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
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
-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}
-keep class com.umeng.socialize.impl.ImageImpl {*;}
-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
   *;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
-keep class com.linkedin.** { *; }
-keep class com.android.dingtalk.share.ddsharemodule.** { *; }
-keepattributes Signature


#==============极光推送==============
-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }


#==============百度地图============
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}

-keep class com.sinovoice.** {*;}
-keep class pvi.com.** {*;}

-dontwarn com.baidu.**
-dontwarn vi.com.**
-dontwarn pvi.com.**

-ignorewarnings

#==================讯飞语音合成=====================
#-dontwarn com.iflytek.speech.**
#-keepattributes Signature
#-keep class com.iflytek.**{*;}
#-keep class com.chinaMobile.**{*;}

#==================环信即时消息=====================
#环信客服
-keep class com.hyphenate.** {*;}
-dontwarn  com.hyphenate.**
-keep class com.superrtc.** {*;}


#==================Cklibrary=====================
#==================Cklibrary=====================
#==================Cklibrary=====================

## ----------------------------------
##      UIL相关
## ----------------------------------
-keep class com.nostra13.universalimageloader.** { *; }
-keepclassmembers class com.nostra13.universalimageloader.** {*;}
-dontwarn com.nostra13.universalimageloader.**
-dontwarn cn.bingoogolapple.photopicker.imageloader.**

### ----------------------------------
###      Glide相关
### ----------------------------------
#-keep public class * extends com.bumptech.glide.module.AppGlideModule
#-keep public class * implements com.bumptech.glide.module.AppGlideModule
#-keep public class * implements com.bumptech.glide.module.GlideModule
#-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
#  **[] $VALUES;
#  public *;
#}
#
## for DexGuard only
##-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

# For retrolambda
-dontwarn java.lang.invoke.*

## ----------------------------------
##      PictureSelector 2.0
## ----------------------------------
-keep class com.luck.picture.lib.** { *; }

-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }

 #rxjava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#rxandroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule


#==================融云即时消息=====================
-keepattributes Exceptions,InnerClasses

-keepattributes Signature

# RongCloud SDK
-keep class io.rong.** {*;}
-keep class * implements io.rong.imlib.model.MessageContent {*;}
-dontwarn io.rong.push.**
-dontnote com.xiaomi.**
-dontnote com.google.android.gms.gcm.**
-dontnote io.rong.**

# VoIP
-keep class io.agora.rtc.** {*;}

# Location
-keep class com.amap.api.**{*;}
-keep class com.amap.api.services.**{*;}

# 红包
-keep class com.google.gson.** { *; }
-keep class com.uuhelper.Application.** {*;}
-keep class net.sourceforge.zbar.** { *; }
-keep class com.google.android.gms.** { *; }
-keep class com.alipay.** {*;}
-keep class com.jrmf360.rylib.** {*;}

-ignorewarnings

-keep class io.rong.app.DemoNotificationReceiver {*;}

-ignorewarning
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.hianalytics.android.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}

-keep class com.huawei.android.hms.agent.**{*;}
-keep class com.huawei.gamebox.plugin.gameservice.**{*;}


## ----------------------------------
##       七牛云
## ----------------------------------
-keep class com.qiniu.**{*;}
-keep class com.qiniu.**{public <init>();}
-ignorewarnings

#==================腾讯bugly=====================
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}


#==================银联支付=====================
-keep class com.unionpay.mobile.android.**



## ----------------------------------
##      rxvolley 网络请求
## ----------------------------------
-dontwarn javax.xml.stream.events.**
-dontwarn com.squareup.okhttp.**
-dontwarn com.google.android.gms.auth.GoogleAuthUtil
-dontwarn com.squareup.picasso.OkHttpLoader
-dontwarn com.squareup.picasso.OkHttpDownloader
-dontwarn org.apache.commons.logging.impl.AvalonLogger
-dontwarn org.apache.commons.logging.impl.Log4JLogger
-dontwarn org.apache.commons.logging.impl.LogKitLogger
-dontwarn org.apache.commons.logging.impl.ServletContextCleaner
-dontwarn org.apache.http.impl.auth.GGSSchemeBase
-dontwarn org.apache.http.impl.auth.KerberosScheme
-dontwarn android.net.http.SslCertificate
-dontwarn android.net.http.SslError
-dontwarn android.net.http.SslCertificate$DName
-dontwarn android.net.http.HttpResponseCache
-dontwarn org.apache.http.params.CoreConnectionPNames

-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-dontnote android.net.http.*
-dontnote org.apache.commons.codec.**
-dontnote org.apache.http.**

-dontwarn com.kymjs.rxvolley.**
-keep class com.kymjs.rxvolley.** {*;}
-dontwarn com.kymjs.common.**
-keep class com.kymjs.common.** {*;}
## ----------------------------------
##    OkHttp相关
## ----------------------------------

-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-keep interface com.squareup.okhttp3.** { *; }
-dontwarn okhttp3.internal.**
-dontwarn okio.**
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

## ----------------------------------
##        Okio相关
## ----------------------------------
-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn okio.**
## ----------------------------------
##             UIL相关
## ----------------------------------

-keep class com.nostra13.universalimageloader.** { *; }
-keepclassmembers class com.nostra13.universalimageloader.** {*;}
-dontwarn com.nostra13.universalimageloader.**
## ----------------------------------
##      SweetAlertDialog弹框
## ----------------------------------
 -keep class cn.pedant.SweetAlert.Rotate3dAnimation {
    public <init>(...);
 }
## ----------------------------------
##      图片选择器
## ----------------------------------
-keep class com.lzy.imagepicker.**{*;}
-dontwarn com.lzy.imagepicker.**
-dontwarn uk.co.senab.photoview.PhotoView.**
-keep class uk.co.senab.photoview.PhotoView.**{*;}
-keep class com.lzy.imagepicker.adapter.ImageRecyclerAdapter
## ----------------------------------
##      Retrofit 2.X 相关
## ----------------------------------
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

## ----------------------------------
##      RxJava 1.X 相关
## ----------------------------------
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
## ----------------------------------
##      Fresco 相关
## ----------------------------------
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**

## ----------------------------------
##      DataBinding 相关
## ----------------------------------
-keepclasseswithmembers class * extends android.databinding.ViewDataBinding{
    <methods>;
}

## ----------------------------------
##      配置不混淆 Demo 里的 Model
## ----------------------------------
-keep class cn.bingoogolapple.bgabanner.demo.model.**{*;}

## ----------------------------------
##      系统权限
## ----------------------------------
-dontwarn pub.devrel.easypermissions.**

## ----------------------------------
##      条件选择器
## ----------------------------------
-keep class com.bigkoo.pickerview.lib.** { *; }

## ----------------------------------
##       工具类大全
## ----------------------------------
-keep class com.blankj.utilcode.** { *; }
#-keep classmembers class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**

#==================gson==========================
-dontwarn com.google.**
-keep class com.google.gson.** {*;}

#==================protobuf======================
-dontwarn com.google.**
-keep class com.google.protobuf.** {*;}
-keep public class com.tencent.bugly.**{*;}


#==================ZBar 混淆规则======================
#-keep class net.sourceforge.zbar.** { *; }
#-keep interface net.sourceforge.zbar.** { *; }
#-dontwarn net.sourceforge.zbar.**



#==================基于IjkPlayer的视频播放器======================
-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**
-keep class com.dueeeke.videoplayer.** { *; }
-dontwarn com.dueeeke.videoplayer.**


#==================Cklibrary=====================

-keep class com.common.cklibrary.entity.** { *; }        # 保持实体类不被混淆

#==================Cklibrary=====================
#==================Cklibrary=====================
#==================Cklibrary=====================




 #代码混淆
-optimizationpasses 5          # 指定代码的压缩级别
#-dontusemixedcaseclassnames   # 是否使用大小写混合
#-dontpreverify           # 混淆时是否做预校验 # 不做预检验,preverify是proguard的四个步骤之一,Android不需要preverify,去掉这一步可以加快混淆速度
#-verbose                # 混淆时是否记录日志
## 不跳过非公共的库的类成员
#-dontskipnonpubliclibraryclassmembers
## 混淆时采用的算法(google推荐，一般不改变)
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
## 把混淆类中的方法名也混淆了
#-useuniqueclassmembernames
## 优化时允许访问并修改有修饰符的类和类的成员
#-allowaccessmodification
## 将文件来源重命名为“SourceFile”字符串
#-renamesourcefileattribute SourceFile
# 保留行号
-keepattributes SourceFile,LineNumberTable

-keep public class * extends android.app.Activity      # 保持哪些类不被混淆
-keep public class * extends android.app.Application   # 保持哪些类不被混淆
-keep public class * extends android.app.Service       # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService    # 保持哪些类不被混淆

-keep class com.yinglan.scg.entity.** { *; }        # 保持实体类不被混淆

-keepclasseswithmembernames class * {              # 保持 native 方法不被混淆
     native <methods>;
 }
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
     public <init>(android.content.Context, android.util.AttributeSet);
 }
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
     public <init>(android.content.Context, android.util.AttributeSet, int);
 }
-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
     public void *(android.view.View);
 }
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
     public static final android.os.Parcelable$Creator *;
 }
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

# 过滤R文件的混淆：
-keep class **.R$* {*;}
#-target 错误
-keepattributes InnerClasses
#-dontoptimize
-keepattributes EnclosingMethod