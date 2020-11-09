package takethis.viewupdater

import javafx.stage.Stage
import takethis.state.FloatImagePanelState
import takethis.view.FloatImagePanel

class FloatImagePanelViewUpdater(private val view: FloatImagePanel, private var r:Boolean=false) {
    fun updateWith(newState: FloatImagePanelState) {
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