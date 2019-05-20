package org.draw.paint.command

interface CommandParser {
    fun isMatched(cmd:String): Boolean
    fun parseCommand(cmd: String): Command
}