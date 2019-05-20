package org.draw.paint.painter

import org.draw.paint.canvas.ICanvas
import org.draw.paint.canvas.Position
import org.draw.paint.common.Failed
import org.draw.paint.common.Status
import org.draw.paint.common.Success
import org.slf4j.LoggerFactory
import kotlin.math.abs


class RectanglePainter(start: Position, end: Position)
    : BoxPainter(start = start, end = end) {

    companion object {
        val log =  LoggerFactory.getLogger(RectanglePainter::class.java)
    }

    override fun validate(canvas: ICanvas): Status {
        val status =  super.validate(canvas)

        return if (status is Failed) {
             status

        } else if(abs(start.x - end.x) == abs(start.y - end.y)){
            log.error("Can't form Rectangle square co-ordinates")
            Failed("Position forms Square not rectangle")

        } else {
            Success()
        }
    }
} 