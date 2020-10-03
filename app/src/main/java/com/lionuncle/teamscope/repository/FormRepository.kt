package com.lionuncle.teamscope.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.lionuncle.teamscope.utils.FireStoreResultForm
import com.lionuncle.teamscope.models.Form
import java.util.*


class FormRepository private constructor() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    //implementing singleton design pattern in a thread safe manner
    companion object {
        @Volatile
        private var instance: FormRepository? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: FormRepository().also { instance = it }
        }
    }


    fun addNewForm(form: Form) {
        db.collection("Forms").document(form.id).set(form)
    }

    fun getAllFormsOfUser(userId: String, resultForm: FireStoreResultForm) {
        db.collection("Forms").get().addOnSuccessListener {
            var currForm: Form
            val formList: ArrayList<Form> = ArrayList<Form>()
            for (documentSnapshot in it) {
                currForm = documentSnapshot.toObject<Form>(Form::class.java)
                if (userId == currForm.userId) {
                    formList.add(currForm)
                }
            }
            resultForm.onFormsGetResult(formList)
        }
    }

}