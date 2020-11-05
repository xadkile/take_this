package takethis.other

import javafx.geometry.Rectangle2D
import javafx.scene.image.WritableImage
import javafx.scene.robot.Robot

/**
 * not working on wayland
 * xorg: ok
 * windows: untested
 */
object CapturerImp : Capturer {
    val robot = Robot()

    override fun take(rectangle: Rectangle2D): WritableImage? {
        if(rectangle.width<=0.0 || rectangle.height<=0.0){
            return null
        }
        val screenShot = robot.getScreenCapture(null,rectangle)
        return screenShot
    }
}