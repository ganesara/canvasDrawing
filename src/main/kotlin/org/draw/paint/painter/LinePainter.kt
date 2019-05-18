package org.draw.paint.painter

import org.draw.paint.canvas.Canvas
import org.draw.paint.canvas.Position

class LinePainter(val start: Position, val end: Position) : Painter {

    val affectedPosition = emptyList<Position>()

    fun checkFeasibility() : Boolean {
        return false
    }

    override fun draw(canvas: Canvas): Boolean {
       return false
    }

}