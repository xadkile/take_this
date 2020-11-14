package takethis.view

 import javafx.geometry.Rectangle2D
 import javafx.scene.input.MouseEvent
 import javafx.scene.paint.Color
 import javafx.scene.paint.Paint
 import javafx.scene.shape.Rectangle
 import javafx.scene.shape.StrokeType
 import javafx.stage.Screen
 import takethis.Common
 import takethis.Utils.CO.qweLength
 import takethis.eventhandler.TransparentWindowEventHandler
 import tornadofx.Fragment
 import tornadofx.pane
 import tornadofx.rectangle
 import tornadofx.style
 import java.awt.GraphicsEnvironment


//cannot have another constructor because takethis.other constructor, takethis.other than the default, will not work, unless I accept using setter to inject my updater into this after construction
//to test event handler. I have to shield TransparentWindow under an interface, then let the handler accept an instance of this interface, and work with property of TransparentWindow via that interface. A lot of boiler code and have to use var, and latinit, and shit
//another way is to inject the handler by param. But this involve passing a map of param with String key. Anything involve String and variable names are bad
// IN short, this framework (javafx) sucks ass

/**
 * A transparent windows spanning the entire screen
 * Drag-draw a rectangle on this window to determine which partition of the screen of which to take screen
 */
class TransparentWindow: Fragment(title = "transparent window") {
    val opacity = 0.4
    val windowPositionX = 0.0
    val windowPositionY = 0.0
    val tpw = this
    var croppingInfo: Rectangle2D = Rectangle2D.EMPTY
    private val eventHandler=find<TransparentWindowEventHandler>()
    lateinit var croppingPane:Rectangle
    var anchorX = 0.0
    var anchorY = 0.0
    var anchorXScreen = 0.0
    var anchorYScreen = 0.0
    override val root = pane {
        style {
            backgroundColor += Color.TRANSPARENT
        }
        rectangle {
            this@TransparentWindow.croppingPane = this
            fill = Paint.valueOf("DODGERBLUE")
            stroke = Paint.valueOf("BLACK")
            strokeType = StrokeType.INSIDE
            width=0.0
            height=0.0
        }
        setOnMouseDragged { event: MouseEvent ->
            eventHandler.onMouseDraggedOnPane(event, tpw)
        }
        setOnMouseReleased { event: MouseEvent ->
            eventHandler.onMouseRelease(event, tpw)
        }
        setOnMousePressed { event: MouseEvent->
            eventHandler.onMousePressed(event, tpw)
        }
    }

    override fun onDock() {
        val bounds: Rectangle2D = Common.bounds
        this.currentStage?.also{
            it.opacity = opacity
            it.height = bounds.height
            it.width = bounds.width
            it.x = bounds.minX
            it.y = bounds.minY
            it.isMaximized=true
        }
        this.primaryStage.isAlwaysOnTop=true
        this.hideCroppingPane()
    }

    fun isCroppingPaneVisible(): Boolean {
        val visibleSize = this.croppingPane.width > 0.0 && this.croppingPane.height > 0.0 && this.croppingPane.isVisible
        val parentVisilbe = this.root.isVisible
        return (visibleSize && parentVisilbe)
    }

    fun showCroppingPane(){
        this.croppingPane.isVisible = true
    }

    fun hideCroppingPane(){
        this.croppingPane.isVisible = false
    }

    fun hide(){
        this.currentStage?.hide()
    }

    fun updateMouseArchorPoint(x: Double, y: Double){
        this.anchorX = x
        this.anchorY = y
    }

    fun relocateCroppingPane(x: Double, y: Double){
        this.croppingPane.x = x
        this.croppingPane.y = y
    }

    fun resizeCroppingPane(width: Double, height: Double){
        this.croppingPane.width=qweLength(width)
        this.croppingPane.height= qweLength(height)
    }
}
