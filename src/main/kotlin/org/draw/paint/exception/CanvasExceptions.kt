package org.draw.paint.exception

class CanvasExceptions: RuntimeException {
    constructor(msg: String):super(msg)
    constructor(throwable: Throwable): super(throwable)
}
