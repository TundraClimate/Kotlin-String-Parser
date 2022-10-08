# Kotlin-String-Parser
This library is for running String parsing

`build.gradle.kts`
```kotlin
plugins {
    //use ShadowJar
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    //add JitPack
    maven("https://jitpack.io")
}

dependencies {
    //implementation library
    implementation("com.github.TundraClimate:Kotlin-String-Parser:${version}")
}
```

using for 
```shell
./gradlew shadowJar
```