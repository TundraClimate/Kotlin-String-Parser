import io.github.tundraclimate.StringParser
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Test {
    @Test
    fun testParse() {
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
        assertEquals(parsed, "{me:\\\"you\\\"}")
    }
}