package org.draw.paint.command

import org.draw.paint.exception.CommandParserException
import org.slf4j.LoggerFactory

class FillCommandParser: CommandParser {

    private  val templateRegex =  """[Bb]\s+(\d+)\s+(\d+)\s+(.)""".toRegex()

    companion object {
        private val log = LoggerFactory.getLogger(FillCommandParser::class.java)
    }

    override fun isMatched(cmd: String): Boolean = templateRegex.matches(cmd)

    override fun parseCommand(cmd: String): Command {
        val result  =  templateRegex.find(cmd)

        try {

            val (sw,sh,colour) = result?.destructured ?: throw CommandParserException("Unable to parse Canvas Command")
            return  FillCommand(startWidth = sw.toInt(),
                        startHeight = sh.toInt(),
                        colour = colour)

        } catch (e: Exception) {
            log.error("Error Parsing the Canvas Command", e)
            throw CommandParserException(throwable = e)
        }
    }
}