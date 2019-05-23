package org.draw.paint.canvas

import org.junit.Assert.assertTrue
import org.junit.Test


class PixelTest {


    @Test
    fun instanceEqualsTest() {
        val p =  Pixel( "abc")
        val q = Pixel(txt =  "abc")
        assertTrue(p == q)
    }

}