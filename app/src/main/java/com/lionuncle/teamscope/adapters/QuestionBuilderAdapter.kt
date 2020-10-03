package com.lionuncle.teamscope.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.databinding.FormListItemLayoutBinding
import com.lionuncle.teamscope.databinding.QuestionBuilderListItemLayoutBinding
import com.lionuncle.teamscope.models.Question

class QuestionBuilderAdapter(private val questionList: ArrayList<Question>) :
    RecyclerView.Adapter<QuestionBuilderAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(binding: QuestionBuilderListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var title: TextView = binding.QuestionBuilderListItemLayoutTitleText
        var type: TextView = binding.QuestionBuilderListItemLayoutTypeText

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding = DataBindingUtil.inflate<QuestionBuilderListItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.question_builder_list_item_layout,
            parent,
            false
        )
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.title.text = questionList.get(position).title
        when (questionList.get(position).answerType) {
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