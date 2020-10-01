package com.lionuncle.teamscope

import kotlin.properties.Delegates

abstract class Option (val questionId: String,val type:String) {
    //final constants for type recognition

    companion object {
        const val TYPE_SHORT_ANSWER: String = "TYPE_SHORT_ANSWER"
        const val TYPE_TIME: String = "TYPE_TIME"
        const val TYPE_NUMBER: String = "TYPE_NUMBER"
    }

    //properties
    var number: Int by Delegates.notNull<Int>()
    lateinit var shortAnswer: String
    lateinit var time: String

    //getters
    fun getOptShortAnswer(): String {return this.shortAnswer}
    fun getOptTime(): String {return this.time}
    fun getOptNumber(): Int {return this.number}

    //setters
    fun setOptShortAnswer(shortAns: String ){
        shortAnswer = shortAns
    }
    fun setOptTime(time: String){
        this.time = time
    }
    fun setOptNumber(number: Int){
        this.number = number
    }

}