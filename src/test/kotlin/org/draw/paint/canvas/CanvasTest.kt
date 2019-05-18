package org.draw.paint.canvas

import org.junit.After
import org.junit.Assert
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
    fun printCanvasTest() {
        val canvas =  Canvas(width = 3, height = 4)
        val result = """
            -----
            |   |
            |   |
            |   |
            |   |
            -----
        """.trimIndent()
        canvas.printScreen()
        Assert.assertEquals(outContent.toString(), result)
    }
}