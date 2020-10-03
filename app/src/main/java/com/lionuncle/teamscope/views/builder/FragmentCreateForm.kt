package com.lionuncle.teamscope.views.builder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.adapters.QuestionBuilderAdapter
import com.lionuncle.teamscope.models.Form
import com.lionuncle.teamscope.models.Question
import com.lionuncle.teamscope.utils.FireStoreResultForm
import com.lionuncle.teamscope.utils.FireStoreResultQuestion
import com.lionuncle.teamscope.viewmodel.QuestionViewModel
import kotlin.collections.ArrayList

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
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_create_form, container, false)
        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        val saveBtn: Button = v.findViewById(R.id.FragmentCreateFormSaveBtn)
        val addBtn: LottieAnimationView = v.findViewById(R.id.FragmentCreateFormAddBtn)
        recyclerView = v.findViewById(R.id.FragmentCreateFormRecyclerView)
        formTitle = v.findViewById(R.id.FragmentCreateFormTitleText)
        currentFormId = arguments?.getString("currFormId").toString()
        addBtn.setOnClickListener {
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


        saveBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        return v
    }

    override fun onResume() {
        super.onResume()
        val fTitle = arguments?.getString("formTitle")
        if (fTitle != null) formTitle.text = "Form: $fTitle"
        recyclerView.adapter?.notifyDataSetChanged()

    }


}