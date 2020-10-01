package com.lionuncle.teamscope

import kotlin.properties.Delegates

abstract class Option {
    //final constants for type recognition
    companion object {
        const val TYPE_SHORT_ANSWER: String = "TYPE_SHORT_ANSWER"
        const val TYPE_MCQ: String = "TYPE_MCQ"
        const val TYPE_TIME: String = "TYPE_TIME"
        const val TYPE_NUMBER: String = "TYPE_NUMBER"
    }
    //properties
    var number: Int by Delegates.notNull<Int>()
    lateinit var shortAnswer: String
    lateinit var time: String
    lateinit var mcqList: MutableList<String>
    lateinit var type :String

    //getters
    fun getOptShortAnswer(): String {return this.shortAnswer}
    fun getOptTime(): String {return this.time}
    fun getOptNumber(): Int {return this.number}
    fun getOptMcqList(): MutableList<String> {return this.mcqList}

    //setters
    fun setOptShortAnswer(shortAns: String ){
        shortAnswer = shortAns
    }
    fun setOptTime(time: String){
        this.time = time
    }
    fun setOptMcq(mcqList: MutableList<String>){
        this.mcqList = mcqList
    }
    fun setOptNumber(number: Int){
        this.number = number
    }

}