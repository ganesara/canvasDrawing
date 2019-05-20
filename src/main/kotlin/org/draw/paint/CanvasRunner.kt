package org.draw.paint

import org.draw.paint.canvas.CanvasHolder
import org.draw.paint.canvas.ICanvas
import org.draw.paint.command.CommandBuilder
import org.draw.paint.common.Status
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

    private val canvas: ICanvas
    private val builder:CommandBuilder

    init {
        canvas =  CanvasHolder()
        builder= CommandBuilder()
    }

    private fun readLine(): String {
        val scanner = Scanner(System.`in`)
        return scanner.nextLine()
    }

    internal fun executeCommand(command: String) {
        val status = builder.setStringCommand(command).build().execute(canvas = this.canvas)

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