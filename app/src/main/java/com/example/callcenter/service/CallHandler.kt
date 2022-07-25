package com.example.callcenter.service

import android.util.Log
import com.example.callcenter.model.Call
import com.example.callcenter.model.CallType
import com.example.callcenter.model.Employee
import com.example.callcenter.model.Role
import java.util.*

class CallHandler {
    var queue: Queue<Call> = LinkedList()

//    fun dispatchCall(call: Call) {
//        val employee = getEmployeeToHandleCall(call)
//        if (employee != null) {
//            employee.handleCall(call)
//        } else {
//            Log.i("CallCenter", "No employee is free to handle the call ${call.id}")
//        }
//    }
//
//    fun getEmployeeToHandleCall(call: Call, listEmployee: List<Employee>) : Employee? {
//        val freeRespondent = listEmployee.first { it.isRespondent() && it.canReceiveCall() }
//        if (freeRespondent == null) {
//            queue.add(call)
//            return null
//        } else if (freeRespondent.canHandleCall()) {
//            return
//        }
//    }
}
