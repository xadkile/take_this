package takethis.view

import javafx.scene.input.MouseEvent
import takethis.eventhandler.FloatImagePanelEventHandler
import takethis.scope.ImageScope
import takethis.stateupdater.FloatImagePanelStateUpdater
import takethis.viewupdater.FloatImagePanelViewUpdater
import tornadofx.Fragment
import tornadofx.View
import tornadofx.imageview
import tornadofx.vbox

class FloatImagePanel : Fragment() {
    override val scope: ImageScope = super.scope as ImageScope

    val stateUpdater = FloatImagePanelStateUpdater(scope.initState)
    val viewUpdater = FloatImagePanelViewUpdater(false)

    val eventHandler = FloatImagePanelEventHandler()

    override val root = vbox {
        imageview(scope.initState.currentImage)

        setOnMousePressed { event ->
            eventHandler.onMousePressed(event, this@FloatImagePanel)
            viewUpdater.update(this@FloatImagePanel,stateUpdater.state)
            println(this@FloatImagePanel.isDocked)
        }

        setOnMouseReleased { event ->
            eventHandler.onMouseReleased(event, this@FloatImagePanel)
            viewUpdater.update(this@FloatImagePanel,stateUpdater.state)
        }

        setOnMouseDragged { event: MouseEvent ->
            eventHandler.onMouseDragged(event, this@FloatImagePanel)
            viewUpdater.update(this@FloatImagePanel,stateUpdater.state)
        }
    }

//    override fun onBeforeShow() {
//        super.onBeforeShow()
//        this.currentStage?.also {
//            it.isResizable = false
//            it.isAlwaysOnTop=true
//            it.x = stateUpdater.state.positionX
//            it.y = stateUpdater.state.positionY
//        }
//        this.primaryStage.isAlwaysOnTop=true
//    }

    override fun onDock() {
        this.currentStage?.also {
            it.isResizable = false
            it.isAlwaysOnTop=true
            it.x = stateUpdater.state.positionX
            it.y = stateUpdater.state.positionY
        }
        this.primaryStage.isAlwaysOnTop=true
    }
}
