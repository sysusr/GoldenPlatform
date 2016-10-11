# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/tikt/Android/Sdk/tools/proguard/proguard-android.txt
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

#==========蒲公英================#
-dontwarn com.pgyersdk.**
-keep class com.pgyersdk.** { *; }

#================百度=================#
-keep class com.baidu.** { *; }
-keep class vi.com.gdi.bgl.android.**{*;}
-dontwarn com.baidu.**

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

##表示对注解中的参数进行保留
-keepattributes *Annotation*
-optimizationpasses 5
-dontshrink
 -dontusemixedcaseclassnames
 -dontskipnonpubliclibraryclasses
 -verbose
 -dontoptimize
  -ignorewarnings
 -keepattributes *JavascriptInterface*

#==================gson==========================
-dontwarn com.google.**
-keep class com.google.gson.** {*;}

##----------------Alipay--------------------------##
 -dontwarn com.alipay.apmobilesecuritysdk.**
 -dontwarn com.alipay.tscenter.biz.**
 -keep class com.alipay.android.app.IAlixPay{*;}
 -keep class com.alipay.android.app.IAlixPay$Stub{*;}
 -keep class com.alipay.android.app.IRemoteServiceCallback{*;}
 -keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
 -keep class com.alipay.sdk.app.PayTask{ public *;}
 -keep class com.alipay.sdk.app.AuthTask{ public *;}