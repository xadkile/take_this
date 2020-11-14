package takethis.eventhandler

import javafx.geometry.Rectangle2D
import javafx.scene.image.WritableImage
import javafx.scene.input.MouseEvent
import javafx.stage.Modality
import javafx.stage.StageStyle
import javafx.util.Duration
import takethis.other.CapturerImp
//import view.CroppingWindow
import takethis.view.FloatImagePanel
import takethis.scope.ImageScope
import takethis.state.FloatImagePanelState
import takethis.view.TransparentWindow
import tornadofx.*
import kotlin.math.abs

class TransparentWindowEventHandler : Component() {
    fun onMousePressed(event: MouseEvent, transparentWindow: TransparentWindow) {
        transparentWindow.anchorXScreen = event.screenX
        transparentWindow.anchorYScreen=event.screenY
        transparentWindow.updateMouseArchorPoint(event.sceneX, event.sceneY)
        transparentWindow.relocateCroppingPane(event.sceneX, event.sceneY)
        transparentWindow.resizeCroppingPane(0.0, 0.0)
        if (!transparentWindow.isCroppingPaneVisible()) {
            transparentWindow.showCroppingPane()
        }
    }

    fun onMouseDraggedOnPane(event: MouseEvent, transparentWindow: TransparentWindow) {
        val newWidth: Double = abs(event.sceneX - transparentWindow.anchorX)
        val newHeight: Double = abs(event.sceneY - transparentWindow.anchorY)
        val newXPos: Double = if (event.sceneX <= transparentWindow.anchorX) {
            event.sceneX
        } else {
            transparentWindow.croppingPane.x
        }

        val newYPos: Double = if (event.sceneY <= transparentWindow.anchorY) {
            event.sceneY
        } else {
            transparentWindow.croppingPane.y
        }

        transparentWindow.resizeCroppingPane(newWidth, newHeight)
        transparentWindow.relocateCroppingPane(newXPos, newYPos)
    }

    fun onMouseRelease(event: MouseEvent, transparentWindow: TransparentWindow) {
        transparentWindow.hide()
        runLater(Duration(200.0)) {
            val image: WritableImage? = CapturerImp.take(
                Rectangle2D(
                    transparentWindow.anchorXScreen,
                    transparentWindow.anchorYScreen,
                    transparentWindow.croppingPane.width,
                    transparentWindow.croppingPane.height
                )
            )
            if (image != null) {
                val imageScope = ImageScope(
                    FloatImagePanelState(
                       positionX= transparentWindow.anchorXScreen,
                       positionY= transparentWindow.anchorYScreen,
                        currentImage = image
                    )
                )
                val imageView = tornadofx.find<FloatImagePanel>(imageScope)

                imageView.openWindow(StageStyle.UNDECORATED)
            }
        }
    }


//    fun onMousePressed(event: MouseEvent, transparentWindow: TransparentWindow) {
//        transparentWindow.updateMouseArchorPoint(event.sceneX, event.sceneY)
//        transparentWindow.relocateCroppingPane(event.sceneX, event.sceneY)
//        transparentWindow.resizeCroppingPane(0.0, 0.0)
//        if (!transparentWindow.isCroppingPaneVisible()) {
//            transparentWindow.showCroppingPane()
//        }
//    }
//
//    fun onMouseDraggedOnPane(event: MouseEvent, transparentWindow: TransparentWindow) {
//        val newWidth: Double = abs(event.sceneX - transparentWindow.anchorX)
//        val newHeight: Double = abs(event.sceneY - transparentWindow.anchorY)
//        val newXPos: Double = if (event.sceneX <= transparentWindow.anchorX) {
//            event.sceneX
//        } else {
//            transparentWindow.croppingPane.x
//        }
//
//        val newYPos: Double = if (event.sceneY <= transparentWindow.anchorY) {
//            event.sceneY
//        } else {
//            transparentWindow.croppingPane.y
//        }
//
//        transparentWindow.resizeCroppingPane(newWidth, newHeight)
//        transparentWindow.relocateCroppingPane(newXPos, newYPos)
//    }
//
//    fun onMouseRelease(event: MouseEvent, transparentWindow: TransparentWindow) {
//        transparentWindow.hide()
//        runLater(Duration(200.0)) {
//            val image: WritableImage? = CapturerImp.take(
//                Rectangle2D(
//                    transparentWindow.croppingPane.x, transparentWindow.croppingPane.y,
//                    transparentWindow.croppingPane.width, transparentWindow.croppingPane.height
//                )
//            )
//            if (image != null) {
//                val imageScope = ImageScope(
//                    FloatImagePanelState(
//                        positionX = transparentWindow.croppingPane.x,
//                        positionY = transparentWindow.croppingPane.y,
//                        currentImage = image
//                    )
//                )
//                val imageView = tornadofx.find<FloatImagePanel>(imageScope)
//                imageView.openWindow(StageStyle.UNDECORATED)
//            }
//        }
//    }
}