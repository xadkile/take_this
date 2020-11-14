package takethis.other

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PositionSizeCalculatorTest {

    @Test
    fun calDistance() {
        val anchor1 = 10.2
        val point1 = 222.2

        val anchor2 = 555.3
        val point2 = 3.2

        val d1 = PositionSizeCalculator.calDistance(anchor1,point1)
        assertEquals(point1-anchor1,d1,0.001)
        val d2 = PositionSizeCalculator.calDistance(anchor2,point2)
        assertEquals(anchor2-point2,d2,0.001)
    }

    @Test
    fun findTopLeftPosition(){
        val mouseX = 50.0
        val mouseY = 60.0

        val distanceToMouseX = 30.0
        val distanceToMouseY = 22.0

        val xy = PositionSizeCalculator.findTopLeftPosition(mouseX,mouseY,distanceToMouseX,distanceToMouseY)

        assertEquals(20.0,xy.x,0.001)
        assertEquals(38.0,xy.y,0.001)
    }
}