plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Serialization
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.0"
    //hilt
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    ///Auth
    id("com.google.gms.google-services")

    //Room Database
    id("androidx.room")
}

android {
    namespace = "com.example.ecommerce"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.ecommerce"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

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
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
            optIn.add("kotlin.RequiresOptIn")
        }
    }

    buildFeatures {
        compose = true
    }

    room {
        schemaDirectory("$projectDir/schemas")
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
    implementation(libs.androidx.credentials)
    implementation(libs.googleid)
    implementation(libs.androidx.ui.text.google.fonts)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //navigation
    implementation(libs.androidx.navigation.compose)

    //for more icons
    implementation(libs.androidx.material.icons.extended)
    // Hilt dependency
    implementation(libs.hilt.android.v2562)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose) // or latest
    //Serialization
    implementation(libs.kotlinx.serialization.json)
    //Coil for images
    implementation(libs.coil.compose)
    // Firebase Auth (BOM manages versions)
    implementation(platform(libs.firebase.bom))
    implementation(libs.google.firebase.auth)
    implementation(libs.androidx.credentials.play.services.auth)
    // Google Sign-In
    implementation(libs.play.services.auth)
    // Coroutines + Play Services await()
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)
    // Preferences Data Store
    implementation(libs.androidx.datastore.preferences)
    //Ktor
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    // coil
    implementation("io.coil-kt.coil3:coil-compose:3.0.3")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.3")

    //Room Database
    val roomVersion = "2.8.2"

    implementation("androidx.room:room-runtime:$roomVersion")

    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    // See Add the KSP plugin to your project
    ksp("androidx.room:room-compiler:$roomVersion")

    // If this project only uses a Java source, use the Java annotationProcessor
    // No additional plugins are necessary
   // annotationProcessor("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")


    // optional - Guava support for Room, including Optional and ListenableFuture
   // implementation("androidx.room:room-guava:$room_version")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$roomVersion")

    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$roomVersion")


}