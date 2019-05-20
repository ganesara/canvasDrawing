package org.draw.paint.command

import org.draw.paint.canvas.ICanvas
import org.draw.paint.canvas.Position
import org.draw.paint.common.Status
import org.draw.paint.painter.FillPainter


class FillCommand(val startWidth: Int,
                  val startHeight: Int,
                  val colour:String): Command {

    override fun execute(canvas: ICanvas): Status {
        val painter = FillPainter(start = Position(x = startWidth, y = startHeight), colour = colour)
        return  painter.paint(canvas = canvas)
    }

}