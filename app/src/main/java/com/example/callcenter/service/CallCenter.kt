package com.example.callcenter.service

import com.example.callcenter.model.Call
import com.example.callcenter.model.Employee

interface CallCenter {
    fun receiveCall(employee: Employee, call: Call)
    fun handleCall(employee: Employee, call: Call)
    fun execute(call: Call)
}