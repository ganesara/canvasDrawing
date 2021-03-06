package org.draw.paint.canvas

import org.slf4j.LoggerFactory

class Canvas(private var width: Int = 0, private var height: Int = 0) : ICanvas {

    companion object {
        private val logger =  LoggerFactory.getLogger(Canvas::class.java)
    }

    private var pixels: Array<Array<Pixel>>

    init {

        pixels = initPixel(width = width, height = height)
    }

    private fun initPixel(width: Int, height: Int): Array<Array<Pixel>> {

        return Array(size = height + 2) {i ->
            Array(size = width + 2){j ->
                if(i == 0 || i == height + 1) {
                    WidthBorder()
                } else if(j == 0 || j == width + 1) {
                    HeightBorder()
                } else {
                    Pixel(txt = CanvasConstants.DEFAULT_DISPLAY_CHAR)
                }
            }
        }
    }

    override fun createCanvas(width: Int, height: Int): Boolean {

        return if(this.width == 0  || this.height == 0) {

            this.width =  width
            this.height =  height
            this.pixels = initPixel(width = width, height = height)
            true

        } else {

            false
        }


    }

    override fun printScreen() {
        for(i in this.pixels.indices) {
            if(i != 0) {
                println("")
            }
            for(j in this.pixels[i].indices) {
                print(this.pixels[i][j])
            }
        }
        println()
    }

    override fun isPositionAvailable(pos: Position): Boolean {

        if(this.pixels.isEmpty()) {
            logger.error("Position is not available")
            return false
        }

        return pos.x > 0
                && pos.y >0
                && this.pixels.size -1  > pos.y
                && this.pixels[0].size -1 > pos.x
    }

    override fun isPositionWritable(pos: Position): Boolean {

        return this.isPositionAvailable(pos)
                && this.pixels[pos.y][pos.x].isBlank()
    }

    override fun getPixelValueAt(pos: Position): String {

        return if(this.isPositionAvailable(pos)) {
                this.pixels[pos.y][pos.x].text
            } else {
                CanvasConstants.INVALID_TEXT_CHAR
            }
    }

    override fun setPixelValueAt(pos: Position, value: String, overwrite: Boolean) : Boolean {
        return  if(!overwrite && this.isPositionWritable(pos = pos)) {
                this.pixels[pos.y][pos.x].text =  value
                true
            }  else if(overwrite){
                this.pixels[pos.y][pos.x].text =  value
                true
            } else {
                false
            }
    }

    override fun setPixelValueBetween(inclusiveStart: Position,
                                      inclusiveEnd: Position,
                                      value: String,
                                      overwrite: Boolean) : List<Position> {

        val start =  inclusiveStart
        val end =  inclusiveEnd
        val affectedList =  mutableListOf<Position>()

        if(!this.isPositionAvailable(pos = start)
            || !this.isPositionAvailable(pos = end)) {
            return  affectedList
        }

        val yRange = if (start.y <= end.y) start.y..end.y else start.y downTo end.y
        val xRange = if (start.x <= end.x) start.x..end.x else start.x downTo  end.x

        for(y in yRange) {
            for(x in xRange) {
                val pixel = this.pixels[y][x]
                pixel.text = value
                affectedList.add(Position(x = x, y = y))
            }
        }

        return affectedList
    }

    override fun writableChildrenOf(pos: Position): List<Position> {
        val list =  mutableListOf<Position>()

        if (this.isPositionWritable(Position(x = pos.x, y = pos.y + 1 ))) {
            list.add(Position(x = pos.x, y = pos.y + 1 ))
        }

        if (this.isPositionWritable(Position(x = pos.x, y = pos.y - 1 ))) {
            list.add(Position(x = pos.x, y = pos.y - 1 ))
        }

        if (this.isPositionWritable(Position(x = pos.x + 1, y = pos.y))) {
            list.add(Position(x = pos.x + 1, y = pos.y))
        }

        if (this.isPositionWritable(Position(x = pos.x - 1, y = pos.y))) {
            list.add(Position(x = pos.x - 1, y = pos.y))
        }

        return list
    }
}