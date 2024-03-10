plugins {
}

dependencies {
    implementation(project(":client"))
    implementation(project(":util"))
    implementation("mysql:mysql-connector-java:8.0.23")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.google.code.gson:gson")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}
