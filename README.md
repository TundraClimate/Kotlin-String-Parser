# Kotlin-String-Parser
This library is for running String parsing

## How to Use
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

### Code Examples

---

```kotlin
/*source*/
val parser = StringParser.from("{ me: \"you\" }")
val parsed = parser.parse { lastChar, _ ->
    when (lastChar) {
        ' ' -> ""
        '[' -> "{"
        ']' -> "}"
        '\"' -> "\\$lastChar"
        else -> lastChar.toString()
    }
}

println(parsed)
```

```
output:
{me:\"you\"}
```
---

```kotlin
/*source*/
val parser = StringParser.from("\"{<--String-->}\" -- {<--Raw-->}")
var isStr = false
val parsed = parser.parse { lastChar ->
    if (isStr) {
        addCharStack(lastChar.toString())
        if (lastChar == '\"') {
            isStr = false
            getCharStack()
        } else ""
    } else when (lastChar) {
        '{' -> "-:"
        '}' -> ":-"
        '\"' -> {
            isStr = true
            addCharStack(lastChar.toString())
            ""
        }

        else -> lastChar.toString()
    }
}
println(parsed)
```

```
output:
"{<--String-->}" -- -:<--Raw-->:-
```