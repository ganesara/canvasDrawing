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

class BoxPainterTest {

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
    fun testValidate() {
        val holder =  CanvasHolder()
        holder.createCanvas(width = 5, height = 7)

        var painter =  BoxPainter(start = Position(x = 2, y = 3),
            end = Position(x = 5, y= 7))
        var result =  painter.validate(canvas = holder)
        assertTrue(result is Success)

        painter =  BoxPainter(start = Position(x = 6, y = 3),
            end = Position(x = 5, y= 7))
        result =  painter.validate(canvas = holder)
        assertTrue(result is Failed)

        painter =  BoxPainter(start = Position(x = 2, y = 3),
            end = Position(x = 5, y= 8))
        result =  painter.validate(canvas = holder)
        assertTrue(result is Failed)

        painter =  BoxPainter(start = Position(x = 2, y = 7),
            end = Position(x = 5, y= 7))
        result =  painter.validate(canvas = holder)
        assertTrue(result is Failed)

        painter =  BoxPainter(start = Position(x = 2, y = 3),
            end = Position(x = 2, y= 7))
        result =  painter.validate(canvas = holder)
        assertTrue(result is Failed)
    }

    @Test
    fun testBoxPaint() {
        val holder =  CanvasHolder()
        holder.createCanvas(width = 6 , height = 7)
        val painter =  BoxPainter(start = Position(x= 2, y = 2),
            end = Position(x= 5, y=5)
        )
        val positions = listOf(Position(x=2, y=2), Position(x=2, y=3),
            Position(x=2, y=4), Position(x=2, y=5), Position(x=3, y=5),
            Position(x=4, y=5), Position(x=5, y=5), Position(x=5, y=4),
            Position(x=5, y=3), Position(x=5, y=2), Position(x=4, y=2),
            Position(x=3, y=2)
        )
        val box = """
            --------
            |      |
            | **** |
            | *  * |
            | *  * |
            | **** |
            |      |
            |      |
            --------

        """.trimIndent()

        val status = painter.paint(holder)
        assertTrue(status is Success)
        holder.printScreen()
        assertTrue(painter.affectedPosition.containsAll(positions))
//        assertEquals(positions, painter.affectedPosition)
        assertEquals(box, outContent.toString())
    }
}