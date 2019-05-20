package org.draw.paint.command

import org.draw.paint.canvas.ICanvas
import org.draw.paint.canvas.Position
import org.draw.paint.common.Status
import org.draw.paint.painter.RectanglePainter


class RectangleCommand(internal val startWidth: Int,
                       internal val startHeight: Int,
                       internal val endWidth: Int,
                       internal val endHeight: Int): Command {

    override fun execute(canvas: ICanvas): Status {
        val painter =  RectanglePainter(start = Position(x = startWidth, y = startHeight),
            end = Position(x = endWidth, y = endHeight))
        return painter.paint(canvas = canvas)
    }

}