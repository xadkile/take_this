package takethis.other

import kotlin.math.abs

object PositionSizeCalculator {
    fun calDistance(anchor: Double, point: Double): Double {
        return abs(anchor - point)
    }

    fun findTopLeftPosition(mouseX: Double, mouseY: Double,
                            topLeftDistanceToX: Double,
                            topLeftDistanceToY: Double): XY {
        return XY(mouseX - topLeftDistanceToX, mouseY - topLeftDistanceToY)
    }
}