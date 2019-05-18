package org.draw.paint.canvas

class Canvas(private val width: Int, private val height: Int) {

    private val pixels: Array<Array<Pixel>>

    init {

        pixels = Array(size = height + 2) {i ->
            Array(size = width + 2){j ->
                if(i == 0 || i == height + 1) {
                    WidthBorder(x = j, y = i)
                } else if(j == 0 || j == width + 1) {
                    HeightBorder(x = j, y = i)
                } else {
                    Pixel(x = j, y = i, text = CanvasConstants.DEFAULT_DISPLAY)
                }
            }
        }
    }

    fun printScreen() {
        for(i in this.pixels.indices) {
            if(i != 0) {
                println("")
            }
            for(j in this.pixels[i].indices) {
                print(this.pixels[i][j])
            }
        }
    }

//    fun isPossitionAvailable(position: Position): Boolean {
//        if(this.pixels.isEmpty()) {
//            return false
//        }
//    }
}