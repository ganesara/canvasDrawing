package org.draw.paint.command

import org.draw.paint.exception.CommandParserException

interface CommandParser {
    fun isMatched(cmd:String): Boolean
    @Throws(CommandParserException::class)
    fun parseCommand(cmd: String): Command
}