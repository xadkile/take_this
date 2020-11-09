package takethis.view

import javafx.scene.input.MouseEvent
import takethis.eventhandler.FloatImagePanelEventHandler
import takethis.scope.ImageScope
import takethis.state.FloatImagePanelState
import takethis.stateupdater.FloatImagePanelStateManager
import takethis.viewupdater.FloatImagePanelViewUpdater
import tornadofx.Fragment
import tornadofx.imageview
import tornadofx.vbox

class FloatImagePanel : Fragment() {
    override val scope: ImageScope = super.scope as ImageScope
    val initState = scope.initState.copy(updater= FloatImagePanelViewUpdater(this,false))
//    val stateManager = FloatImagePanelStateManager(
//        FloatImagePanelState(
//            positionX = scope.spawPositionX,
//            positionY = scope.spawPositionY,
//            updater = FloatImagePanelViewUpdater(this,false)
//        )
//    )
    val stateManager = FloatImagePanelStateManager(initState)
    val eventHandler = find<FloatImagePanelEventHandler>()
    override val root = vbox {
        imageview(initState.currentImage)

        setOnMousePressed { event ->
            eventHandler.onMousePressed(event, this@FloatImagePanel)
        }

        setOnMouseReleased { event ->
            eventHandler.onMouseReleased(event, this@FloatImagePanel)
        }

        setOnMouseDragged { event: MouseEvent ->
            eventHandler.onMouseDragged(event, this@FloatImagePanel)
        }
    }

    override fun onDock() {
        this.currentStage?.also {
            it.isResizable = false
            it.isAlwaysOnTop=true
            it.x = stateManager.state.positionX
            it.y = stateManager.state.positionY
        }
        this.primaryStage.isAlwaysOnTop=true
    }
}
