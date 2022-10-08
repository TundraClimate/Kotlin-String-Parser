package io.github.tundraclimate

import java.lang.StringBuilder

class StringParser private constructor(private var parsingText: String) {
    private var charStack: String = ""
    private var lastChar: Char = ' '

    companion object {
        //From String
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

    fun getCharStack() = charStack

    //Parsing String
    private fun parsing(each: (lastChar: Char) -> String): String {
        var result = ""
        parsingText.forEach {
            lastChar = it
            val s = each(lastChar)
            result += s
        }
        return result
    }

    //Wrapped Parser
    fun parse(override: String = "", parsing: StringParser.(lastChar: Char) -> String): String {
        if (override.isNotEmpty()) parsingText = override
        return parsing { lc -> this.parsing(lc) }
    }
}