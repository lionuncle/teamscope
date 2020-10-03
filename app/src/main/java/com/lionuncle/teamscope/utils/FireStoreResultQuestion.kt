package com.lionuncle.teamscope.utils

import com.lionuncle.teamscope.models.Question

interface FireStoreResultQuestion {
    fun onQuestionGetResult(questionsList: ArrayList<Question>)
}