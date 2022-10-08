package io.github.tundraclimate

import java.lang.StringBuilder

class StringParser private constructor(private var parsingText: String) {
    private var charStack: String = ""
    private var lastChar: Char = ' '

    companion object {
        @JvmStatic
        fun from(parsingText: String): StringParser = StringParser(parsingText)

        @JvmStatic
        fun from(parsingText: StringBuilder) = StringParser(parsingText.toString())

        @JvmStatic
        fun nw(): StringParser = StringParser("")
    }

    fun clearCharStack() {
        charStack = ""
    }

    fun addCharStack(stack: String) {
        charStack += stack
    }

    private fun parsing(each: (charStack: String, lastChar: Char) -> String): String {
        var result = ""
        parsingText.forEach {
            lastChar = it
            val s = each(charStack, lastChar)
            result += s
        }
        return result
    }

    fun parse(override: String = "", parsing: StringParser.(lastChar: Char, charStack: String) -> String): String {
        if (override.isNotEmpty()) parsingText = override
        return parsing { cs, lc -> this.parsing(lc, cs) }
    }
}