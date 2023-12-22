plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":house"))
    implementation(project(":client"))

    implementation ("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    testImplementation("org.springframework.batch:spring-batch-test")
}
