plugins {
    kotlin("jvm") version "1.3.71"
}

group = "dev.gressier"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "${JavaVersion.VERSION_11}"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "${JavaVersion.VERSION_11}"
    }
}
