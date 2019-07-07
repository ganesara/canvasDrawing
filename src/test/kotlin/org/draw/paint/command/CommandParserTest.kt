package org.draw.paint.command

import org.draw.paint.painter.CanvasPainter
import org.draw.paint.painter.FillPainter
import org.draw.paint.painter.LinePainter
import org.draw.paint.painter.RectanglePainter
import org.junit.Assert.*
import org.junit.Test


class CommandParserTest {


    @Test
    fun testCreateCanvasPainter() {
        val painter =  CommandParser.parse(cmd = "C 20 4")

        assertTrue(painter is CanvasPainter)
    }

    @Test
    fun testCreateFillCommandPainter() {
        val result =  CommandParser.parse(cmd = "b 1 2 *")
        assertTrue(result is FillPainter)
    }

    @Test
    fun testCreateLineCommandPainter() {

        val result =  CommandParser.parse(cmd = "L 1 2 6 2")
        assertTrue(result is LinePainter)
    }


    @Test
    fun testRectangleCommandPainter() {

        val result =  CommandParser.parse(cmd = "r 1 2 6 2")
        assertTrue(result is RectanglePainter)

    }
}