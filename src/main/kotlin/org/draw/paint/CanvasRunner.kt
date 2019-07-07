package org.draw.paint

import org.draw.paint.canvas.Canvas
import org.draw.paint.canvas.ICanvas
import org.draw.paint.command.CommandParser
import org.draw.paint.common.Status
import org.draw.paint.exception.CanvasExceptions
import org.slf4j.LoggerFactory
import java.util.*

fun main(args: Array<String>) {
    val canvasRunner  = CanvasRunner()
    canvasRunner.start()
}

class CanvasRunner {

    companion object {
        private val log =  LoggerFactory.getLogger(CanvasRunner::class.java)
    }

    private var canvas: ICanvas =  Canvas()

    private fun readLine(): String {
        val scanner = Scanner(System.`in`)
        return scanner.nextLine()
    }

    internal fun executeCommand(command: String) {


        val status = CommandParser.parse(cmd = command).paint(canvas = canvas)

        if(status.isSuccess()) {
            canvas.printScreen()
        }

        Status.statusProcessor(status = status)
    }

    private fun executeConsoleCommands() {
        var command: String

        do {
            print("Enter Command :")
            command = this.readLine()
            log.trace("Received instruction as $command")
            this.executeCommand(command = command)

        } while (true)
    }


    fun start() {
        executeConsoleCommands()
    }

}