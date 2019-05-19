package org.draw.paint.painter

import org.draw.paint.canvas.ICanvas
import org.draw.paint.common.Status
import org.draw.paint.common.Success

interface Painter {

    fun validate(canvas: ICanvas): Status = Success()
    fun paint(canvas: ICanvas) : Status
}