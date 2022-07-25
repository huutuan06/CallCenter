package com.example.callcenter.model

data class Call(
    var id: Int,
    var callType: CallType,
    var isHandled: Boolean
)
