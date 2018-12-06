import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.10"
}

group = "com.codav"
version = "1.0-SNAPSHOT"
description = """
    Proyecto para persistencia de personas

    Nombre del Proyecto: ${project.name}"""


repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    // Use the Kotlin JDK 8 standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.exposed:exposed:0.11.2")
    implementation("mysql:mysql-connector-java:8.0.13")

    // Use the Kotlin test library
    //testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration
    //testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Wrapper> {
    gradleVersion = "5.0"
}