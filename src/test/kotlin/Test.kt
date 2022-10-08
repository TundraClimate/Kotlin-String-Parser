import io.github.tundraclimate.StringParser
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Test {
    @Test
    fun testParse() {
        //example code
        val parser = StringParser.from("{ me: \"you\" }")
        val parsed = parser.parse { lastChar ->
            when (lastChar) {
                ' ' -> ""
                '[' -> "{"
                ']' -> "}"
                '\"' -> "\\$lastChar"
                else -> lastChar.toString()
            }
        }
        assertEquals(parsed, "{me:\\\"you\\\"}")
    }

    @Test
    fun testParse2() {
        //second example code
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
    }
}