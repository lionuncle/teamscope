package com.lionuncle.teamscope

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FormAdapter(val formList: ArrayList<Form>) : RecyclerView.Adapter<FormAdapter.FormViewHolder>() {

    inner class FormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.FormListItemLayoutTitleText)
        var baseView: LinearLayout = itemView.findViewById(R.id.FormListItemLayoutBaseView)

//        fun FormViewHolder(itemView: View) {
//            super.itemView
//            title = itemView.findViewById(R.id.FormListItemLayoutTitleText)
//            baseView = itemView.findViewById(R.id.FormListItemLayoutBaseView)
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.form_list_item_layout, parent, false)
        return FormViewHolder(v)
    }

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        holder.title.setText(formList.get(position).title)
//        holder.baseView.setOnClickListener(View.OnClickListener {
//            val i = Intent(holder.baseView.getContext(), CourseEnrollActivity::class.java)
//            i.putExtra("ClickedId", formList.get(position).id)
//            holder.baseView.getContext().startActivity(i)
//        })
    }

    override fun getItemCount(): Int {
        return formList.size
    }
}