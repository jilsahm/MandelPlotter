package gui

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.properties.Delegates

class MainStage : Application(){

    override fun start( primaryStage: Stage? ) {
        primaryStage?.width  = 1024.0
        primaryStage?.height = 640.0
        CanvasScene( primaryStage ).load()
        primaryStage?.isResizable = false
        primaryStage?.show()
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch( MainStage::class.java )
        }
    }
}