package takethis.test

import javafx.stage.Stage
import tornadofx.App
import tornadofx.UIComponent
import tornadofx.find


class TestApp(val viewToTestSupplier: (app: TestApp,rootView:TestView,stage: Stage) -> UIComponent) : App(TestView::class) {
    lateinit var tv: TestView
    lateinit var v: UIComponent
    lateinit var stage: Stage
    override fun start(stage: Stage) {
        super.start(stage)
        tv = find<TestView>()
        val uiComponent = viewToTestSupplier(this,tv,stage)
        tv.addUIComponent(uiComponent)
        this.stage = stage
        stage.show()
    }
}
