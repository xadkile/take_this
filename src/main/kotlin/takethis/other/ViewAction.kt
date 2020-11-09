package takethis.other

import kotlin.reflect.KClass
import kotlin.reflect.cast

interface ViewAction<T:Any>{
    /**
     * should return a readable and/or descriptive name for an action
     */
    fun getName():String
}

/**
 * create an action object based on [ViewAction] interface
 */
fun <T:Any> createAction(name:String=""):ViewAction<T>{
    return object:ViewAction<T>{
        override fun getName(): String {
            return name
        }
    }
}

/**
 * Extract data type accepted by an action
 */
inline fun <reified T : Any> ViewAction<T>.getUnderlyingDataType(): KClass<T> {
    return T::class
}

/**
 * cast a variable to the data type accepted by this action
 */
@Throws(ClassCastException::class)
inline fun <reified T:Any> ViewAction<T>.castData(data:Any):T{
    val type:KClass<T> = this.getUnderlyingDataType()
    return type.cast(data)
}