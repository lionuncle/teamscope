package com.lionuncle.teamscope.viewmodel

import androidx.lifecycle.ViewModel
import com.lionuncle.teamscope.utils.FireStoreResultForm
import com.lionuncle.teamscope.models.Form
import com.lionuncle.teamscope.repository.FormRepository

class FormViewModel : ViewModel() {

    fun createNewForm(userId: String, formTitle: String): String {
        val form = Form(formTitle, userId)
        FormRepository.getInstance().addNewForm(form)
        return form.id
    }

    fun getAllFormsOfUser(userId: String, resultForm: FireStoreResultForm) {
        FormRepository.getInstance().getAllFormsOfUser(userId, resultForm)
    }
}