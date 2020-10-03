package com.lionuncle.teamscope.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lionuncle.teamscope.models.Form
import com.lionuncle.teamscope.R


class FormAdapter(val formList: ArrayList<Form>) :
    RecyclerView.Adapter<FormAdapter.FormViewHolder>() {

    inner class FormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.FormListItemLayoutTitleText)
        var baseView: LinearLayout = itemView.findViewById(R.id.FormListItemLayoutBaseView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.form_list_item_layout, parent, false)
        return FormViewHolder(v)
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