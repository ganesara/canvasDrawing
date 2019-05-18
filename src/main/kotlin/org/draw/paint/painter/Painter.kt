package org.draw.paint.painter

import org.draw.paint.canvas.Canvas

interface Painter {

    fun draw(canvas: Canvas) : Boolean
}