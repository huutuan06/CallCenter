package com.example.callcenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.callcenter.model.*
import com.example.callcenter.service.CallHandler

class MainActivity : AppCompatActivity() {
    var callHandler = CallHandler()
    var calls = ArrayList<Call>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        start()
    }

    private fun initData() {
        callHandler.respondents.addAll(
            listOf(
                Employee(1, Role.RESPONDENT, Status.FREE),
                Employee(2, Role.RESPONDENT, Status.FREE),
                Employee(3, Role.RESPONDENT, Status.FREE),
                Employee(4, Role.RESPONDENT, Status.FREE)
            )
        )
        callHandler.managers.addAll(
            listOf(
                Employee(5, Role.MANAGER, Status.FREE),
                Employee(6, Role.MANAGER, Status.FREE),
                Employee(7, Role.MANAGER, Status.FREE)
            )
        )
        callHandler.directors.addAll(
            listOf(
                Employee(8, Role.DIRECTOR, Status.FREE),
                Employee(9, Role.DIRECTOR, Status.FREE)
            )
        )

        calls.addAll(
            listOf(
                Call(1, CallType.MANAGER_CALL, false),
                Call(2, CallType.RESPONDENT_CALL, false),
                Call(3, CallType.DIRECTOR_CALL, false),
                Call(4, CallType.RESPONDENT_CALL, false),
                Call(5, CallType.RESPONDENT_CALL, false),
                Call(6, CallType.RESPONDENT_CALL, false),
                Call(7, CallType.RESPONDENT_CALL, false),
                Call(8, CallType.RESPONDENT_CALL, false),
                Call(9, CallType.MANAGER_CALL, false),
                Call(10, CallType.DIRECTOR_CALL, false),
                Call(11, CallType.MANAGER_CALL, false),
                Call(12, CallType.DIRECTOR_CALL, false),
                Call(13, CallType.RESPONDENT_CALL, false),
                Call(14, CallType.RESPONDENT_CALL, false)
            )
        )
    }

    private fun start() {
        for (call in calls) {
            callHandler.execute(call)
        }
    }
}
