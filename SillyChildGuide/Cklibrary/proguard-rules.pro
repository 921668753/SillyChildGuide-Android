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


  -dontusemixedcaseclassnames
    -dontshrink
    -dontoptimize
    -keep public class javax.**
    -keep public class android.webkit.**
    -dontwarn android.support.v4.**
    -keepattributes Exceptions,InnerClasses,Signature
    -keepattributes *Annotation*
    -keepattributes SourceFile,LineNumberTable
    -keep class com.android.dingtalk.share.ddsharemodule.** { *; }

    -keep public class com.linkedin.android.mobilesdk.R$*{
    public static final int *;
        }
    -keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
    }

-keepnames class * implements android.os.Parcelable {
public static final ** CREATOR;
}

-keep class com.linkedin.** { *; }
-keepattributes Signature



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

-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**

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



#==================gson==========================
-dontwarn com.google.**
-keep class com.google.gson.** {*;}

#代码混淆
 -optimizationpasses 5          # 指定代码的压缩级别
 -dontusemixedcaseclassnames   # 是否使用大小写混合
 -dontpreverify           # 混淆时是否做预校验
 -verbose                # 混淆时是否记录日志

 -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

 -keep public class * extends android.app.Activity      # 保持哪些类不被混淆
 -keep public class * extends android.app.Application   # 保持哪些类不被混淆
 -keep public class * extends android.app.Service       # 保持哪些类不被混淆
 -keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
 -keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
 -keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
 -keep public class * extends android.preference.Preference        # 保持哪些类不被混淆

-keep class com.common.cklibrary.entity.** { *; }        # 保持实体类不被混淆
-dontwarn com.common.cklibrary.common.**
-keep class com.common.cklibrary.common.** { *; }        # 保持实体类不被混淆
-keep class com.common.cklibrary.utils.myview.** { *; }

 -keepclasseswithmembernames class * {              # 保持 native 方法不被混淆
     native <methods>;
 }

 #不混淆CREATOR常量, 有人定义CREATOR时不写final关键字 -_-!!
 -keep class * implements android.os.Parcelable {
     public static final android.os.Parcelable$Creator *;
     public static android.os.Parcelable$Creator *;
 }

 -keep class butterknife.** { *; }
 -dontwarn butterknife.internal.**
 -keep class **$$ViewBinder { *; }
 -keep class **$$ViewHolder { *; }

 -keepclasseswithmembernames class * {
     @butterknife.* <fields>;
 }
 -keepclasseswithmembernames class * {
     @butterknife.* <methods>;
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

-keep class android.support.v4.app.** { *; }

-keep interface android.support.v4.app.** { *; }

-keep class com.unionpay.mobile.android.**{*;}
-keep class com.newrelic.** { *; }
-dontwarn com.newrelic.**
-keepattributes EnclosingMethod, Exceptions, Signature, InnerClasses
-optimizations optimization_filter

# 过滤R文件的混淆：
-keep class **.R$* {*;}
#-target #错误
-dontoptimize
-keep class android.webkit.** { *; }

-keep interface android.webkit.** { *; }
-dontwarn android.webkit.**
-ignorewarnings                # 抑制警告
