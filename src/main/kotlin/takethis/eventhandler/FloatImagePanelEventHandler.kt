package takethis.eventhandler

import javafx.scene.input.MouseEvent
import takethis.view.FloatImagePanel
import tornadofx.Component
import kotlin.math.abs

class FloatImagePanelEventHandler: Component() {

    fun onMousePressed(event:MouseEvent,fip:FloatImagePanel) {
        fip.currentStage?.also{
            fip.xDistanceToMouse = abs(event.screenX - it.x)
            fip.yDistanceToMouse = abs(event.screenY - it.y)
        }
    }

    fun onMouseReleased(event:MouseEvent,fip:FloatImagePanel) {

        if (fip.wasDragged) {
            fip.wasDragged = false
        } else {
            fip.close()
        }
    }

    fun onMouseDragged(event:MouseEvent,fip:FloatImagePanel) {
        fip.currentStage?.also {
            it.x = event.screenX - fip.xDistanceToMouse
            it.y = event.screenY - fip.yDistanceToMouse
        }
        fip.wasDragged = true
    }
}