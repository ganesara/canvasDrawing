package org.draw.paint.canvas

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream


class CanvasHolderTest {
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
    fun testCreateCanvas() {
        val holder =  CanvasHolder()
        holder.createCanvas(width = 3, height = 2)
        val expected = """
            -----
            |   |
            |   |
            -----
        """.trimIndent()
        holder.printScreen()
        assertEquals(outContent.toString(), expected)
    }

    @Test
    fun testReadAndWritePixel() {
        val holder =  CanvasHolder()
        holder.createCanvas(width = 3, height = 4)
        val pos =  Position(x=1, y=1)
        assertEquals(holder.getPixelValueAt(pos = pos), CanvasConstants.DEFAULT_DISPLAY_CHAR)
        assertTrue(holder.isPositionWritable(pos = pos))
        assertTrue(holder.setPixelValueAt(pos = pos, value = "*"))
        assertEquals(holder.getPixelValueAt(pos = pos), "*")
        assertFalse(holder.setPixelValueAt(pos = pos, value = "*", overwrite = false))
        assertEquals(holder.getPixelValueAt(pos = Position(x = 4, y = 4)), CanvasConstants.INVALID_TEXT_CHAR)
    }

}