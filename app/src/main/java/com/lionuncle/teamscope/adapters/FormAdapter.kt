package com.lionuncle.teamscope.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.databinding.FormListItemLayoutBinding
import com.lionuncle.teamscope.models.Form


class FormAdapter(private val formList: ArrayList<Form>) :
    RecyclerView.Adapter<FormAdapter.FormViewHolder>() {

    inner class FormViewHolder(binding: FormListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var title: TextView = binding.FormListItemLayoutTitleText
        var baseView: LinearLayout = binding.FormListItemLayoutBaseView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val binding = DataBindingUtil.inflate<FormListItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.form_list_item_layout,
            parent,
            false
        )
        return FormViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        holder.title.setText(formList.get(position).title)
        holder.baseView.setOnClickListener(View.OnClickListener {
            val bundle = bundleOf("formTitle" to holder.title.text.toString())
            bundle.putString("formId", formList.get(position).id)
            holder.itemView.findNavController()
                .navigate(R.id.action_FormFillerFragment_to_fragmentFormFill, bundle)
        })
    }

    override fun getItemCount(): Int {
        return formList.size
    }
}