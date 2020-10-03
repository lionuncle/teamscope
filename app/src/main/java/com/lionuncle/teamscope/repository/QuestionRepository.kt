package com.lionuncle.teamscope.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.lionuncle.teamscope.models.Question
import com.lionuncle.teamscope.utils.FireStoreResultForm
import com.lionuncle.teamscope.utils.FireStoreResultQuestion
import java.util.ArrayList

class QuestionRepository {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addNewQuestion(question: Question) {
        db.collection("Questions").document(question.id).set(question)
    }

    fun getAllQuestionsOfForm(formId: String, resultQuestion: FireStoreResultQuestion) {
        db.collection("Questions").get().addOnSuccessListener {
            var currQuestion: Question
            val questionList: ArrayList<Question> = ArrayList<Question>()
            for (documentSnapshot in it) {
                currQuestion = documentSnapshot.toObject<Question>(Question::class.java)
                if (formId == currQuestion.formId) {
                    questionList.add(currQuestion)
                }
            }
            resultQuestion.onQuestionGetResult(questionList)
        }
    }
}