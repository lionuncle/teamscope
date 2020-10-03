package com.lionuncle.teamscope.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.databinding.FormListItemLayoutBinding
import com.lionuncle.teamscope.databinding.QuestionFillerListItemLayoutBinding
import com.lionuncle.teamscope.models.Question

class QuestionFillerAdapter(val questionList: ArrayList<Question>) :
    RecyclerView.Adapter<QuestionFillerAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(binding: QuestionFillerListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var title: TextView = binding.QuestionFillerListItemLayoutTitleText
        var shortAnswer: EditText = binding.QuestionFillerListItemLayoutShortAnswerText
        var number: EditText = binding.QuestionFillerListItemLayoutNumberText
        var time: EditText = binding.QuestionFillerListItemLayoutTimeText

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding = DataBindingUtil.inflate<QuestionFillerListItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.question_filler_list_item_layout,
            parent,
            false
        )
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.title.setText(questionList.get(position).title)
        when (questionList.get(position).answerType) {
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