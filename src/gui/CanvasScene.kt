package gui

import javafx.event.EventHandler
import javafx.event.EventType
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.fxml.FXMLLoader
import javafx.scene.canvas.Canvas
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color

class CanvasScene( val stage: Stage? ){
    val gridpane: GridPane = GridPane()
    val canvas: Canvas = Canvas( this.stage!!.width, this.stage.height )

    init {

        this.gridpane.add( this.canvas, 0, 0 )
        this.canvas.onMouseClicked = EventHandler<MouseEvent> { this.clear() }
    }

    fun load(){
        this.stage?.scene = Scene( this.gridpane )
        this.paint()
    }

    fun paint(){
        val painter = this.canvas.graphicsContext2D
        painter.fill = Color.AQUA
        painter.fillRect( 0.0, 0.0, 100.0, 100.0 )
    }

    fun clear(){
        val painter = this.canvas.graphicsContext2D
        painter.fill = Color.BLACK
        painter.fillRect( 0.0, 0.0, this.canvas.width, this.canvas.height )
    }
}