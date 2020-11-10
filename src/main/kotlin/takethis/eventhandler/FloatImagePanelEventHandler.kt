package takethis.eventhandler

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import takethis.view.FloatImagePanel
import tornadofx.Component
import java.io.File
import java.nio.file.Paths
import kotlin.math.abs

/**
 * this event handler still need access to the because it need access to the view state
 * If I can separate the view state, the handler can work directly with the state, and avoid the view completely. This is much easier to test because the state is a simple data structure and creating test state is easier to setup test view.
 * By changing the view state, I will indirectly change the view appearance.
 * To do that, i need to write up some binding between the state and the view
 */
class FloatImagePanelEventHandler  {

    fun onMousePressed(event: MouseEvent, fip: FloatImagePanel) {
        if (event.isPrimaryButtonDown) {
            fip.currentStage?.also {
                fip.stateUpdater.changeXDistanceToMouse(abs(event.screenX - it.x))
                fip.stateUpdater.changeYDistanceToMouse(abs(event.screenY - it.y))
            }
        } else {
            println("secondary:Show context menu")
            val contextMenu = ContextMenu().apply {

                val saveItem = MenuItem("Save").apply {
                    this.onAction = EventHandler<ActionEvent> { event ->
                        println("Save event")
                    }
                }

                this.items.add(saveItem)
            }
            contextMenu.show(fip.currentWindow)

            val file: File = Paths.get("").toFile()
            fip.stateUpdater.changePreventClose(true)
        }
    }

    fun onMouseReleased(event: MouseEvent, fip: FloatImagePanel) {
        if (fip.stateUpdater.state.preventClose) {
            fip.stateUpdater.changePreventClose(false)
        } else {
            fip.close()
        }
    }

    fun onMouseDragged(event: MouseEvent, fip: FloatImagePanel) {
        if (event.isPrimaryButtonDown) {
            fip.stateUpdater.changeXPosition(event.screenX - fip.stateUpdater.state.xDistanceToMouse)

            fip.stateUpdater.changeYPosition(
                event.screenY - fip.stateUpdater.state.yDistanceToMouse
            )
            fip.stateUpdater.changePreventClose(true)
        }
    }

    fun saveImageToFile(image: Image, file: File) {
        //TODO
    }
}