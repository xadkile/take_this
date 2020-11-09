package takethis.eventhandler

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import takethis.stateupdater.FloatImagePanelStateManager
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
class FloatImagePanelEventHandler : Component() {

    fun onMousePressed(event: MouseEvent, fip: FloatImagePanel) {
        if (event.isPrimaryButtonDown) {
            fip.currentStage?.also {
                fip.stateManager.changeXDistanceToMouse(abs(event.screenX - it.x))
                fip.stateManager.changeYDistanceToMouse(abs(event.screenY - it.y))
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
            fip.stateManager.changePreventClose(true)
        }
    }

    fun onMouseReleased(event: MouseEvent, fip: FloatImagePanel) {
        if (fip.stateManager.state.preventClose) {
            fip.stateManager.changePreventClose(false)
        } else {
            fip.close()
        }
    }

    fun onMouseDragged(event: MouseEvent, fip: FloatImagePanel) {
        if (event.isPrimaryButtonDown) {
            println(event.screenX - fip.stateManager.state.xDistanceToMouse)
            fip.stateManager.changeXPosition(event.screenX - fip.stateManager.state.xDistanceToMouse)

            fip.stateManager.changeYPosition(
                event.screenY - fip.stateManager.state.yDistanceToMouse
            )
            fip.stateManager.changePreventClose(true)
        }
    }

    fun saveImageToFile(image: Image, file: File) {
        //TODO
    }
}