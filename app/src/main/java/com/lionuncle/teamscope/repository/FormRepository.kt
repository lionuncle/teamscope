package com.lionuncle.teamscope.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.lionuncle.teamscope.FireStoreResult
import com.lionuncle.teamscope.Form
import java.util.*


class FormRepository {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addNewForm(form: Form){
        db.collection("Forms").document(form.id).set(form)
    }
    fun getAllFormsOfUser(userId: String, result: FireStoreResult){
        db.collection("Forms").get().addOnSuccessListener {
            var currForm: Form
            val formList: ArrayList<Form> = ArrayList<Form>()
            for (documentSnapshot in it) {
                currForm = documentSnapshot.toObject<Form>(Form::class.java)
                if (userId == currForm.userId) {
                    formList.add(currForm)
                }
            }
            result.onFormsGetResult(formList)
        }
    }

}