package org.draw.paint.canvas

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream


class PixelTest {


    @Test
    fun instanceEqualsTest() {
        val p =  Pixel(1, 2, "abc")
        val q = Pixel(x = 5, y = 8, text =  "abc")
        assertTrue(p == q)
    }

}