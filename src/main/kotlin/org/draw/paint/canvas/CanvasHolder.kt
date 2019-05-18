package org.draw.paint.canvas

import org.slf4j.LoggerFactory


class CanvasHolder {

    companion object {
        private  val logger =  LoggerFactory.getLogger(CanvasHolder::class.java)
    }

    protected var canvas: Canvas =  Canvas(width = 0, height = 0)


    fun createCanvas(width: Int, height:Int) {
        canvas = Canvas(width = width, height = height)
        logger.info("Canvas Created")
    }


    fun print() {
        canvas.printScreen()
    }
}