plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("kotlin-parcelize")
	id("com.google.devtools.ksp")
}

android {
	namespace = "com.example.ss1_githubuser"
	compileSdk = 34

	defaultConfig {

		buildConfigField("String", "GITHUB_TOKEN", "\"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\""
		)
		applicationId = "com.example.ss1_githubuser"
		minSdk = 24
		//noinspection OldTargetApi
		targetSdk = 33
		versionCode = 1
		versionName = "1.0"
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}

	buildFeatures{
		viewBinding = true
		buildConfig = true
	}
}

dependencies {

	implementation("androidx.core:core-ktx:1.12.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.9.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
	implementation("androidx.navigation:navigation-ui-ktx:2.7.3")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
	implementation("com.github.bumptech.glide:glide:4.16.0")
	implementation("com.loopj.android:android-async-http:1.4.11")
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
	implementation("androidx.room:room-common:2.5.2")
	implementation("androidx.room:room-ktx:2.5.2")
    implementation("androidx.datastore:datastore-core:1.0.0")
	implementation("androidx.datastore:datastore-preferences:1.0.0")
	implementation("com.airbnb.android:lottie:6.1.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
	implementation ("androidx.fragment:fragment-ktx:1.6.1")
	implementation("androidx.datastore:datastore-preferences:1.0.0")
	implementation("androidx.room:room-runtime:2.5.2")
	ksp("androidx.room:room-compiler:2.5.2")
	implementation("androidx.arch.core:core-testing:2.2.0")
	implementation("org.mockito:mockito-core:3.12.4")
	testImplementation("org.mockito:mockito-inline:3.12.4")
    testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}