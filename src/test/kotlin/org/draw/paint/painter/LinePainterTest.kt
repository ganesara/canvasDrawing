package org.draw.paint.painter

import org.draw.paint.canvas.CanvasHolder
import org.draw.paint.canvas.Position
import org.draw.paint.common.Failed
import org.draw.paint.common.Success
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class LinePainterTest {
    private lateinit var outContent: ByteArrayOutputStream
    private val actualOutStream =  System.out


    @Before
    fun before() {
        outContent =  ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
    }

    @After
    fun after() {
        System.setOut(actualOutStream)
    }


    @Test
    fun testDrawNotFeasible() {
        val holder = CanvasHolder()
        holder.createCanvas(width = 5, height = 6)

        var painter = LinePainter(start = Position(x=2, y=2),
            end = Position(x=2, y = 7))
        var result = painter.paint(holder)
        assertTrue(result is Failed)

        painter = LinePainter(start = Position(x=2, y=2),
            end = Position(x=0, y = 5))
        result = painter.paint(holder)
        assertTrue(result is Failed)

        painter = LinePainter(start = Position(x=0, y=2),
            end = Position(x=0, y = 5))
        result = painter.paint(holder)
        assertTrue(result is Failed)

        painter = LinePainter(start = Position(x=2, y=1),
            end = Position(x=0, y = 5))
        result = painter.paint(holder)
        assertTrue(result is Failed)

        painter = LinePainter(start = Position(x=1, y=1),
            end = Position(x=2, y = 5))
        result = painter.paint(holder)
        assertTrue(result is Failed)

    }


    @Test
    fun testLinePrinterPaint() {
        val holder =  CanvasHolder()
        holder.createCanvas(width = 5, height = 6)
        val painter =  LinePainter(start = Position(x = 2, y = 4),  end = Position(x = 2, y = 2))
        val result = painter.paint(canvas = holder)

        val expectedResult = listOf(Position(2,4), Position(2,3), Position(2,2))
        holder.printScreen()

        val printOut = """
            -------
            |     |
            | *   |
            | *   |
            | *   |
            |     |
            |     |
            -------

        """.trimIndent()

        assertTrue(result is Success)
        assertEquals(expectedResult, painter.affectedPositions)
        assertEquals(outContent.toString(), printOut)
    }

}