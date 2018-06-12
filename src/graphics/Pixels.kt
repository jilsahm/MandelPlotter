package graphics

import javafx.scene.paint.Color

class ColorSet( private val depth: Int = 1 ){
    private var currentRGB: Int = 0
    private var activeColor: Int = 0
    private val colorstep: Int = this.depth
    private val colors: Array<Color> = Array( 256, { buildColor( it ) } )

    private fun buildColor( index: Int ): Color{
        val color = Color.rgb( this.currentRGB, this.currentRGB, this.currentRGB )
        this.currentRGB += this.colorstep
        if ( this.currentRGB > 255 ) {
            this.currentRGB = 0
        }
        return color
    }

    fun next(): Color{
        if ( activeColor >= this.colors.size ){
            this.activeColor = 0
        }
        return this.colors[this.activeColor++]
    }

    fun getColor( index: Int ): Color{
        return this.colors[index]
    }
}