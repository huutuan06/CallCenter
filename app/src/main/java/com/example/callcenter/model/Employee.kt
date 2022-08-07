package com.example.callcenter.model

data class Employee(
    var id: Int,
    var role: Role,
    var status: Status
) {
    fun busy() {
        status = Status.BUSY
    }
    fun free() {
        status = Status.FREE
    }

    fun canReceiveCall(): Boolean {
        return status == Status.FREE
    }

    fun canHandleCall(call: Call): Boolean {
        return when (role) {
            Role.RESPONDENT -> {
                call.callType == CallType.RESPONDENT_CALL
            }
            Role.MANAGER -> {
                call.callType == CallType.MANAGER_CALL
            }
            Role.DIRECTOR -> {
                call.callType == CallType.DIRECTOR_CALL
            }
        }
    }
}
