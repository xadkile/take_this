package takethis.other

import javafx.scene.image.Image
import tornadofx.Scope

/**
 * A data ferry class to pass [image] to a [FloatImagePanel]
 */
class ImageScope(val image: Image, val spawPositionX:Double,val spawPositionY: Double) : Scope()