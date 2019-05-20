package org.draw.paint.command

import org.junit.Assert.*
import org.junit.Test

class LineCommandParserTest {
    @Test
    fun testMath() {
        val parser =  LineCommandParser()

        assertTrue(parser.isMatched(cmd = "L 1 2 6 2"))
        assertTrue(parser.isMatched(cmd = "l 1 2 6 2"))
        assertFalse(parser.isMatched(cmd = "dssd"))
    }

    @Test
    fun testParser() {
        val parser =  LineCommandParser()
        val result =  parser.parseCommand(cmd = "L 1 2 6 2") as LineCommand

        assertEquals(1, result.startWidth)
        assertEquals(2, result.startHeight)
        assertEquals(6, result.endWidth)
        assertEquals(2, result.endHeight)
    }
}