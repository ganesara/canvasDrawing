package org.draw.paint.painter

import org.draw.paint.canvas.CanvasHolder
import org.draw.paint.canvas.Position
import org.draw.paint.common.Failed
import org.junit.Assert
import org.junit.Test

class RectanglePainterTest {

    @Test
    fun testValidation() {
        val holder =  CanvasHolder()
        holder.createCanvas(width = 5, height = 7)

        var painter =  RectanglePainter(start = Position(x = 2, y = 2),
            end = Position(x = 5, y= 5)
        )
        var result =  painter.validate(canvas = holder)
        Assert.assertTrue(result is Failed)
    }
}