package org.draw.paint.command

import org.draw.paint.exception.CommandParserException
import org.slf4j.LoggerFactory
import java.lang.Exception

class CreateCanvasCommandParser: CommandParser {

    companion object {
        private val log = LoggerFactory.getLogger(CreateCanvasCommandParser::class.java)
    }

    private val cmdPattern = """[Cc]\s+(\d+)\s+(\d+)""".toRegex()

    override fun isMatched(cmd: String): Boolean = cmdPattern.matches(cmd)

    @Throws(CommandParserException::class)
    override fun parseCommand(cmd: String): Command {
        val result  =  cmdPattern.find(cmd)

        try {

            val (width , height) = result?.destructured ?: throw CommandParserException("Unable to parse Canvas Command")
            return  CreateCanvasCommand(width = width.toInt(), height = height.toInt())

        } catch (e: Exception) {
            log.error("Error Parsing the Canvas Command", e)
            throw CommandParserException(throwable = e)
        }
    }

}