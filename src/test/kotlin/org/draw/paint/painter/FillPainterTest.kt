package org.draw.paint.painter

import org.draw.paint.canvas.CanvasHolder
import org.draw.paint.canvas.Position
import org.draw.paint.common.Failed
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class FillPainterTest {

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
    }


    @Test
    fun testFillByBreadthFirstSearch() {

        val holder = CanvasHolder()
        holder.createCanvas(width = 6, height = 7)
        val painter =  RectanglePainter(start = Position(2,2),
            end = Position(5,6))
        painter.paint(canvas = holder)
        val fillPainter = FillPainter(start = Position(3,3),
            colour = ".")

        fillPainter.paint(canvas = holder)
        holder.printScreen()
        val positions = listOf(Position(x=3, y=3), Position(x=3, y=4),
            Position(x=4, y=3), Position(x=4, y=4), Position(x=4, y=5), Position(x=3, y=5))

        val result = """
            --------
            |      |
            | **** |
            | *..* |
            | *..* |
            | *..* |
            | **** |
            |      |
            --------
        """.trimIndent() + "\n"

        assertEquals(fillPainter.affectedPositions,positions)
        assertEquals(result , outContent.toString())
        //actualOutStream.println(outContent.toString())
    }

    @Test
    fun testFillByBreadthFirstSearchFillOutside() {

        val holder = CanvasHolder()
        holder.createCanvas(width = 6, height = 7)
        val painter =  RectanglePainter(start = Position(2,2),
            end = Position(5,6))
        painter.paint(canvas = holder)
        val fillPainter = FillPainter(start = Position(1,1),
            colour = ".")

        fillPainter.paint(canvas = holder)
        holder.printScreen()

        val result = """
            --------
            |......|
            |.****.|
            |.*  *.|
            |.*  *.|
            |.*  *.|
            |.****.|
            |......|
            --------
        """.trimIndent() + "\n"

        val positions =  listOf(Position(x=1, y=1), Position(x=1, y=2),
            Position(x=2, y=1), Position(x=3, y=1), Position(x=4, y=1),
            Position(x=5, y=1), Position(x=6, y=1), Position(x=6, y=2),
            Position(x=6, y=3), Position(x=6, y=4), Position(x=6, y=5),
            Position(x=6, y=6), Position(x=6, y=7), Position(x=5, y=7),
            Position(x=4, y=7), Position(x=3, y=7), Position(x=2, y=7),
            Position(x=1, y=7), Position(x=1, y=6), Position(x=1, y=5),
            Position(x=1, y=4), Position(x=1, y=3))

        assertEquals(fillPainter.affectedPositions,positions)
        assertEquals(result , outContent.toString())
    }

    @Test
    fun testFillByBreadthFirstSearchFillTwoBox() {

        val holder = CanvasHolder()
        holder.createCanvas(width = 10, height = 12)
        var painter =  RectanglePainter(start = Position(2,2),
            end = Position(6,7))
        painter.paint(canvas = holder)
        painter =  RectanglePainter(start = Position(3,4),
            end = Position(8,10))
        painter.paint(canvas = holder)
        val fillPainter = FillPainter(start = Position(4,5),
            colour = ".")

        fillPainter.paint(canvas = holder)
        holder.printScreen()
        //actualOutStream.println(outContent.toString())

        val result = """
            ------------
            |          |
            | *****    |
            | *   *    |
            | *******  |
            | **..* *  |
            | **..* *  |
            | ***** *  |
            |  *    *  |
            |  *    *  |
            |  ******  |
            |          |
            |          |
            ------------

        """.trimIndent()

        val positions =  listOf(Position(x=4, y=5), Position(x=4, y=6),
            Position(x=5, y=5), Position(x=5, y=6))

        assertEquals(fillPainter.affectedPositions,positions)
        assertEquals(result , outContent.toString())
    }
}