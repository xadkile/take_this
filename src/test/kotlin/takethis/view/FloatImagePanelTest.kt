package takethis.view

import io.mockk.*
import javafx.application.Platform
import javafx.scene.image.Image
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.robot.Robot
import javafx.stage.Stage
import javafx.util.Duration
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertFalse
import org.testfx.api.FxToolkit
import org.testfx.robot.impl.ClickRobotImpl
import takethis.eventhandler.FloatImagePanelViewEventHandler
import takethis.scope.ImageScope
import takethis.state.FloatImagePanelState
import takethis.test.BaseTornadoTest
import takethis.test.TestApp
import takethis.test.TestView
import takethis.viewupdater.FloatImagePanelViewUpdater
import tornadofx.*
import java.net.URL


import tornadofx.UIComponent
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class FloatImagePanelTest : BaseTornadoTest() {
    lateinit var fip: FloatImagePanel
    lateinit var initState: FloatImagePanelState
    lateinit var viewUpdater: FloatImagePanelViewUpdater
    val mockEventHandler = mockk<FloatImagePanelViewEventHandler>(relaxed = true)
    val mockViewUpdater = mockk<FloatImagePanelViewUpdater>(relaxed = true)
    @BeforeEach
    fun localBefore() {
        initState = fip.stateUpdater.state.copy()
        fip.viewUpdater.update(fip, initState)
        viewUpdater = fip.viewUpdater
    }

    @Test
    @DisplayName("State is reflected correct on a view")
    fun testDisplayStateCorrectly() {
        this.generalViewAssuranceTest(fip)
        /*position X*/
        val state0 = initState.copy(
            positionX = 100.0,
        )
        viewUpdater.update(fip, state0)
        Platform.runLater {
            assertEquals(100.0, fip.currentStage?.x)
        }
        /*position Y*/
        val state1 = initState.copy(
            positionY = 333.0
        )
        viewUpdater.update(fip, state1)
        Platform.runLater {
            assertEquals(333.0, fip.currentStage?.y)
        }
        /*close*/
        val state2 = initState.copy(
            close = true
        )
        viewUpdater.update(fip, state2)
        Platform.runLater {
            val ct = fip.currentStage
            assertNotNull(ct)
            assertFalse(ct.isShowing)
        }
    }

    @Test
    @DisplayName("Event handlers are triggered when mouse primary button is pressed")
    fun testBinding_MousePressed_Primary() {
        fip.eventHandler = mockEventHandler
        fip.viewUpdater = mockViewUpdater
        Platform.runLater {
            val robot = Robot()
            robot.mouseMove(100.0, 100.0)
            robot.mouseClick(MouseButton.PRIMARY)
        }

        runLater(Duration(300.0)) { //wait for the view to update
            verify (exactly = 1){
                mockEventHandler.onMouseReleased(any(),any())
                mockEventHandler.onMousePressed(any(), any())
            }

            verify(exactly=2) {
                mockViewUpdater.update(any(),any())
            }

            confirmVerified(mockEventHandler)
        }

    }

    @Test
    @DisplayName("Event handlers are triggered when mouse secondary button is pressed")
    fun testBinding_MousePressed_Secondary() {
        fip.eventHandler = mockEventHandler
        Platform.runLater {
            val robot = Robot()
            robot.mouseMove(100.0, 100.0)
            robot.mouseClick(MouseButton.SECONDARY)
        }

        runLater(Duration(300.0)) { //wait for the view to update
            verify (exactly = 1){
                mockEventHandler.onMouseReleased(any(),any())
                mockEventHandler.onMousePressed(any(), any())
            }

            verify(exactly=2) {
                mockViewUpdater.update(any(),any())
            }

            confirmVerified(mockEventHandler)
        }
    }

    @Test
    @DisplayName("Event handlers are triggered when mouse is dragged")
    fun testBinding_MouseDragged() {
        fip.eventHandler = mockEventHandler

        Platform.runLater {
            val robot = Robot()
            robot.mouseMove(100.0, 100.0)
            robot.mousePress(MouseButton.PRIMARY)
            robot.mouseMove(200.0, 100.0)
            robot.mouseRelease(MouseButton.PRIMARY)
        }

        runLater(Duration(300.0)) { //wait for the view to update
            verify(atLeast = 1) {
                mockEventHandler.onMouseDragged(any(), any())
            }
            confirmVerified(mockEventHandler)
        }
    }

    override fun setUpCode(
        app: TestApp,
        rootView: TestView,
        stage: Stage
    ): UIComponent {
        initState = {
            val testImageUrl: URL = this::class.java.classLoader.getResource("bmo.png")
            val mockImage = Image(testImageUrl.toString())
            FloatImagePanelState(currentImage = mockImage)
        }()
        val scope = ImageScope(initState)
        val v: FloatImagePanel = find(scope)
        app.v = v
        fip = v
        return v
    }
}