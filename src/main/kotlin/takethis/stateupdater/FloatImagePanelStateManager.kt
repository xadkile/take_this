package takethis.stateupdater

import javafx.scene.image.Image
import takethis.other.ViewAction
import takethis.state.FloatImagePanelState
import takethis.other.castData
import takethis.other.createAction

class FloatImagePanelStateManager(var state: FloatImagePanelState) {
    companion object CO{
        val CHANGE_X_DISTANCE_TO_MOUSE = createAction<Double>("CHANGE_X_DISTANCE_TO_MOUSE")
        val CHANGE_Y_DISTANCE_TO_MOUSE = createAction<Double>("CHANGE_Y_DISTANCE_TO_MOUSE")
        val CHANGE_PREVENT_CLOSE = createAction<Boolean>("CHANGE_PREVENT_CLOSE")
        val CHANGE_CURRENT_IMAGE = createAction<Image>("CHANGE_CURRENT_IMAGE")
        val CHANGE_ALL = createAction<FloatImagePanelState>("CHANGE_ALL")
        val CHANGE_POSITION_X = createAction<Double>("CHANGE_POSITION_X")
        val CHANGE_POSITION_Y = createAction<Double>("CHANGE_POSITION_Y")
    }

    fun changeXDistanceToMouse(newXDistance:Double){
        this.state = this.state.copy(xDistanceToMouse = newXDistance)
    }

    fun changeYDistanceToMouse(newValue:Double){
        this.state= this.state.copy(yDistanceToMouse = newValue)
    }

    fun changePreventClose(newValue:Boolean){
        this.state = this.state.copy(preventClose=newValue)
    }

    fun changeXPosition(newValue: Double){
        this.state = this.state.copy(positionX = newValue)
    }

    fun changeYPosition(newValue:Double){
        this.state = this.state.copy(positionY=newValue)
    }

    fun <T : Any> invokeAction(action: ViewAction<T>, data: T) {
        when (action) {
            CHANGE_X_DISTANCE_TO_MOUSE -> {
                this.state = this.state.copy(
                    xDistanceToMouse = CHANGE_X_DISTANCE_TO_MOUSE.castData(data)
                )
            }
            CHANGE_Y_DISTANCE_TO_MOUSE -> {
                this.state = this.state.copy(
                    yDistanceToMouse = CHANGE_Y_DISTANCE_TO_MOUSE.castData(data)
                )
            }
            CHANGE_PREVENT_CLOSE -> {
                this.state =
                    this.state.copy(preventClose = CHANGE_PREVENT_CLOSE.castData(data))
            }
            CHANGE_CURRENT_IMAGE -> {
                this.state =
                    this.state.copy(currentImage = CHANGE_CURRENT_IMAGE.castData(data))
            }
            CHANGE_ALL->{
                this.state = CHANGE_ALL.castData(data)
            }

            CHANGE_POSITION_X->{
                this.state = this.state.copy(positionX = CHANGE_POSITION_X.castData(data))
            }

            CHANGE_POSITION_Y->{
                this.state = this.state.copy(positionY= CHANGE_POSITION_Y.castData(data))
            }
        }
    }
}

