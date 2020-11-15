package takethis

import javafx.application.Platform
import javafx.geometry.Rectangle2D
import javafx.stage.Screen
import javafx.stage.StageStyle
import takethis.view.TransparentWindow
import tornadofx.find

object Common {
    val screen: Screen = Screen.getPrimary()
    val bounds: Rectangle2D = screen.visualBounds
    fun openTransparentWindow(){
        Platform.runLater {
//            val t = find<TransparentWindow>().openWindow(StageStyle.UNDECORATED)
            val t = find<TransparentWindow>().openModal(StageStyle.UNDECORATED)
        }
    }


}