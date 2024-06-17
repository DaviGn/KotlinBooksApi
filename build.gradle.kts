import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.9.22"
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion
	id("com.github.davidmc24.gradle.plugin.avro") version "1.5.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	maven {
		url = uri("http://packages.confluent.io/maven")
		isAllowInsecureProtocol = true
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.apache.avro:avro:1.11.2")
	implementation("io.confluent:kafka-avro-serializer:7.6.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.konform:konform:0.6.0")

	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")

	implementation("org.flywaydb:flyway-core")

	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

//avro plugin config
avro {
	stringType.set("CharSequence")
}

//set avro folder as resource
sourceSets {
	main {
		resources {
			srcDirs ("src/main/avro")
		}
		java {
			srcDirs("build/generated-main-avro-java")
		}
	}
}

tasks {
	compileKotlin.configure {
		dependsOn("generateAvroJava")
	}

	compileTestKotlin.configure {
		dependsOn("generateTestAvroJava")
	}

	withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			jvmTarget = "17"
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
