package org.draw.paint.command

import org.draw.paint.canvas.ICanvas
import org.draw.paint.common.Failed
import org.draw.paint.common.Status
import java.util.*

class CommandBuilder {

    private var command: String = ""

    private val commandParsers
            = arrayOf(CreateCanvasCommandParser(),
        FillCommandParser(), LineCommandParser(), RectangleCommandParser(), FillCommandParser(), QuitCommandParser())

    fun setStringCommand(string: String):CommandBuilder {
        command =  string
        return  this
    }

    fun build():Command {

        var cmd: Command? = null
        commandParsers.forEach {p ->
            if (p.isMatched(command)) {
                cmd = p.parseCommand(command)
                return@forEach
            }
        }

        return cmd ?: object : Command {
            override fun execute(canvas: ICanvas): Status {
               return Failed("Command Not Found")
            }
        }
    }
}