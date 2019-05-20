package org.draw.paint.command

import org.draw.paint.canvas.ICanvas
import org.draw.paint.common.Status
import kotlin.system.exitProcess


class QuitCommand: Command {

    override fun execute(canvas: ICanvas): Status {
        exitProcess(status = 0)
    }

}