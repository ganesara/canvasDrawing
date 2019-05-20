package org.draw.paint.exception

class CommandParserException: RuntimeException {
    constructor(msg: String):super(msg)
    constructor(throwable: Throwable): super(throwable)
}