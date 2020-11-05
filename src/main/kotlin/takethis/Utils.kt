package takethis

import kotlin.math.max

class Utils{
    companion object CO{
        @JvmStatic
        fun qweLength(length:Double):Double{
            return max(0.0,length)
        }
    }
}