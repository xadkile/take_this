package takethis

import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.testfx.api.FxAssert
import org.testfx.api.FxRobot
import org.testfx.api.FxToolkit
import org.testfx.framework.junit5.ApplicationExtension
import org.testfx.framework.junit5.Start
import org.testfx.matcher.control.LabeledMatchers


@ExtendWith(ApplicationExtension::class)
class Bench {
    lateinit var button: Button

    @Start
    fun start(stage: Stage) {

        button = Button("click me!")
        button.setId("myButton")
        button.setOnAction { actionEvent -> button.setText("clicked!") }
        stage.setScene(Scene(StackPane(button), 100.0, 100.0))
        stage.show()
    }

    @Test
    fun should_contain_button_with_text(robot: FxRobot?) {
        FxAssert.verifyThat(button, LabeledMatchers.hasText("click me!"))
        // or (lookup by css id):
        FxAssert.verifyThat("#myButton", LabeledMatchers.hasText("click me!"))
        // or (lookup by css class):
        FxAssert.verifyThat(".button", LabeledMatchers.hasText("click me!"))
    }
}