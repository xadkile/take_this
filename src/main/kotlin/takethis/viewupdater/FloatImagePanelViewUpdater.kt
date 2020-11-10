package takethis.viewupdater

import javafx.stage.Stage
import takethis.state.FloatImagePanelState
import takethis.view.FloatImagePanel

/**
 * Stateless, only house updating logic
 */
class FloatImagePanelViewUpdater(private var r:Boolean=false) {
    fun update(view:FloatImagePanel,newState: FloatImagePanelState) {
        if(r){
            val cs: Stage? = view.currentStage
            if (cs != null) {
                cs.x = newState.positionX
                cs.y = newState.positionY
            }
        }else{
            r=!r
        }
    }
}