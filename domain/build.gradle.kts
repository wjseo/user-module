plugins {
    kotlin("jvm")
}

group = "com.roouty"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")// MariaDB
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}