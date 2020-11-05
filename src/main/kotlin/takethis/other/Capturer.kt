package takethis.other

import javafx.geometry.Rectangle2D
import javafx.scene.image.WritableImage


/**
 * read mouse dragging info from view.TransparentPanel => output x,y, width, height of the target image
 */
interface Capturer{
    fun take(rectangle:Rectangle2D): WritableImage?
}