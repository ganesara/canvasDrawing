package org.draw.paint.command

import org.junit.Assert.*
import org.junit.Test

class FillCommandParserTest {
    @Test
    fun testMath() {
        val parser =  FillCommandParser()

        assertTrue(parser.isMatched(cmd = "B 1 2 C"))
        assertTrue(parser.isMatched(cmd = "b 1 2 *"))
        assertTrue(parser.isMatched(cmd = "b 1 2 ."))
        assertFalse(parser.isMatched(cmd = "dssd"))
    }

    @Test
    fun testParser() {
        val parser =  FillCommandParser()
        val result =  parser.parseCommand(cmd = "b 1 2 *") as FillCommand

        assertEquals(1, result.startWidth)
        assertEquals(2, result.startHeight)
        assertEquals("*", result.colour)
    }
}