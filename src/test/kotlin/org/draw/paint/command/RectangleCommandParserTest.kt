package org.draw.paint.command

import org.junit.Assert.*
import org.junit.Test

class RectangleCommandParserTest {
    @Test
    fun testMath() {
        val parser =  RectangleCommandParser()

        assertTrue(parser.isMatched(cmd = "R 1 2 6 2"))
        assertTrue(parser.isMatched(cmd = "r 1 2 6 2"))
        assertFalse(parser.isMatched(cmd = "dssd"))
    }

    @Test
    fun testParser() {
        val parser =  RectangleCommandParser()
        val result =  parser.parseCommand(cmd = "r 1 2 6 2") as RectangleCommand

        assertEquals(1, result.startWidth)
        assertEquals(2, result.startHeight)
        assertEquals(6, result.endWidth)
        assertEquals(2, result.endHeight)
    }
}