package takethis

import dorkbox.systemTray.MenuItem
import dorkbox.systemTray.SystemTray
import javafx.application.Platform
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.Stage
import tornadofx.App
import tornadofx.find
import java.security.Permissions
import java.util.*

/**
 * Create test for this
 * -Djdk.gtk.version=3
 */
class MyApp : App(IndependentWindow::class) {

    override fun start(stage: Stage) {
        super.start(stage)
        //start the software in tray if possible
        val sm = System.getSecurityManager()
//        sm.checkPermission()
        this.findIndeWindow().hide()
        val systemTray: SystemTray? = SystemTray.get()
        if (systemTray != null/*&&false*/) {
            Platform.setImplicitExit(false)
            systemTray.setImage(Constants.ICON_URL)

            val exitItem = MenuItem("Exit") { event ->
                Platform.exit()
                systemTray.shutdown()
            }

            val takePicItem = MenuItem("Take pic") { event ->
                println("take pic....")
                Common.openTransparentWindow()
            }

            val openTestWindow = MenuItem("Test window") { event ->
                Platform.runLater {

                    this@MyApp.findIndeWindow().show()
                }
            }

            systemTray.menu.also {
                it.add(takePicItem)
                it.add(openTestWindow)
                it.add(exitItem)
            }
        } else {
            //unable to show tray because the current system does not support tray
            // TODO for now, use this Alert, create another alert as a fragment so that it can be reused.
            Platform.runLater {
                val alert = Alert(
                    Alert.AlertType.NONE,
                    Constants.OPEN_INDEPENDENT_WINDOW_REQUEST,
                    ButtonType.YES, ButtonType.NO
                )
                val op: Optional<ButtonType>? = alert.showAndWait()
                if(op!=null && op.isPresent){
                    when(op.get()){
                        ButtonType.YES -> {
                            Platform.runLater {
                                find<IndependentWindow>().show()
                            }
                        }
                        ButtonType.NO -> Platform.exit()
                    }
                }else{
                    Platform.exit()
                }
            }
        }
    }
    fun findIndeWindow():IndependentWindow{
        return find<IndependentWindow>()
    }
}


