package org.draw.paint.command

import org.draw.paint.exception.CommandParserException
import org.slf4j.LoggerFactory

class QuitCommandParser: CommandParser {

    private  val templateRegex =  """[Qq]""".toRegex()

    companion object {
        private val log = LoggerFactory.getLogger(QuitCommandParser::class.java)
    }

    override fun isMatched(cmd: String): Boolean = templateRegex.matches(cmd)

    override fun parseCommand(cmd: String): Command = QuitCommand()

}