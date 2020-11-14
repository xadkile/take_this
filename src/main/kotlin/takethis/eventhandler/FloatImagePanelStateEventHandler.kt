package takethis.eventhandler

import javafx.scene.input.MouseEvent
import takethis.stateupdater.FloatImagePanelStateUpdater
import kotlin.math.abs
import takethis.other.PositionSizeCalculator

/**
 * Handle hypothesis state event, only work on state
 */
class FloatImagePanelStateEventHandler {
    fun onMousePressed2(event: MouseEvent, stateUpdater: FloatImagePanelStateUpdater) {
        onMousePressPrimary(event,stateUpdater)
        onMousePressSecondary(event,stateUpdater)
    }

    fun onMousePressPrimary(event: MouseEvent, stateUpdater: FloatImagePanelStateUpdater){
        if (event.isPrimaryButtonDown) {
            stateUpdater.changeXDistanceToMouse(abs(event.screenX - stateUpdater.state.positionX))
            stateUpdater.changeYDistanceToMouse(abs(event.screenY - stateUpdater.state.positionY))
        }
    }

    fun onMousePressSecondary(event: MouseEvent, stateUpdater: FloatImagePanelStateUpdater){
        if(!event.isPrimaryButtonDown){
            println("secondary:Show context menu")
            stateUpdater.changeShowContextMenu(true)
            stateUpdater.changePreventClose(true)
        }
    }

    fun onMouseReleased2(event: MouseEvent, stateUpdater: FloatImagePanelStateUpdater) {
        if (stateUpdater.state.preventCloseOnMouseRelease) {
            stateUpdater.changePreventClose(false)
        } else {
            stateUpdater.setClose(true)
        }
    }

    fun onMouseDragged2(event: MouseEvent, stateUpdater: FloatImagePanelStateUpdater) {
        if (event.isPrimaryButtonDown) {
            val newXY = PositionSizeCalculator.findTopLeftPosition(
                event.screenX, event.screenY,
                stateUpdater.state.xDistanceToMouse, stateUpdater.state.yDistanceToMouse
            )

            stateUpdater.changeXPosition(newXY.x)
            stateUpdater.changeYPosition(newXY.y)

            stateUpdater.changePreventClose(true)
        }
    }
}