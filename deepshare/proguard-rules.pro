# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:/work/android-sdk-windows/tools/proguard/proguard-android.txt
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

-ignorewarnings

-renamesourcefileattribute SourceFile
-keepattributes Exceptions,InnerClasses,Signature,SourceFile,LineNumberTable,EnclosingMethod
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

-keep class com.singulariti.deepshare.DeepShare { *; }
-keep class com.singulariti.deepshare.listeners.DSInappDataListener { *; }
-keep class com.singulariti.deepshare.listeners.NewUsageFromMeListener { *; }
-keep class com.singulariti.deepshare.listeners.DSFailListener { *; }