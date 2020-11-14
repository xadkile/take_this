package takethis

import dorkbox.systemTray.Menu
import dorkbox.systemTray.MenuItem
import dorkbox.systemTray.SystemTray
import javafx.application.Platform
import javafx.geometry.Rectangle2D
import javafx.stage.Screen
import javafx.stage.StageStyle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import takethis.view.TransparentWindow
import tornadofx.*
import java.net.URL

class IndependentWindow: View() {
    override val root = vbox {
        button("show trans panel"){
            action{
                Common.openTransparentWindow()
            }
        }
    }

    override fun onDock() {
        this.currentStage?.also{
            it.isResizable=false
            it.x = Common.bounds.maxX
            it.y = 0.0
        }
        this.primaryStage.isAlwaysOnTop=true
    }

    fun hide(){
        this.currentStage?.also{
            it.hide()
        }
    }

    fun show(){
        this.currentStage?.also{
            it.show()
        }
    }
}
