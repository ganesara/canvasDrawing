package org.draw.paint.painter

import org.draw.paint.canvas.ICanvas
import org.draw.paint.canvas.Position
import org.draw.paint.common.Failed
import org.draw.paint.common.Status
import org.draw.paint.common.Success
import org.slf4j.LoggerFactory

class FillPainter(private val start:Position, private val colour: String): Painter {

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}