package takethis.state

import javafx.scene.image.Image
import takethis.viewupdater.FloatImagePanelViewUpdater

/**
 * State should be complete independent of everything. It should not have a dependency on view updater. For that to be possible, the stateUpdater must have direct access to viewUpdater
 * Different type of state
 */
data class FloatImagePanelState(
    /**
     * x distance between top left corner and the current mouse
     */
    val xDistanceToMouse: Double = 0.0,
    /**
     * y distance between top left corner and the current mouse
     */
    val yDistanceToMouse: Double = 0.0,
    val positionX:Double = 0.0,
    val positionY:Double=0.0,
    val preventCloseOnMouseRelease: Boolean = false,
    val currentImage: Image,
    val close:Boolean=false,
    val showContextMenu:Boolean=false,
//    val imageSaved:Boolean=false,
)