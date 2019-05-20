package org.draw.paint

import org.draw.paint.canvas.CanvasHolder
import org.draw.paint.canvas.ICanvas

fun main(args: Array<String>) {
    val canvasRunner  = CanvasRunner()
    canvasRunner.start()
}

class CanvasRunner {

    private val canvas: ICanvas

    init {
        canvas =  CanvasHolder()
    }



    fun start() {

    }


}