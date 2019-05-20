package org.draw.paint.common

enum class StatusTypes {
    SUCCESS, FAILED
}



open class Status(val status: StatusTypes) {

    companion object{
        fun statusProcessor(status: Status) {
            when(status) {
                is Success -> println("Done.")
                is Failed -> println("Err: ${status.reason}")
            }
        }
    }

    fun isSuccess(): Boolean {
        return status == StatusTypes.SUCCESS
    }
}

class Success: Status(status = StatusTypes.SUCCESS)

class Failed(val reason: String):Status(status = StatusTypes.FAILED)