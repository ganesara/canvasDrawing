package org.draw.paint.canvas

import org.slf4j.LoggerFactory

class Canvas(private val width: Int, private val height: Int) : ICanvas {

    companion object {
        private val logger =  LoggerFactory.getLogger(Canvas::class.java)
    }

    private val pixels: Array<Array<Pixel>>

    init {

        pixels = Array(size = height + 2) {i ->
            Array(size = width + 2){j ->
                if(i == 0 || i == height + 1) {
                    WidthBorder(x = j, y = i)
                } else if(j == 0 || j == width + 1) {
                    HeightBorder(x = j, y = i)
                } else {
                    Pixel(x = j, y = i, text = CanvasConstants.DEFAULT_DISPLAY_CHAR)
                }
            }
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

    override fun getPixcelValueAt(pos: Position): String {

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
                affectedList.add(pixel.position)
            }
        }

        return affectedList
    }
}