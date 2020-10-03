package com.lionuncle.teamscope.views.builder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.adapters.QuestionBuilderAdapter
import com.lionuncle.teamscope.databinding.FragmentCreateFormBInding
import com.lionuncle.teamscope.models.Question
import com.lionuncle.teamscope.utils.FireStoreResultQuestion
import com.lionuncle.teamscope.viewmodel.QuestionViewModel

class FragmentCreateForm : Fragment() {

    lateinit var viewModel: QuestionViewModel
    lateinit var formTitle: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var currentFormId: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCreateFormBInding>(
            inflater,
            R.layout.fragment_create_form,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        recyclerView = binding.FragmentCreateFormRecyclerView
        formTitle = binding.FragmentCreateFormTitleText
        currentFormId = arguments?.getString("currFormId").toString()
        binding.FragmentCreateFormAddBtn.setOnClickListener {
            val bundle = bundleOf("currFormId" to currentFormId)
            findNavController().navigate(
                R.id.action_createFormFragment_to_addQuestionDialogFragment,
                bundle
            )
        }
//        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("type")
//            ?.observe(
//                viewLifecycleOwner
//            ) { result ->
//                // Do something with the result.
//                when (result) {
//                    Question.TYPE_SHORT_ANSWER -> viewModel.createNewQuestion(currentFormId,)
//                    Question.TYPE_NUMBER -> Toast.makeText(context, "number", Toast.LENGTH_SHORT)
//                        .show()
//                    Question.TYPE_TIME -> Toast.makeText(context, "time", Toast.LENGTH_SHORT).show()
//                }
//            }
        viewModel.getAllQuestionsOfForm(currentFormId, object : FireStoreResultQuestion {

            override fun onQuestionGetResult(questionsList: ArrayList<Question>) {

                recyclerView.setLayoutManager(LinearLayoutManager(context));
                recyclerView.setHasFixedSize(true);

                recyclerView.adapter = QuestionBuilderAdapter(questionsList)
            }

        })


        binding.FragmentCreateFormSaveBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onResume() {

        super.onResume()
        val fTitle = arguments?.getString("formTitle")
        if (fTitle != null) formTitle.text = "Form: $fTitle"
        recyclerView.adapter?.notifyDataSetChanged()

    }


}