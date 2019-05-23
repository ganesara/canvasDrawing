package org.draw.paint.painter

import org.draw.paint.canvas.ICanvas
import org.draw.paint.common.Failed
import org.draw.paint.common.Status
import org.draw.paint.common.Success

class CanvasPainter(private val width: Int, private  val height: Int) : Painter {

    override fun paint(canvas: ICanvas): Status {

        return  if (width == 0 || height == 0) {
            Failed(reason = "Can't create canvas for $width and $height")
        } else {
            val success = canvas.createCanvas(width = width, height = height)
            if (success) Success() else Failed(reason = "Canvas not created")
        }
    }

}