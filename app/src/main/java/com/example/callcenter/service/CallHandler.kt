package com.example.callcenter.service

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.callcenter.Utils.Constant
import com.example.callcenter.model.*
import java.util.*

class CallHandler : CallCenter {
    var respondents = arrayListOf<Employee>()
    var managers = arrayListOf<Employee>()
    var directors = arrayListOf<Employee>()

    var respondentCalls: Queue<Call> = LinkedList()
    var managerCalls: Queue<Call> = LinkedList()
    var directorCalls: Queue<Call> = LinkedList()

    override fun execute(call: Call) {
        respondentCalls.add(call)
        processCall(Role.RESPONDENT)
    }

    override fun receiveCall(employee: Employee, call: Call) {
        employee.busy()
        if (employee.canHandleCall(call)) {
            handleCall(employee, call)
        } else {
            moveCallToSuitableEmployee(employee.role, call) // employee level < call level
            employee.free()
            processCall(employee.role)
        }
    }

    override fun handleCall(employee: Employee, call: Call) {
        Log.d("Call_Center", "Employee ${employee.id} - ${employee.role} IS HANDLING Call ${call.id}")
        Handler(Looper.getMainLooper()).postDelayed({
            call.isHandled = true
            employee.free()
            Log.d("Call_Center", "Employee ${employee.id} - ${employee.role} FINISHED Call ${call.id}")
            processCall(employee.role)
        }, Constant.HANDLING_TIME)
    }

    private fun moveCallToSuitableEmployee(role: Role, call: Call) {
        when (role) {
            Role.RESPONDENT -> {
                managerCalls.add(call)
                processCall(Role.MANAGER)
            }
            Role.MANAGER -> {
                directorCalls.add(call)
                processCall(Role.DIRECTOR)
            }
            else -> {
                // Error
            }
        }
    }

    private fun processCall(role: Role) {
        when (role) {
            Role.RESPONDENT -> {
                val anyRespondentFree = respondents.any { it.canReceiveCall() }
                if (anyRespondentFree) {
                    val call = respondentCalls.poll()
                    if (call != null)
                        receiveCall(respondents.first { it.status == Status.FREE }, call)
                }
            }
            Role.MANAGER -> {
                val anyManagerFree = managers.any { it.canReceiveCall() }
                if (anyManagerFree) {
                    val call = managerCalls.poll()
                    if (call != null)
                        receiveCall(managers.first { it.status == Status.FREE }, call)
                }
            }
            Role.DIRECTOR -> {
                val anyDirectorFree = directors.any { it.canReceiveCall() }
                if (anyDirectorFree) {
                    val call = directorCalls.poll()
                    if (call != null)
                        receiveCall(directors.first { it.status == Status.FREE }, call)
                }
            }
        }
    }
}
