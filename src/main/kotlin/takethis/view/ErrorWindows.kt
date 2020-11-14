package takethis.view

import javafx.scene.Parent
import tornadofx.View
import tornadofx.textfield
import tornadofx.vbox

class ErrorWindows:View() {

    override val root: Parent = vbox{
        textfield { "zzzzzz" }
    }
}