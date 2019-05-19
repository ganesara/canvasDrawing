package org.draw.paint.canvas

import org.draw.paint.painter.Painter
import org.slf4j.LoggerFactory


class CanvasHolder: ICanvas {

    companion object {
        private  val logger =  LoggerFactory.getLogger(CanvasHolder::class.java)
    }

    var canvas: Canvas =  Canvas(width = 0, height = 0)


    fun createCanvas(width: Int, height:Int) {
        canvas = Canvas(width = width, height = height)
        logger.info("Canvas Created")
    }


    override fun printScreen()
            = canvas.printScreen()


    override fun isPositionAvailable(pos: Position): Boolean
            = canvas.isPositionAvailable(pos = pos)


    override fun isPositionWritable(pos: Position): Boolean
            = canvas.isPositionWritable(pos = pos)


    override fun getPixcelValueAt(pos: Position): String
            =  canvas.getPixcelValueAt(pos = pos)


    override fun setPixelValueAt(pos: Position, value: String, overwrite: Boolean): Boolean
            = canvas.setPixelValueAt(pos = pos, value = value, overwrite = overwrite)


    override fun setPixelValueBetween(
        inclusiveStart: Position,
        inclusiveEnd: Position,
        value: String,
        overwrite: Boolean
    ): List<Position> {

       return  canvas.setPixelValueBetween(inclusiveStart = inclusiveStart,
           inclusiveEnd =  inclusiveEnd,
           value = value,
           overwrite = overwrite)
    }

    fun paint(painter: Painter) {
        painter.paint(this)
    }
}