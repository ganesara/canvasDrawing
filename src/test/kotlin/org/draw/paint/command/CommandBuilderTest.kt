package org.draw.paint.command

import org.draw.paint.canvas.CanvasHolder
import org.draw.paint.common.Failed
import org.junit.Assert.*
import org.junit.Test

class CommandBuilderTest {

    @Test
    fun testCommandBuilder() {
        val builder =  CommandBuilder()

        assertTrue(builder.setStringCommand("C 20 4").build() is CreateCanvasCommand)
        assertTrue(builder.setStringCommand("L 1 2 6 2").build() is LineCommand)
        assertTrue(builder.setStringCommand("R 14 1 18 3").build() is RectangleCommand)
        assertTrue(builder.setStringCommand("B 10 3 o").build() is FillCommand)
        assertTrue(builder.setStringCommand("q").build() is QuitCommand)
        assertTrue(builder.setStringCommand("don't Know").build().execute(CanvasHolder()) is Failed)
    }
}