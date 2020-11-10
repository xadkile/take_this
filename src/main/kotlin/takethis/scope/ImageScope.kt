package takethis.scope

import javafx.scene.image.Image
import takethis.state.FloatImagePanelState
import takethis.viewupdater.FloatImagePanelViewUpdater
import tornadofx.Scope
import kotlin.reflect.KClass

/**
 * A data ferry class to pass initial state to a [FloatImagePanel]
 */
class ImageScope(
    val initState: FloatImagePanelState,
) : Scope()