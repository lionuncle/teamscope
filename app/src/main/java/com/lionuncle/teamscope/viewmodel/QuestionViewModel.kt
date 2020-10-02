package com.lionuncle.teamscope.viewmodel

import androidx.lifecycle.ViewModel
import com.lionuncle.teamscope.models.Question
import com.lionuncle.teamscope.repository.QuestionRepository
import com.lionuncle.teamscope.utils.FireStoreResultForm
import com.lionuncle.teamscope.utils.FireStoreResultQuestion

class QuestionViewModel: ViewModel() {

    fun createNewQuestion(formId: String, questionTitle: String, questionType: String){
        QuestionRepository().addNewQuestion(Question(questionTitle,formId,questionType))
    }

    fun getAllQuestionsOfForm(formId: String, resultQuestion: FireStoreResultQuestion){
        QuestionRepository().getAllQuestionsOfForm(formId,resultQuestion)
    }
}