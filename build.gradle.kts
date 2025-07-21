plugins {
    kotlin("jvm") version "2.0.20"
}

group = "com.mindfulbytes"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<JavaExec>("runApp") {
    group = "application"
    description = "Runs the Main.kt application"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("com.mindfulbytes.MainKt")
}