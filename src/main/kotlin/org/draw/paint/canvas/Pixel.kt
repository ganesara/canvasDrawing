package org.draw.paint.canvas

open class Pixel() {

    var text: String = ""

    constructor(txt: String): this() {
        this.text = txt
    }

    fun isBlank():Boolean {
        return this.text.isBlank()
    }


    override fun equals(other: Any?): Boolean {
        return this.text == (other as? Pixel)?.text ?: "UNKNOWN"
    }

    override fun hashCode(): Int {
        return this.text.hashCode()
    }

    override fun toString(): String {
        return this.text
    }
}

data class Position(val x: Int, val y:Int)

class WidthBorder : Pixel( txt = CanvasConstants.WIDTH_BORDER_CHAR)

class HeightBorder : Pixel(txt =  CanvasConstants.HEIGHT_BORDER_CHAR)