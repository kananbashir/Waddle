# 1) Keep Kotlin metadata & all annotations needed at runtime
-keep class kotlin.Metadata { *; }
-keepattributes Signature,*Annotation*,InnerClasses,EnclosingMethod,
               RuntimeVisibleAnnotations,RuntimeInvisibleAnnotations,
               RuntimeVisibleParameterAnnotations,RuntimeInvisibleParameterAnnotations

# 2) Keep Koin’s core so DI definitions still resolve
-keep class org.koin.** { *; }
-keepclassmembers class org.koin.** { *; }

# 3) Keep DI modules if you’ve put them in a specific package
-keep class com.waddleup.core.di.DataModuleKt { *; }
-keep class com.waddleup.core.di.NetworkModuleKt { *; }
-keep class com.waddleup.waddle.di.MainModuleKt { *; }
-keep class com.waddleup.auth.di.AuthModuleKt { *; }

# 4) Retrofit 2 – preserve HTTP interfaces & annotations
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature,Exceptions,*Annotation*
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# 5) OkHttp – avoid warnings & keep core classes
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }

# 6) Keep bottom nav items
-keep class com.waddleup.navigation.** { *; }
-keepnames class com.waddleup.navigation.**