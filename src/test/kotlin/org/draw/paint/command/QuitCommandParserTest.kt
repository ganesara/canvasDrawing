package org.draw.paint.command

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class QuitCommandParserTest {
    @Test
    fun testMath() {
        val parser =  QuitCommandParser()

        assertTrue(parser.isMatched(cmd = "Q"))
        assertTrue(parser.isMatched(cmd = "q"))
        assertFalse(parser.isMatched(cmd = "dssd"))
    }
}