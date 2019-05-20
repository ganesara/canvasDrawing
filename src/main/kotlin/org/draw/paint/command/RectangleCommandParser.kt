package org.draw.paint.command

import org.draw.paint.exception.CommandParserException
import org.slf4j.LoggerFactory

class RectangleCommandParser: CommandParser {

    private  val templateRegex =  """[Rr]\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)""".toRegex()

    companion object {
        private val log = LoggerFactory.getLogger(RectangleCommandParser::class.java)
    }

    override fun isMatched(cmd: String): Boolean = templateRegex.matches(cmd)

    override fun parseCommand(cmd: String): Command {
        val result  =  templateRegex.find(cmd)

        try {

            val (sw,sh,ew,eh) = result?.destructured ?: throw CommandParserException("Unable to parse Canvas Command")
            return  RectangleCommand(startWidth = sw.toInt(),
                        startHeight = sh.toInt(),
                        endWidth = ew.toInt(),
                        endHeight = eh.toInt())

        } catch (e: Exception) {
            log.error("Error Parsing the Canvas Command", e)
            throw CommandParserException(throwable = e)
        }
    }
}