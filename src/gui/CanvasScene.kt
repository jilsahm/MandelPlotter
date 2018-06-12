package gui

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.scene.canvas.Canvas
import javafx.scene.image.WritableImage
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color

import graphics.ColorSet
import math.Mandelbrot

class CanvasScene( val stage: Stage? ){
    val pixelsX = this.stage?.width?.toInt() ?: 100
    val pixelsY = this.stage?.height?.toInt() ?: 100
    val gridpane: GridPane = GridPane()
    val canvas: Canvas = Canvas( this.stage!!.width, this.stage.height )
    val image: WritableImage = WritableImage( this.pixelsX, this.pixelsY )

    init {
        this.gridpane.add( this.canvas, 0, 0 )
        this.canvas.onMouseClicked = EventHandler<MouseEvent> { this.clear() }
    }

    fun load(){
        this.stage?.scene = Scene( this.gridpane )
        this.paint()
    }

    fun paint(){
        val painter     = this.canvas.graphicsContext2D
        val pixelwriter = this.image.pixelWriter
        val colors      = ColorSet()
        val mandelbrot  = Mandelbrot( this.pixelsX, this.pixelsY )

        for( y in 0 until this.pixelsY){
            for( x in 0 until this.pixelsX ){
                pixelwriter.setColor( x,y, colors.getColor( mandelbrot.getPixelValue( x, y ).toInt() ) )
            }
        }

        painter.drawImage( this.image, 0.0, 0.0 )
    }

    fun clear(){
        val painter = this.canvas.graphicsContext2D
        painter.fill = Color.BLACK
        painter.fillRect( 0.0, 0.0, this.canvas.width, this.canvas.height )
    }
}