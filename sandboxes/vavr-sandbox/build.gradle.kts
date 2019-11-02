plugins {
    java
}

group = "com.thisisscala"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Vavr
    compile("io.vavr", "vavr", "1.0.0-alpha-3")

    // Lombok
    compileOnly("org.projectlombok", "lombok", "1.18.10")
    testCompileOnly("org.projectlombok", "lombok", "1.18.10")
    annotationProcessor("org.projectlombok", "lombok", "1.18.10")
    testAnnotationProcessor("org.projectlombok", "lombok", "1.18.10")

    // JUnit 5
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.0-M1")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.6.0-M1")
    testRuntime("org.junit.platform", "junit-platform-console", "1.6.0-M1")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}
