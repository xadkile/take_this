package takethis.test

import javafx.application.Platform
import tornadofx.UIComponent
import tornadofx.View
import tornadofx.stackpane


class TestView : View() {
    override val root = stackpane { }
    fun addUIComponent(uiComponent: UIComponent) {
        Platform.runLater {
            root.add(uiComponent)
        }
    }
}
