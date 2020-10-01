package com.lionuncle.teamscope.viewmodel

import androidx.lifecycle.ViewModel
import com.lionuncle.teamscope.FireStoreResult
import com.lionuncle.teamscope.Form
import com.lionuncle.teamscope.repository.FormRepository

class FormViewModel: ViewModel() {

    fun createNewForm(userId: String,formTitle: String){
        FormRepository().addNewForm(Form(formTitle,userId))
    }

    fun getAllFormsOfUser(userId: String, result: FireStoreResult){
        FormRepository().getAllFormsOfUser(userId,result)
    }
}