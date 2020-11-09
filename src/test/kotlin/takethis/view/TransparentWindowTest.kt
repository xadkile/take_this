package takethis.view

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.stage.Stage
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.testfx.api.FxRobot
import org.testfx.api.FxToolkit
import org.testfx.framework.junit5.ApplicationExtension
import org.testfx.framework.junit5.Start
import takethis.CC
import tornadofx.*
import java.util.function.Supplier
import tornadofx.launch

class TView : View() {
    override val root = vbox()
}
class TApp:App(TView::class)
//@ExtendWith(ApplicationExtension::class)
internal class TransparentWindowTest {
    //    var tpw: TransparentWindow = TransparentWindow()
    lateinit var tpw: TransparentWindow
    val view = TView()
    val primaryStage: Stage = FxToolkit.registerPrimaryStage()

    @BeforeEach
    fun before(){
        FxToolkit.setupFixture {
//            tpw = find()
//            view.root.add(tpw.root)
//            primaryStage.scene = Scene(view.root)
//            tpw = TransparentWindow()
//            primaryStage.scene = Scene(tpw.root)
            primaryStage.show()
            primaryStage.toFront()
        }
    }
//    @Start
//    fun start(stage: Stage) {
//        val app = App()
//        FxToolkit.setupApplication {
//            app
//        }
//        FxToolkit.setupFixture {
//            val primaryStage = FxToolkit.registerPrimaryStage()
//            tpw = find()
//            view.root.add(tpw.root)
//            primaryStage.scene = Scene(view.root)
//            primaryStage.show()
//            primaryStage.toFront()
//        }
//    }

    @Test
    fun transparent_view_is_as_large_as_the_screen() {
        tpw=find()
        assertNotNull(tpw)
        tpw.openWindow()
//        tpw.openWindow()
//        assertNotNull(tpw.currentStage)
//        tpw.currentStage?.also {
//            assertTrue(it.isShowing)
//            assertEquals(CC.SCREEN_SIZE.width, it.width)
//        }

//        FxAssert.verifyThat(view,org.testfx.matcher.control.)
//        FxAssert.verifyThat(button, LabeledMatchers.hasText("click me!"))
//        // or (lookup by css id):
//        FxAssert.verifyThat("#myButton", LabeledMatchers.hasText("click me!"))
//        // or (lookup by css class):
//        FxAssert.verifyThat(".button", LabeledMatchers.hasText("click me!"))
    }
}