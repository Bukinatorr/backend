import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.21"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    kotlin("jvm") version "1.9.20"

    kotlin("plugin.spring") version "1.9.20" apply false
    kotlin("plugin.jpa") version "1.8.22" apply false
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
    group = "xyz.bukinator"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent

    repositories {
        // Required to download KtLint
        mavenCentral()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        implementation("com.google.code.gson:gson")
    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}

project(":batch") {
    dependencies {
        implementation(project(":house"))
        implementation(project(":client"))
        implementation(project(":util"))
        implementation("org.springframework.boot:spring-boot-starter-batch")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        testImplementation("org.springframework.batch:spring-batch-test")
    }
}

project(":client") {
    dependencies {
        implementation(project(":util"))
        implementation("org.springframework.boot:spring-boot-starter-webflux")
    }
}

project(":util") {
    dependencies {}
}
