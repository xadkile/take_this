package takethis.view

import javafx.scene.input.MouseEvent
import takethis.eventhandler.FloatImagePanelEventHandler
import takethis.eventhandler.TransparentWindowEventHandler
import takethis.other.ImageScope
import tornadofx.Fragment
import tornadofx.imageview
import tornadofx.vbox
import kotlin.math.abs

class FloatImagePanel : Fragment() {
    private val fip = this
    override val scope: ImageScope = super.scope as ImageScope
    var xDistanceToMouse:Double = 0.0
    var yDistanceToMouse:Double = 0.0
    var wasDragged = false
//    val eventHandler:FloatImagePanelEventHandler by inject()
    val eventHandler = find<FloatImagePanelEventHandler>()
    override val root = vbox {
        imageview(scope.image)
        setOnMousePressed { event->
            eventHandler.onMousePressed(event,this@FloatImagePanel)
        }
        setOnMouseReleased { event ->
            eventHandler.onMouseReleased(event,this@FloatImagePanel)
        }

        setOnMouseDragged { event: MouseEvent ->
            eventHandler.onMouseDragged(event,this@FloatImagePanel)
        }
    }

    override fun onDock() {
        this.currentStage?.also {
            it.isAlwaysOnTop=true
            it.isResizable = false
            it.x = scope.spawPositionX
            it.y = scope.spawPositionY
        }
    }
}
