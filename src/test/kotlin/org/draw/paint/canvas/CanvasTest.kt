package org.draw.paint.canvas

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CanvasTest {
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
    fun testPrintCanvasTest() {
        val canvas =  Canvas(width = 3, height = 4)
        val result = """
            -----
            |   |
            |   |
            |   |
            |   |
            -----
        """.trimIndent() + "\n"
        canvas.printScreen()
        assertEquals(outContent.toString(), result)
    }


    @Test
    fun testIsPositionAvailable() {
        val canvas = Canvas(width = 3, height = 4)
        assertTrue(canvas.isPositionAvailable(Position(x=1, y = 1)))
        assertTrue(canvas.isPositionAvailable(Position(x=3, y = 4)))

        assertFalse(canvas.isPositionAvailable(Position(x=0, y = 0)))
        assertFalse(canvas.isPositionAvailable(Position(x=1, y = 0)))
        assertFalse(canvas.isPositionAvailable(Position(x=0, y = 1)))
        assertFalse(canvas.isPositionAvailable(Position(x=4, y = 0)))
        assertFalse(canvas.isPositionAvailable(Position(x=4, y = 4)))
    }

    @Test
    fun testReadAndWritePixel() {
        val canvas = Canvas(width = 3, height = 4)
        val pos =  Position(x=1, y=1)
        assertEquals(canvas.getPixelValueAt(pos = pos), CanvasConstants.DEFAULT_DISPLAY_CHAR)
        assertTrue(canvas.isPositionWritable(pos = pos))
        assertTrue(canvas.setPixelValueAt(pos = pos, value = "*"))
        assertEquals(canvas.getPixelValueAt(pos = pos), "*")
        assertFalse(canvas.setPixelValueAt(pos = pos, value = "*", overwrite = false))
        assertEquals(canvas.getPixelValueAt(pos = Position(x = 4, y = 4)), CanvasConstants.INVALID_TEXT_CHAR)
    }

    @Test
    fun testIsPositionWritable() {
        val canvas = Canvas(width = 3, height = 4)
        val pos =  Position(x=1, y=1)

        assertTrue(canvas.isPositionWritable(pos = pos))
        canvas.setPixelValueAt(pos = pos, value = "&")
        assertFalse(canvas.isPositionWritable(pos = pos))
    }


    @Test
    fun testSetPixelValueBetween() {
        val canvas =   Canvas(width = 5, height = 6)

        val result = canvas.setPixelValueBetween(inclusiveStart = Position(x = 2, y = 2),
            inclusiveEnd = Position(x = 4, y = 2), value = "*")
        val expectedResult = listOf(Position(2,2), Position(3,2), Position(4,2))
        canvas.printScreen()
        val printOut = """
            -------
            |     |
            | *** |
            |     |
            |     |
            |     |
            |     |
            -------
        """.trimIndent() + "\n"
        assertEquals(result, expectedResult)
        assertEquals(outContent.toString(), printOut)
    }

    @Test
    fun testSetPixelValueBetweenReverse() {
        val canvas =   Canvas(width = 5, height = 6)

        val result = canvas.setPixelValueBetween(inclusiveStart = Position(x = 4, y = 2),
            inclusiveEnd = Position(x = 2, y = 2), value = "*")
        val expectedResult = listOf(Position(4,2), Position(3,2), Position(2,2))
        canvas.printScreen()
        val printOut = """
            -------
            |     |
            | *** |
            |     |
            |     |
            |     |
            |     |
            -------
        """.trimIndent() + "\n"
        assertEquals(result, expectedResult)
        assertEquals(outContent.toString(), printOut)
    }

    @Test
    fun testVerticalSetPixelValueBetween() {
        val canvas =   Canvas(width = 5, height = 6)

        val result = canvas.setPixelValueBetween(inclusiveStart = Position(x = 2, y = 2),
            inclusiveEnd = Position(x = 2, y = 4), value = "*")
        val expectedResult = listOf(Position(2,2), Position(2,3), Position(2,4))
        canvas.printScreen()
        val printOut = """
            -------
            |     |
            | *   |
            | *   |
            | *   |
            |     |
            |     |
            -------
        """.trimIndent() + "\n"
        assertEquals(result, expectedResult)
        assertEquals(outContent.toString(), printOut)
    }

    @Test
    fun testVerticalSetPixelValueBetweenReverse() {
        val canvas =   Canvas(width = 5, height = 6)

        val result = canvas.setPixelValueBetween(inclusiveStart = Position(x = 2, y = 4),
            inclusiveEnd = Position(x = 2, y = 2), value = "*")
        val expectedResult = listOf(Position(2,4), Position(2,3), Position(2,2))
        canvas.printScreen()
        val printOut = """
            -------
            |     |
            | *   |
            | *   |
            | *   |
            |     |
            |     |
            -------
        """.trimIndent() + "\n"
        assertEquals(expectedResult, result)
        assertEquals(outContent.toString(), printOut)
    }

    @Test
    fun testWritableChildrenOf() {
        val canvas = Canvas(width = 5, height = 6)

        var list = canvas.writableChildrenOf(pos = Position(1,1))
        assertEquals(list, listOf(Position(1,2), Position(2, 1)))

        list = canvas.writableChildrenOf(pos = Position(3,3))
        assertEquals(list, listOf(Position(3,4), Position(3,2),
            Position(4, 3), Position(2,3)))

        canvas.setPixelValueAt(Position(3,4),"*")
        list = canvas.writableChildrenOf(pos = Position(3,3))
        assertEquals(list, listOf( Position(3,2),
            Position(4, 3), Position(2,3)))

        canvas.setPixelValueAt(Position(3,2),"*")
        list = canvas.writableChildrenOf(pos = Position(3,3))
        assertEquals(list, listOf(Position(4, 3), Position(2,3)))

        canvas.setPixelValueAt(Position(4,3),"*")
        list = canvas.writableChildrenOf(pos = Position(3,3))
        assertEquals(list, listOf(Position(2,3)))

        canvas.setPixelValueAt(Position(2,3),"*")
        list = canvas.writableChildrenOf(pos = Position(3,3))
        assertTrue(list.isEmpty())
    }
}