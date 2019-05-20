package org.draw.paint.painter

import org.draw.paint.canvas.ICanvas
import org.draw.paint.canvas.Position
import org.draw.paint.common.Failed
import org.draw.paint.common.Status
import org.draw.paint.common.Success
import org.slf4j.LoggerFactory

class FillPainter(private val start:Position, private val colour: String): Painter {

    val affectedPositions = mutableListOf<Position>()

    companion object {
        private val log = LoggerFactory.getLogger(FillPainter::class.java)
    }

    override fun validate(canvas: ICanvas): Status {

        return if( !canvas.isPositionAvailable(start) ) {
            Failed("Position Not available")
        } else if(!canvas.isPositionWritable(start)){
            Failed("Position Filled already")
        } else {
            Success()
        }
    }


    override fun paint(canvas: ICanvas): Status {
        var status = this.validate(canvas = canvas)

        if(status is Failed) {
            return status
        }

        status = this.fillByBreadthFirstSearch(canvas = canvas)

        log.debug("Affected positions Count =  ${affectedPositions.size}")
        log.trace("Affected positions : $affectedPositions")
        log.info("Painting of LinePainter completed ")
        return status
    }


    private fun fillByBreadthFirstSearch(canvas: ICanvas) : Success {

        var temp = start
        val trackList =  mutableListOf<Position>()
        canvas.setPixelValueAt(pos = temp, value = colour, overwrite = false)
        affectedPositions.add(temp)

        var children =  canvas.writableChildrenOf(pos = temp)

        children.forEach { p ->
            canvas.setPixelValueAt(pos = p, value = colour, overwrite = false)
            affectedPositions.add(p)
            trackList.add(p)
        }

        while(trackList.size > 0) {
            temp =  trackList.last()
            trackList.removeAt(trackList.size - 1)

            children =  canvas.writableChildrenOf(pos = temp)

            children.forEach { p ->
                canvas.setPixelValueAt(pos = p, value = colour, overwrite = false)
                affectedPositions.add(p)
                trackList.add(p)
            }
        }

        return Success()
    }
}
