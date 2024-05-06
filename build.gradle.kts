import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	kotlin("plugin.jpa") version "1.9.23"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly ("com.h2database:h2")
	implementation("io.mockk:mockk:1.12.0")
	implementation("com.github.kittinunf.fuel:fuel:3.0.0-alpha1")
	implementation ("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.1")
	implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
	testImplementation("org.mockito:mockito-core:3.12.4")
	testImplementation("org.mockito:mockito-core:5.11.0")
	testImplementation ("org.mockito.kotlin:mockito-kotlin:3.2.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test:6.2.3")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")


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
