package org.draw.paint.painter

import org.draw.paint.canvas.CanvasConstants
import org.draw.paint.canvas.ICanvas
import org.draw.paint.canvas.Position
import org.draw.paint.common.Failed
import org.draw.paint.common.Status
import org.draw.paint.common.Success
import org.slf4j.LoggerFactory

open class BoxPainter(protected val start: Position, protected val end: Position) : Painter  {

    companion object {
        val log =  LoggerFactory.getLogger(BoxPainter::class.java)
    }

    val affectedPosition =  LinkedHashSet<Position>()

    override fun validate(canvas: ICanvas): Status {

        return if(!canvas.isPositionAvailable(start)) {

            log.error("Start Position is out of Canvas focus")
             Failed("Start Position is not available")

        } else if(!canvas.isPositionAvailable(this.end)){
            log.error("End Position is out of Canvas focus")
            Failed("End Position is not available")

        } else if(this.start.x == this.end.x){

            log.error("Column Positions are Same. can't draw Box")
            Failed("Column Positions are Same. can't draw Box")

        } else if(this.start.y == this.end.y){

            log.error("Row Positions are Same. can't draw Box")
            Failed("Row Positions are Same. can't draw Box")

        } else {
            Success()
        }
    }

    override fun paint(canvas: ICanvas): Status {
        val validationStatus =  this.validate(canvas= canvas)

        if(validationStatus is Failed) {
            log.error("Box painter Validation Failed")
            return validationStatus
        }

        val positions =  mutableListOf<Position>()

        positions += canvas.setPixelValueBetween(inclusiveStart = start,
            inclusiveEnd = Position(x = start.x, y = end.y),
            value = CanvasConstants.LINE_DISPLAY_CHAR)

        positions += canvas.setPixelValueBetween(inclusiveStart = Position(x = start.x, y = end.y),
            inclusiveEnd = end,
            value = CanvasConstants.LINE_DISPLAY_CHAR)

        positions += canvas.setPixelValueBetween(inclusiveStart = end,
            inclusiveEnd = Position(x = end.x, y = start.y),
            value = CanvasConstants.LINE_DISPLAY_CHAR)

        positions += canvas.setPixelValueBetween(inclusiveStart = Position(x = end.x, y = start.y),
            inclusiveEnd = start,
            value = CanvasConstants.LINE_DISPLAY_CHAR)

        affectedPosition.addAll(positions)

        log.debug("Affected positions Count =  ${affectedPosition.size}")
        log.trace("Affected positions : $affectedPosition")
        log.info("Painting of LinePainter completed ")
        return Success()
    }



}