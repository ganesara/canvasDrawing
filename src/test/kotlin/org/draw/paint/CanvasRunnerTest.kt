package org.draw.paint

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CanvasRunnerTest {

    private val output = """
        ----------------------
        |                    |
        |                    |
        |                    |
        |                    |
        ----------------------
        Done.
        ----------------------
        |                    |
        |******              |
        |                    |
        |                    |
        ----------------------
        Done.
        ----------------------
        |                    |
        |******              |
        |     *              |
        |     *              |
        ----------------------
        Done.
        ----------------------
        |             *****  |
        |******       *   *  |
        |     *       *****  |
        |     *              |
        ----------------------
        Done.
        ----------------------
        |ooooooooooooo*****oo|
        |******ooooooo*   *oo|
        |     *ooooooo*****oo|
        |     *oooooooooooooo|
        ----------------------
        Done.
    """.trimIndent() + System.lineSeparator()

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
    fun testExecuteCommand() {
        val runner =  CanvasRunner()
        runner.executeCommand(command = "C 20 4")
        runner.executeCommand(command = "L 1 2 6 2")
        runner.executeCommand(command = "L 6 3 6 4")
        runner.executeCommand(command = "R 14 1 18 3")
        runner.executeCommand(command = "B 10 3 o")

        assertEquals(output.replace(System.lineSeparator(), "\n"),
            outContent.toString().replace(System.lineSeparator(), "\n"))
    }
}