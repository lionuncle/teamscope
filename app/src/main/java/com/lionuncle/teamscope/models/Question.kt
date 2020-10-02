package com.lionuncle.teamscope.models

import java.util.*

data class Question(
    val title: String = "",
    val formId: String = "",
    val answerType: String = "",
    val answer: String = "",
    val id: String = UUID.randomUUID().toString()
){
    companion object {
        const val TYPE_SHORT_ANSWER: String = "TYPE_SHORT_ANSWER"
        const val TYPE_TIME: String = "TYPE_TIME"
        const val TYPE_NUMBER: String = "TYPE_NUMBER"
    }
}