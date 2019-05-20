package org.draw.paint.command

import org.draw.paint.canvas.ICanvas
import org.draw.paint.common.Status
import java.awt.Canvas

interface Command {
  fun execute(canvas: ICanvas) : Status
}