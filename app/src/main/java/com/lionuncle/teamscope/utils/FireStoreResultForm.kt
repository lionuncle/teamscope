package com.lionuncle.teamscope.utils

import com.lionuncle.teamscope.models.Form
import com.lionuncle.teamscope.models.Question

interface FireStoreResultForm {
    fun onFormsGetResult(formsList: ArrayList<Form>)
}