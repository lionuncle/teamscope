package com.lionuncle.teamscope.views.filler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.adapters.QuestionFillerAdapter
import com.lionuncle.teamscope.models.Question
import com.lionuncle.teamscope.utils.FireStoreResultQuestion
import com.lionuncle.teamscope.viewmodel.FormViewModel
import com.lionuncle.teamscope.viewmodel.QuestionViewModel

class FragmentFormFill : Fragment() {

    lateinit var formId: String
    lateinit var formTitle: String
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_form_fill, container, false)
        formId = arguments?.getString("formId").toString()
        formTitle = arguments?.getString("formTitle").toString()
        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        recyclerView = view.findViewById(R.id.FragmentFormFillFormRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);

        val formTitleText: TextView = view.findViewById(R.id.FragmentFormFillFormTitleText)
        formTitleText.text = formTitle

        viewModel.getAllQuestionsOfForm(formId, object : FireStoreResultQuestion {
            override fun onQuestionGetResult(questionsList: ArrayList<Question>) {
                recyclerView.adapter = QuestionFillerAdapter(questionsList)
            }
        })


        val doneBtn: Button = view.findViewById(R.id.FragmentFormFillFormDoneBtn)
        doneBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }
}