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
        holder.print()
        assertEquals(outContent.toString(), expected)
    }

}