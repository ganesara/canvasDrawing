package org.draw.paint.command

import org.draw.paint.canvas.ICanvas
import org.draw.paint.canvas.Position
import org.draw.paint.common.Status
import org.draw.paint.painter.LinePainter


class LineCommand(val startWidth: Int,
                  val startHeight: Int,
                  val endWidth: Int,
                  val endHeight: Int): Command {

    override fun execute(canvas: ICanvas): Status {
        val painter =  LinePainter(start = Position(x = startWidth, y= startHeight),
                                     end = Position(x = endWidth, y = endHeight))
        return painter.paint(canvas = canvas)
    }

}