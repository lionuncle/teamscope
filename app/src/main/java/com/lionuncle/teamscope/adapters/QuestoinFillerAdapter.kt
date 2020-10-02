package com.lionuncle.teamscope.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.models.Question

class QuestionFillerAdapter(val questionList: ArrayList<Question>) : RecyclerView.Adapter<QuestionFillerAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.QuestionFillerListItemLayoutTitleText)
        var shortAnswer: EditText = itemView.findViewById(R.id.QuestionFillerListItemLayoutShortAnswerText)
        var number: EditText = itemView.findViewById(R.id.QuestionFillerListItemLayoutNumberText)
        var time: EditText = itemView.findViewById(R.id.QuestionFillerListItemLayoutTimeText)
        var baseView: ConstraintLayout = itemView.findViewById(R.id.QuestionFillerListItemLayoutBaseView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_filler_list_item_layout, parent, false)
        return QuestionViewHolder(v)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.title.setText(questionList.get(position).title)
        when(questionList.get(position).answerType){
            Question.TYPE_SHORT_ANSWER -> {
                holder.shortAnswer.visibility = View.VISIBLE
                holder.number.visibility = View.GONE
                holder.time.visibility = View.GONE
            }
            Question.TYPE_NUMBER -> {
                holder.shortAnswer.visibility = View.GONE
                holder.number.visibility = View.VISIBLE
                holder.time.visibility = View.GONE
            }
            Question.TYPE_TIME -> {
                holder.shortAnswer.visibility = View.GONE
                holder.number.visibility = View.GONE
                holder.time.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return questionList.size
    }
}