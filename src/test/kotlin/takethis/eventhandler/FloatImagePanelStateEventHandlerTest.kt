package takethis.eventhandler

import io.mockk.every
import io.mockk.mockk
import javafx.scene.input.MouseEvent
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import takethis.state.FloatImagePanelState
import takethis.stateupdater.FloatImagePanelStateUpdater

internal class FloatImagePanelStateEventHandlerTest {
    val handler = FloatImagePanelStateEventHandler()
    val state = FloatImagePanelState(
        currentImage = mockk()
    )

    @Test
    fun onMousePressPrimary() {
        val initState = state.copy(
            positionX = 100.0,
            positionY = 200.0
        )
        val stateUpdater = FloatImagePanelStateUpdater(
            initState.copy(
                positionX = 100.0,
                positionY = 200.0
            )
        )

        val event: MouseEvent = {
            val e = mockk<MouseEvent>()
            every { e.isPrimaryButtonDown } returns true
            every { e.screenX } returns 10.2
            every { e.screenY } returns 3.3
            e
        }()

        handler.onMousePressPrimary(event, stateUpdater)
        assertEquals(
            initState.copy(
                xDistanceToMouse = 100.0 - 10.2,
                yDistanceToMouse = 200.0 - 3.3,
            ), stateUpdater.state
        )
    }

    @Test
    fun onMousePressSecondary() {
        val stateUpdater = FloatImagePanelStateUpdater(
            state.copy()
        )
        val event: MouseEvent = {
            val e = mockk<MouseEvent>()
            every { e.isPrimaryButtonDown } returns false
            e
        }()
        handler.onMousePressSecondary(event, stateUpdater)
        assertEquals(
            state.copy(preventCloseOnMouseRelease = true, showContextMenu = true),
            stateUpdater.state
        )
    }

    @Test
    fun onMouseDragged2() {
        val initState:FloatImagePanelState = this.state.copy(
            xDistanceToMouse = 30.0, yDistanceToMouse = 66.0,
            positionX = 15.0, positionY = 150.0
        )
        val stateUpdater = FloatImagePanelStateUpdater(initState)
        val event: MouseEvent = {
            val e = mockk<MouseEvent>()
            every{e.isPrimaryButtonDown} returns true
            every { e.screenX } returns 200.0
            every { e.screenY } returns 300.0
            e
        }()
        this.handler.onMouseDragged2(event, stateUpdater)
        val newState = stateUpdater.state
        assertEquals(170.0, newState.positionX)
        assertEquals(234.0, newState.positionY)
        assertTrue(newState.preventCloseOnMouseRelease)
    }
}