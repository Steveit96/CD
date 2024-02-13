plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.androidHilt)
}

android {
    namespace = "com.feature.livia"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.feature.livia"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes.add("META-INF/gradle/incremental.annotation.processors")
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    // Dagger and Hilt
    implementation(libs.daggerHiltAndroid)
    implementation(libs.daggerCore)
    implementation(libs.daggerAndroid)
    implementation(libs.daggerCompiler)
    kapt(libs.daggerHiltCompiler)
    kapt(libs.daggerHiltAndroidCompiler)
    implementation(libs.daggerAndroidProcessor)
    implementation(libs.daggerAndroidSupport)

    // Retrofit
    implementation(libs.retrofitCore)
    implementation(libs.retrofitGsonConverter)

    implementation(libs.lifeCycleExtensions)
    implementation(libs.fragmentKtx)

    //RoomDB
    implementation(libs.roomCompiler)
    implementation(libs.roomRunTime)


    //interceptor
    implementation(libs.interceptor)

    implementation(libs.lifeCycleViewModel)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}