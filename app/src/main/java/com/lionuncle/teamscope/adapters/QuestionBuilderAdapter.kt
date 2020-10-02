package com.lionuncle.teamscope.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.models.Question

class QuestionBuilderAdapter(val questionList: ArrayList<Question>) : RecyclerView.Adapter<QuestionBuilderAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.QuestionBuilderListItemLayoutTitleText)
        var type: TextView = itemView.findViewById(R.id.QuestionBuilderListItemLayoutTypeText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_builder_list_item_layout, parent, false)
        return QuestionViewHolder(v)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.title.text = questionList.get(position).title
        when(questionList.get(position).answerType){
            Question.TYPE_SHORT_ANSWER -> {
                holder.type.text = "Answer TYPE: SHORT ANSWER"
            }
            Question.TYPE_NUMBER -> {
                holder.type.text = "ANSWET TYPE: NUMBER"
            }
            Question.TYPE_TIME -> {
                holder.type.text = "ANSWET TYPE: TIME"
            }
        }
    }

    override fun getItemCount(): Int {
        return questionList.size
    }
}