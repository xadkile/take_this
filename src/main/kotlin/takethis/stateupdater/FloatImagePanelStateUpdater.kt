package takethis.stateupdater

import javafx.scene.image.Image
import takethis.other.ViewAction
import takethis.state.FloatImagePanelState
import takethis.other.castData
import takethis.other.createAction
import takethis.viewupdater.FloatImagePanelViewUpdater

/**
 * House controlled logic to update a state class
 * For simple class, this is essentially a setter-class and can be skipped.
 * Only setup a state updater for complex setting logic or to trim down lengthy code
 */
class FloatImagePanelStateUpdater(
    var state: FloatImagePanelState
) {
    companion object CO {
        val CHANGE_X_DISTANCE_TO_MOUSE = createAction<Double>("CHANGE_X_DISTANCE_TO_MOUSE")
        val CHANGE_Y_DISTANCE_TO_MOUSE = createAction<Double>("CHANGE_Y_DISTANCE_TO_MOUSE")
        val CHANGE_PREVENT_CLOSE = createAction<Boolean>("CHANGE_PREVENT_CLOSE")
        val CHANGE_CURRENT_IMAGE = createAction<Image>("CHANGE_CURRENT_IMAGE")
        val CHANGE_ALL = createAction<FloatImagePanelState>("CHANGE_ALL")
        val CHANGE_POSITION_X = createAction<Double>("CHANGE_POSITION_X")
        val CHANGE_POSITION_Y = createAction<Double>("CHANGE_POSITION_Y")
    }

    fun changeXDistanceToMouse(newXDistance: Double) {
        this.state = this.state.copy(xDistanceToMouse = newXDistance)
    }

    fun changeYDistanceToMouse(newValue: Double) {
        this.state = this.state.copy(yDistanceToMouse = newValue)
    }

    fun changePreventClose(newValue: Boolean) {
        this.state = this.state.copy(preventClose = newValue)
    }

    fun changeXPosition(newValue: Double) {
        this.state = this.state.copy(positionX = newValue)
    }

    fun changeYPosition(newValue: Double) {
        this.state = this.state.copy(positionY = newValue)
    }
}

