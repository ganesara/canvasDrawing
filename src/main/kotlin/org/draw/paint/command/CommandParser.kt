package org.draw.paint.command

import org.draw.paint.canvas.ICanvas
import org.draw.paint.canvas.Position
import org.draw.paint.common.Failed
import org.draw.paint.common.Status
import org.draw.paint.exception.CanvasExceptions
import org.draw.paint.painter.*
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

object CommandParser  {

    private val createPainterFunList =  mutableListOf<(String) -> Painter?> (
        this::createCanvasPainter,
        this::createFillCommandPainter,
        this::createLineCommandPainter,
        this::quiteCommandPainter,
        this::rectangleCommandPainter
    )

    private val log =  LoggerFactory.getLogger(CommandParser::class.java)

    fun parse(cmd: String): Painter {

        var failed: Painter =   object: Painter {
            override fun validate(canvas: ICanvas): Status = Failed(reason = "Unable to Execute Command")
            override fun paint(canvas: ICanvas) : Status = Failed(reason = "Unable to Execute Command")
        }

        try {

            this.createPainterFunList.forEach { fn ->
                val painter =  fn(cmd)
                if(painter !=  null) return painter
            }

        } catch (e: Exception) {

            failed =   object: Painter {
                override fun validate(canvas: ICanvas): Status = Failed(reason = e.localizedMessage)
                override fun paint(canvas: ICanvas) : Status = Failed(reason = e.localizedMessage)
            }
        }

        return failed
    }

    private fun parseCommand(regex: Regex, string: String): MatchResult.Destructured ? {

        try {

            if(regex.matches(string)) {
                val result  =  regex.find(string)
                return result?.destructured ?: throw CanvasExceptions("Unable to parse Canvas Command")
            }

        } catch (e: Exception) {
            log.error("Error Parsing the Canvas Command", e)
            throw CanvasExceptions(throwable = e)
        }

        return  null
    }


    fun createCanvasPainter(cmd: String):Painter? {

        val cmdPattern = """[Cc]\s+(\d+)\s+(\d+)""".toRegex()

        val  (width , height) = this.parseCommand(regex = cmdPattern, string = cmd) ?: return null
        return  CanvasPainter(width = width.toInt(), height = height.toInt())

    }


    private fun createFillCommandPainter(cmd: String): Painter? {

        val templateRegex =  """[Bb]\s+(\d+)\s+(\d+)\s+(.)""".toRegex()

        val (sw,sh,colour) =  this.parseCommand(regex = templateRegex, string = cmd) ?: return null
        return  FillPainter(start = Position(x = sw.toInt(), y = sh.toInt()) ,colour = colour)

    }

    private fun createLineCommandPainter(cmd:String): Painter? {

        val templateRegex =  """[Ll]\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)""".toRegex()

        val (sw,sh,ew,eh) = this.parseCommand(regex = templateRegex, string = cmd) ?: return null

        return  LinePainter(start = Position(x = sw.toInt(), y= sh.toInt()),
            end = Position(x = ew.toInt(), y = eh.toInt()))
    }

    private fun quiteCommandPainter(cmd: String) : Painter? {

        val templateRegex =  """[Qq]""".toRegex()

        if(templateRegex.matches(cmd)) {
            exitProcess(status = 0)
        }

        return null
    }


    private  fun rectangleCommandPainter(cmd: String): Painter? {

        val templateRegex =  """[Rr]\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)""".toRegex()
        val (sw,sh,ew,eh) = this.parseCommand(regex = templateRegex, string = cmd) ?: return null

        return  RectanglePainter(start = Position(x = sw.toInt(), y = sh.toInt()),
            end = Position(x = ew.toInt(), y = eh.toInt()))

    }

}