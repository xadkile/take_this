package takethis.view

import javafx.scene.input.MouseEvent
import javafx.stage.Modality
import takethis.eventhandler.FloatImagePanelViewEventHandler
import takethis.scope.ImageScope
import takethis.stateupdater.FloatImagePanelStateUpdater
import takethis.viewupdater.FloatImagePanelViewUpdater
import tornadofx.Fragment
import tornadofx.View
import tornadofx.imageview
import tornadofx.vbox

class FloatImagePanel : Fragment() {

    override fun onCreate() {
        super.onCreate()
//        this.primaryStage.initModality(Modality.APPLICATION_MODAL)
    }

    override val scope: ImageScope = super.scope as ImageScope

    val stateUpdater = FloatImagePanelStateUpdater(scope.initState)

//    val viewUpdater = FloatImagePanelViewUpdater(false)
    var viewUpdater = FloatImagePanelViewUpdater()

    var eventHandler = FloatImagePanelViewEventHandler()

    override val root = vbox {
        imageview(scope.initState.currentImage)

        setOnMousePressed { event ->
            eventHandler.onMousePressed(event, this@FloatImagePanel)
            viewUpdater.update(this@FloatImagePanel,stateUpdater.state)
        }

        setOnMouseReleased { event ->
            eventHandler.onMouseReleased(event, this@FloatImagePanel)
            viewUpdater.update(this@FloatImagePanel,stateUpdater.state)
        }

        setOnMouseDragged { event: MouseEvent ->
            eventHandler.onMouseDragged(event, this@FloatImagePanel)
            viewUpdater.update(this@FloatImagePanel,stateUpdater.state)
        }
        
        setOnDragDone { 
            println("drag done")
        }
    }

    override fun onDock() {
        //stage.initModality(Modality.APPLICATION_MODAL);
        this.currentStage?.also {
            it.isResizable = false
            it.isAlwaysOnTop=true

            it.x = stateUpdater.state.positionX
            it.y = stateUpdater.state.positionY
        }
        this.primaryStage.isAlwaysOnTop=true
    }
}
