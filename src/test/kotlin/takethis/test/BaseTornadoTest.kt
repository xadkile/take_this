package takethis.test

import javafx.stage.Stage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.testfx.api.FxToolkit
import tornadofx.UIComponent
import kotlin.test.assertTrue

abstract class BaseTornadoTest {
    abstract fun setUpCode(app: TestApp,rootView:TestView,stage: Stage): UIComponent
    lateinit var app: TestApp

    @BeforeEach
    fun beforeEach() {
        FxToolkit.registerPrimaryStage()
        app = FxToolkit.setupApplication {
            TestApp { app,rootView,stage ->
                this.setUpCode(app,rootView,stage)
            }
        } as TestApp
    }

    @AfterEach
    fun afterEach(){
        FxToolkit.cleanupStages()
        FxToolkit.cleanupApplication(app)
    }

    fun generalViewAssuranceTest(view:UIComponent){
        assertTrue(view.isDocked)
    }
}