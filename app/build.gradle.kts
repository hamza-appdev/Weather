import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    //serlization plugin
    kotlin("plugin.serialization") version "2.2.0"

    //hilt
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")

}
    val secretprop = Properties()
    val secretfile = File(rootDir,"secret.properties")
    if(secretfile.exists() && secretfile.isFile){
    secretfile.inputStream().use { stream->
        secretprop.load(stream)
    }
    }else{
        print("⚠️ secret.properties is not found")
    }

    val apikey: String= secretprop.getProperty("API_KEY")?:""

android {
    namespace = "com.example.waetherapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.waetherapp"
        minSdk = 31
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "API_KEY", "\"$apikey\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig=true
        resValues=true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


//    Material 2:
    implementation("androidx.compose.material:material:1.9.2")
//    Material 3:
    implementation("androidx.compose.material3:material3:1.4.0")

//    lottie animation
    implementation("com.airbnb.android:lottie-compose:6.6.9")

//    Viewmodel
    implementation("androidx.activity:activity-compose:1.7.1")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

//    Extended icon
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    //ktor
    implementation("io.ktor:ktor-client-core:3.2.1")
    implementation("io.ktor:ktor-client-cio:3.2.1")
    implementation("io.ktor:ktor-client-content-negotiation:3.2.1")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.2.1")
    implementation("io.ktor:ktor-client-logging:3.2.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")

    //hilt

    implementation("com.google.dagger:hilt-android:2.57.1")
    ksp("com.google.dagger:hilt-android-compiler:2.57.1")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")


}