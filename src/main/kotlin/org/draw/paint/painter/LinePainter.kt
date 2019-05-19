package org.draw.paint.painter

import org.draw.paint.canvas.CanvasConstants
import org.draw.paint.canvas.ICanvas
import org.draw.paint.canvas.Position
import org.draw.paint.common.Failed
import org.draw.paint.common.Status
import org.draw.paint.common.Success
import org.slf4j.LoggerFactory

class LinePainter(private val start: Position, private val end: Position) : Painter {
    companion object {
        val logger =  LoggerFactory.getLogger(LinePainter::class.java)
    }
    val affectedPositions = mutableListOf<Position>()

    override fun validate(canvas: ICanvas) : Status {

        return if( !canvas.isPositionAvailable(start)) {
            logger.error("Start Position is Unknown, Can't paint Line")
            Failed(reason = "Start Position Unknown")

        } else if( !canvas.isPositionAvailable(end)) {
            logger.error("End position Unknown. Can't paint line")
            Failed(reason = "End Position Unknown")
        } else if( (this.start.x ==  this.end.x)
                        .xor(this.end.y == this.start.y).not() ){
            logger.error("Co-ordinates are not suitable for drawing horizontal or vertical line")
            Failed("Only horizontal or vertical line are possible")
        } else {
            Success()
        }
    }

    override fun paint(canvas: ICanvas): Status {

        val validationStatus =  this.validate(canvas)

        if(validationStatus is Failed) {
            logger.error("Line painter Validation Failed")
            return validationStatus
        }

        affectedPositions += canvas.setPixelValueBetween(inclusiveStart = this.start,
            inclusiveEnd = this.end, value = CanvasConstants.LINE_DISPLAY_CHAR
        )

        logger.debug("Affected positions Count =  ${affectedPositions.size}")
        logger.trace("Affected positions : $affectedPositions")
        logger.info("Painting of LinePainter completed ")
       return Success()
    }

}