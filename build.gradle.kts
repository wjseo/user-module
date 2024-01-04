import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") apply false  // Spring Boot 프로젝트에 필요한 기능을 제공하는 플러그인
    id("io.spring.dependency-management")  // spring의 종속성을 관리하는데 도움을 주는 플러그인
    id("org.jlleitschuh.gradle.ktlint") version "12.0.3"  // Kotlin 코드의 스타일을 점검하고 포맷팅 도와주는 플러그인

    kotlin("jvm") version "1.9.21"  // Kotlin jvm 버전지정
    kotlin("plugin.spring") version "1.9.21" apply false  // Kotlin에서 Spring Boot와 사용하기 위한 플러그인
    kotlin("plugin.jpa") apply false  // Kotlin에서 JPA를 지원하는 플러그인
}
// rootProject를 포함한 모든 프로젝트에 적용하는 설정
allprojects {
    group = "com.roouty"
    version = "0.0.1-SNAPSHOT"

    // 라이브러리 관리를 위한 저장소 설정
    repositories {
        mavenCentral()  // 라이브러리를 다운로드 받을 수 있는 주요 저장소
    }
}


// setting.gradle 에 include 된 하위 프로젝트에 적용하는 설정
subprojects {
    // 플러그인 적용
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    dependencyManagement {
        val springCloudDependenciesVersion: String by project
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudDependenciesVersion}")
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.getByName("bootJar") {
        enabled = false
    }

    tasks.getByName("jar") {
        enabled = true
    }

    // KotlinCompile 과정 중에 설정을 추가
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = JavaVersion.VERSION_21.toString()  // 컴파일 타겟을 Java 버전 21로 지정
        }
    }

    // JUnit5+ 실행을 설정하기 위함.
    tasks.withType<Test> {
        useJUnitPlatform()
    }
    tasks.register("prepareKotlinBuildScriptModel") {}

}
