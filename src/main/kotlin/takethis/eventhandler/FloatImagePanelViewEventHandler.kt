package takethis.eventhandler

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import javafx.stage.FileChooser
import takethis.view.FloatImagePanel
import java.io.File
import javax.imageio.ImageIO
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage
import takethis.Constants
import java.awt.image.BufferedImage
import java.time.LocalDateTime


/**
 * React to view event
 */
class FloatImagePanelViewEventHandler {
    val stateEventHandler = FloatImagePanelStateEventHandler()
    fun onMousePressed(event: MouseEvent, fip: FloatImagePanel) {
        stateEventHandler.onMousePressed2(event, fip.stateUpdater)
        if (event.isPrimaryButtonDown == false) {
            if (fip.stateUpdater.state.showContextMenu) {
                val contextMenu = ContextMenu().apply {
                    val saveItem = MenuItem("Save").apply {
                        this.onAction = EventHandler { event: ActionEvent ->
                            val stage: Stage? = fip.currentStage
                            if (stage != null) {
                                val fileChooser: FileChooser = {
                                    val fc = FileChooser()
                                    fc.title = Constants.FILE_CHOOSER_WINDOW_TITLE
                                    fc.initialFileName =
                                        "Image_${LocalDateTime.now()}.${Constants.DEFAULT_IMAGE_FILE_TYPE}"
                                    fc
                                }()
                                val file: File? = fileChooser.showSaveDialog(fip.currentStage)
                                if (file != null) {
                                    try{
                                    this@FloatImagePanelViewEventHandler.saveImageFile(
                                        image = fip.stateUpdater.state.currentImage,
                                        file = file
                                    )
//                                        fip.stateUpdater.changeImageSaved(true)
                                    }catch(e:Exception){

                                    }
                                }
                            }
                        }
                    }
                    this.items.add(saveItem)
                }
                contextMenu.x = event.screenX
                contextMenu.y = event.screenY
                contextMenu.show(fip.currentStage)
            }
        }
    }

    fun onMouseReleased(event: MouseEvent, fip: FloatImagePanel) {
        stateEventHandler.onMouseReleased2(event, fip.stateUpdater)
    }

    fun onMouseDragged(event: MouseEvent, fip: FloatImagePanel) {
        stateEventHandler.onMouseDragged2(event, fip.stateUpdater)
    }

    @Throws(Exception::class)
    private fun saveImageFile(image: Image, file: File) {
        val bufImage: BufferedImage = SwingFXUtils.fromFXImage(image, null)
        ImageIO.write(bufImage, Constants.DEFAULT_IMAGE_FILE_TYPE, file)
    }
}