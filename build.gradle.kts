import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "io.github.tundraclimate"
version = "0.0.1"

val ver = version as String

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks {
    test {
        useJUnitPlatform()
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")
        archiveVersion.set(ver)
    }
}

java {
    withSourcesJar()
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
        }
    }
}

application {
    mainClass.set("$group.StringParser")
}