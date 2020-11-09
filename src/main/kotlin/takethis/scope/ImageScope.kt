package takethis.scope

import javafx.scene.image.Image
import takethis.state.FloatImagePanelState
import tornadofx.Scope

/**
 * A data ferry class to pass [image] to a [FloatImagePanel]
 */
class ImageScope(
//    val image: Image,
//    val spawPositionX: Double,
//    val spawPositionY: Double,
    val initState: FloatImagePanelState
) : Scope()