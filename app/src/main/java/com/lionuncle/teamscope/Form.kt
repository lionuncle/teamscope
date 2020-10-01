package com.lionuncle.teamscope

import java.util.*

data class Form (val title: String = "",val userId: String= "", val id: String = UUID.randomUUID().toString()){}