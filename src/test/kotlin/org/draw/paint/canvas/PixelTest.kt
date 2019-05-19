package org.draw.paint.canvas

import org.junit.Assert.assertTrue
import org.junit.Test


class PixelTest {


    @Test
    fun instanceEqualsTest() {
        val p =  Pixel(1, 2, "abc")
        val q = Pixel(x = 5, y = 8, text =  "abc")
        assertTrue(p == q)
    }

}