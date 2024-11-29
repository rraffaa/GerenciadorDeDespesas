plugins {
    id("com.android.application")
    kotlin("android") version "1.9.10"
}

android {
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.gerenciamento_despesas"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    namespace = "com.example.gerenciamento_despesas"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Dependências do AndroidX
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2024.11.00"))
    implementation("androidx.compose.ui:ui:1.7.5")
    implementation("androidx.compose.material:material:1.7.5")  // Material Design 2 (estável)
    implementation(kotlin("stdlib", version = "1.9.10"))
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("androidx.compose.material3:material3-android:1.3.1")

    // Dependências para testes unitários
    testImplementation("org.mockito:mockito-core:5.0.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-inline:5.0.0")

    // Dependências para testes de Instrumentação no Android
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.11.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.7.5")

    // Dependências de depuração
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.5")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.7.5")
}
