package com.lionuncle.teamscope.utils

import com.lionuncle.teamscope.models.Form

interface FireStoreResultForm {
    fun onFormsGetResult(formsList: ArrayList<Form>)
}