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

    init {
        canvas =  CanvasHolder()
    }


    private fun executeConsoleCommands() {
        val scanner = Scanner(System.`in`)
        var command: String
        val builder =  CommandBuilder()

        do {
            print("Enter Command :")
            command = scanner.nextLine()
            log.trace("Received instruction as $command")
            val status = builder.setStringCommand(command).build().execute(canvas = this.canvas)

            if(status.isSuccess()) {
                canvas.printScreen()
            }

            Status.statusProcessor(status = status)
        } while (true)
    }


    fun start() {
        executeConsoleCommands()
    }


}