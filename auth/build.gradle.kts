plugins {
    id("java")
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

val springBootVersion = "3.2.3"
val springCloudVersion = "4.1.0"

dependencies {
    // Eureka Client
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:$springCloudVersion")

    implementation("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")

    // DB
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.postgresql:postgresql:42.7.3")

    // JWT
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
}
