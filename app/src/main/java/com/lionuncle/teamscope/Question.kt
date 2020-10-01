package com.lionuncle.teamscope

import java.util.*

data class Question(val title:String, val option: Option,val id: String = UUID.randomUUID().toString(), val formId : String)