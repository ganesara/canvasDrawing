package org.draw.paint.canvas

interface ICanvas {

    fun createCanvas(width: Int, height:Int): Boolean
    fun printScreen()
    fun isPositionAvailable(pos: Position): Boolean
    fun isPositionWritable(pos: Position): Boolean
    fun getPixelValueAt(pos: Position): String
    fun setPixelValueAt(pos: Position, value: String, overwrite: Boolean = true) : Boolean
    fun setPixelValueBetween(inclusiveStart: Position,
                             inclusiveEnd: Position,
                             value: String,
                             overwrite: Boolean = true) : List<Position>
    fun writableChildrenOf(pos: Position): List<Position>
}