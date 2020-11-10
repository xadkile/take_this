package takethis.state

import javafx.scene.image.Image
import takethis.viewupdater.FloatImagePanelViewUpdater

/**
 * State should be complete independent of everything. It should not have a dependency on view updater. For that to be possible, the stateUpdater must have direct access to viewUpdater
 */
data class FloatImagePanelState(
    val xDistanceToMouse: Double = 0.0,
    val yDistanceToMouse: Double = 0.0,
    val positionX:Double = 0.0,
    val positionY:Double=0.0,
    val preventClose: Boolean = false,
    val currentImage: Image,
)