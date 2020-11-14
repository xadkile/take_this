package takethis.stateupdater

import takethis.state.FloatImagePanelState

/**
 * House controlled logic to update a state class
 * For simple class, this is essentially a setter-class and can be skipped.
 * Only setup a state updater for complex setting logic or to trim down lengthy code
 */
class FloatImagePanelStateUpdater(
    var state: FloatImagePanelState
) {

    fun changeXDistanceToMouse(newXDistance: Double) {
        this.state = this.state.copy(xDistanceToMouse = newXDistance)
    }

//    fun changeImageSaved(newValue:Boolean){
//        this.state = this.state.copy(imageSaved = newValue)
//    }

    fun changeYDistanceToMouse(newValue: Double) {
        this.state = this.state.copy(yDistanceToMouse = newValue)
    }

    fun changePreventClose(newValue: Boolean) {
        this.state = this.state.copy(preventCloseOnMouseRelease = newValue)
    }

    fun changeXPosition(newValue: Double) {
        this.state = this.state.copy(positionX = newValue)
    }

    fun changeYPosition(newValue: Double) {
        this.state = this.state.copy(positionY = newValue)
    }

    fun setClose(newValue: Boolean){
        this.state = this.state.copy(close=newValue)
    }

    fun changeShowContextMenu(newValue:Boolean){
        this.state = this.state.copy(showContextMenu = newValue)
    }
}

