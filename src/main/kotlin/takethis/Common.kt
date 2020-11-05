package takethis

import javafx.application.Platform
import javafx.stage.StageStyle
import takethis.view.TransparentWindow
import tornadofx.find

object Common {
    fun openTransparentWindow(){
        Platform.runLater {
            find<TransparentWindow>().openWindow(StageStyle.UNDECORATED)
        }
    }


}