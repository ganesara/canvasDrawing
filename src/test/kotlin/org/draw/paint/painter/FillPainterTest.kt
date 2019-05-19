package org.draw.paint.painter

import org.draw.paint.canvas.CanvasHolder
import org.draw.paint.canvas.Position
import org.draw.paint.common.Failed
import org.junit.Assert.assertTrue
import org.junit.Test

class FillPainterTest {

    @Test
    fun testFillValidation() {
        val holder =  CanvasHolder()
        holder.createCanvas(width = 6, height = 7)
        val linePainter =  LinePainter(start = Position(2, 2),
            end = Position(5,2)
        )
        linePainter.paint(holder)
        var fillPainter = FillPainter(start = Position(2, 8), colour = ".")
        var status =  fillPainter.validate(holder)
        assertTrue(status is Failed)

        fillPainter = FillPainter(start = Position(3, 2), colour = ".")
        status =  fillPainter.validate(holder)

        assertTrue(status is Failed)
        holder.printScreen()
    }

}