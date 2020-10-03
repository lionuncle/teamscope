package com.lionuncle.teamscope.views.filler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.adapters.QuestionFillerAdapter
import com.lionuncle.teamscope.databinding.FragmentCreateFormBInding
import com.lionuncle.teamscope.databinding.FragmentFormFillBinding
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
        val binding = DataBindingUtil.inflate<FragmentFormFillBinding>(
            inflater,
            R.layout.fragment_form_fill,
            container,
            false
        )
        formId = arguments?.getString("formId").toString()
        formTitle = arguments?.getString("formTitle").toString()
        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        recyclerView = binding.FragmentFormFillFormRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);

        binding.FragmentFormFillFormTitleText.text = formTitle

        viewModel.getAllQuestionsOfForm(formId, object : FireStoreResultQuestion {
            override fun onQuestionGetResult(questionsList: ArrayList<Question>) {
                recyclerView.adapter = QuestionFillerAdapter(questionsList)
            }
        })


        binding.FragmentFormFillFormDoneBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }
}