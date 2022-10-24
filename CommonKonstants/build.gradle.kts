plugins {
    kotlin("jvm") version "1.7.20"
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.9.1")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.7.20")
}
