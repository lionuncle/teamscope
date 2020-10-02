package com.lionuncle.teamscope.views.filler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.adapters.FormAdapter
import com.lionuncle.teamscope.models.Form
import com.lionuncle.teamscope.utils.FireStoreResultForm
import com.lionuncle.teamscope.viewmodel.FormViewModel
import com.lionuncle.teamscope.views.MainActivity


class FormFillerFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: FormViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_form_filler, container, false)
        viewModel = ViewModelProvider(this).get(FormViewModel::class.java)
        recyclerView = view.findViewById(R.id.FragmentFormFillerRecyclerView)

        viewModel.getAllFormsOfUser(MainActivity.userId, object : FireStoreResultForm {

            override fun onFormsGetResult(formsList: ArrayList<Form>) {
                recyclerView.layoutManager = LinearLayoutManager(context);
                recyclerView.setHasFixedSize(true);
                recyclerView.adapter = FormAdapter(formsList)
            }

        })


        return view
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }
}