package takethis.viewupdater

import javafx.application.Platform
import javafx.stage.Stage
import takethis.state.FloatImagePanelState
import takethis.view.FloatImagePanel

/**
 * Stateless, only house updating logic
 */
class FloatImagePanelViewUpdater() {
    fun update(view: FloatImagePanel, newState: FloatImagePanelState) {
        val cs: Stage? = view.currentStage
        if (cs != null) {
            Platform.runLater {
                cs.x = newState.positionX
                cs.y = newState.positionY
                if (newState.close) {
                    view.close()
                }
            }
        }
    }
}