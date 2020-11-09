package takethis.state

import javafx.scene.image.Image
import takethis.viewupdater.FloatImagePanelViewUpdater

data class FloatImagePanelState(
    val xDistanceToMouse: Double = 0.0,
    val yDistanceToMouse: Double = 0.0,
    val positionX:Double = 0.0,
    val positionY:Double=0.0,
    val preventClose: Boolean = false,
    val currentImage: Image,
    val updater: FloatImagePanelViewUpdater? = null
) {
    init {
        updater?.updateWith(this)
    }
}