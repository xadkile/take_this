package takethis.view

import javafx.scene.image.Image
import javafx.stage.Stage
import org.junit.jupiter.api.*
import takethis.scope.ImageScope
import takethis.state.FloatImagePanelState
import takethis.test.BaseTornadoTest
import takethis.test.TestApp
import takethis.test.TestView
import tornadofx.*
import java.net.URL


import tornadofx.UIComponent

internal class FloatImagePanelTest: BaseTornadoTest() {
    lateinit var fip: FloatImagePanel

    @Test
    fun test1() {
        println(fip.isDocked)
    }

    override fun setUpCode(app: TestApp, rootView: TestView, stage:Stage): UIComponent {
        val scope: ImageScope = {
            val testImageUrl: URL = this::class.java.classLoader.getResource("bmo.png")
            val mockImage = Image(testImageUrl.toString())
            ImageScope(FloatImagePanelState(currentImage = mockImage))
        }()
        val v: FloatImagePanel = find<FloatImagePanel>(scope)
        app.v = v
        fip = v
        return v
    }
}
