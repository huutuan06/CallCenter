package com.example.callcenter.model

import android.os.Handler
import android.os.Looper
import com.example.callcenter.Utils.Constant

data class Employee(
    var id: Int,
    var name: String,
    var role: Role,
    var isFree: Boolean
) {
    fun isRespondent(): Boolean = role == Role.RESPONDENT
    fun isManager(): Boolean = role == Role.MANAGER
    fun isDirector(): Boolean = role == Role.DIRECTOR

    fun canReceiveCall(): Boolean {
        return isFree
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
        } && isFree
    }

    fun handleCall(call: Call) {
        Handler(Looper.getMainLooper()).postDelayed({
            // TODO: execute Call
            call.isHandled = true
        }, Constant.CALL_HANDLING_TIME)
    }
}
