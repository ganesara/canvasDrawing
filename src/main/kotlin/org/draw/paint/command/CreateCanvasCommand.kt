package org.draw.paint.command

import org.draw.paint.canvas.CanvasHolder
import org.draw.paint.canvas.ICanvas
import org.draw.paint.common.Failed
import org.draw.paint.common.Status
import org.draw.paint.common.Success


class CreateCanvasCommand(internal val width: Int, internal val height: Int): Command {

    override fun execute(canvas: ICanvas): Status {

        return when(canvas) {
            is CanvasHolder -> {
                canvas.createCanvas(width = width, height = height)
                Success()
            }
            else -> {
                Failed(reason = "Canvas Holder is needed")
            }
        }
    }
}