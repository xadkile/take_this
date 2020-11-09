package takethis.other

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ViewActionKtTest {

    val action1 = createAction<Double>("testAction")
    val anyData: Any = 10.2
    @Test
    fun testGetT() {
        assertEquals(Double::class, action1.getUnderlyingDataType())
        assertNotEquals(String::class, action1.getUnderlyingDataType())
    }

    @Test
    fun testCastData_ok() {
        assertEquals(action1.castData(anyData)::class, Double::class)
    }

    @Test
    fun testCastData_fail() {
        val anyData: Any = "string"
        assertThrows(ClassCastException::class.java) { action1.castData(anyData) }
    }

    @Test
    fun testCreateAction(){
        val actionName="action"
        val act:ViewAction<Double> = createAction(actionName)
        assertEquals(Double::class,act.getUnderlyingDataType())
        assertEquals(actionName,act.getName())
        try{
            val d = act.castData(anyData)
        }catch(e:Exception){
            fail("unable to cast data")
        }
    }
}