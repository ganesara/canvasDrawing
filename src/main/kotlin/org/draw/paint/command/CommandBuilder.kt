package org.draw.paint.command

import org.draw.paint.canvas.ICanvas
import org.draw.paint.common.Failed
import org.draw.paint.common.Status
import org.draw.paint.exception.CommandParserException
import org.slf4j.LoggerFactory

class CommandBuilder {

    companion object {
        private val log = LoggerFactory.getLogger(CommandBuilder::class.java)
    }

    private var command: String = ""

    private val commandParsers
            = arrayOf(CreateCanvasCommandParser(),
        FillCommandParser(), LineCommandParser(), RectangleCommandParser(), FillCommandParser(), QuitCommandParser())

    fun setStringCommand(string: String):CommandBuilder {
        command =  string.trim()
        return  this
    }

    fun build():Command {

        var cmd: Command? = null
        commandParsers.forEach {p ->
            if (p.isMatched(command)) {

                try {

                    cmd = p.parseCommand(command)
                    return@forEach

                } catch (e: CommandParserException) {
                    log.error("Error parsing the command $command", e)

                    cmd = object : Command {
                        override fun execute(canvas: ICanvas): Status {
                            return Failed("Exception in command parsing : ${e.message}")
                        }
                    }
                }
            }
        }

        if(cmd == null) {
            log.error("Command not found for : $command")
        }

        return cmd ?: object : Command {
            override fun execute(canvas: ICanvas): Status {
               return Failed("Command Not Found")
            }
        }
    }
}