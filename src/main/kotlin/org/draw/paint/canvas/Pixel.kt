package org.draw.paint.canvas

open class Pixel(x: Int, y: Int) {

    private var text: String = ""
    private val position: Position

    init {
        text = ""
        position = Position(x, y)

    }

    constructor(x:Int, y:Int, text: String): this(x = x, y = y) {
        this.text  =  text
    }

    fun display(): String {
        return this.text
    }

    fun isBlank():Boolean {
        return this.text.isBlank()
    }

    fun isNotBlank():Boolean {
        return this.text.isNotBlank()
    }

    override fun equals(other: Any?): Boolean {
        return this.text == (other as? Pixel)?.text ?: "UNKNOWN"
    }

    override fun hashCode(): Int {
        return this.text.hashCode()
    }

    override fun toString(): String {
        return this.text.toString()
    }
}

data class Position(val x: Int, val y:Int)

class WidthBorder(x: Int, y: Int) : Pixel(x = x, y = y, text = CanvasConstants.WIDTH_BORDER)

class HeightBorder(x: Int, y:Int) : Pixel(x = x, y = y, text =  CanvasConstants.HEIGHT_BORDER)