package org.draw.paint.command

import org.junit.Assert.*
import org.junit.Test

class CreateCanvasCommandParserTest {

    @Test
    fun testMath() {
        val parser =  CreateCanvasCommandParser()
        assertTrue(parser.isMatched(cmd = "C 20 4"))
        assertTrue(parser.isMatched(cmd = "c 20 4"))
        assertFalse(parser.isMatched(cmd = "d 20 4"))
    }

    @Test
    fun testParser() {
        val parser =  CreateCanvasCommandParser()
        val result =  parser.parseCommand(cmd = "C 20 4") as CreateCanvasCommand

        assertEquals(20, result.width)
        assertEquals(4, result.height)
    }
}