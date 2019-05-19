package org.draw.paint

import org.draw.paint.canvas.ICanvas

interface Painter {
    fun draw(canvas: ICanvas)
}