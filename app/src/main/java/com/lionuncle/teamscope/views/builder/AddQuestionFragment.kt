package com.lionuncle.teamscope.views.builder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.databinding.FragmentAddQuestionBinding
import com.lionuncle.teamscope.models.Question
import com.lionuncle.teamscope.viewmodel.QuestionViewModel


class AddQuestionFragment : Fragment() {

    lateinit var viewModel: QuestionViewModel
    lateinit var currFormId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAddQuestionBinding>(
            inflater,
            R.layout.fragment_add_question,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(QuestionViewModel()::class.java)
        currFormId = arguments?.getString("currFormId").toString()


        binding.FragmentAddQuestionSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    when (position) {
                        0 -> {
                            binding.QuestionFillerListItemLayoutShortAnswerText.visibility =
                                View.VISIBLE
                            binding.QuestionFillerListItemLayoutNumberText.visibility = View.GONE
                            binding.QuestionFillerListItemLayoutTimeText.visibility = View.GONE
                        }
                        1 -> {
                            binding.QuestionFillerListItemLayoutShortAnswerText.visibility =
                                View.GONE
                            binding.QuestionFillerListItemLayoutNumberText.visibility = View.VISIBLE
                            binding.QuestionFillerListItemLayoutTimeText.visibility = View.GONE
                        }
                        2 -> {
                            binding.QuestionFillerListItemLayoutShortAnswerText.visibility =
                                View.GONE
                            binding.QuestionFillerListItemLayoutNumberText.visibility = View.GONE
                            binding.QuestionFillerListItemLayoutTimeText.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        binding.FragmentAddQuestionDoneBtn.setOnClickListener {
            when (binding.FragmentAddQuestionSpinner.selectedItemPosition) {
                0 -> {
                    viewModel.createNewQuestion(
                        currFormId,
                        binding.QuestionFillerListItemLayoutTitleText.text.toString(),
                        Question.TYPE_SHORT_ANSWER
                    )

//                     findNavController().previousBackStackEntry?.savedStateHandle?.set("type",
//                         Question.TYPE_SHORT_ANSWER
//                     )
                    findNavController().popBackStack()
                }
                1 -> {
                    viewModel.createNewQuestion(
                        currFormId,
                        binding.QuestionFillerListItemLayoutTitleText.text.toString(),
                        Question.TYPE_NUMBER
                    )
//
//                    findNavController().previousBackStackEntry?.savedStateHandle?.set("type",
//                        Question.TYPE_NUMBER
//                    )
                    findNavController().popBackStack()
                }
                2 -> {
                    viewModel.createNewQuestion(
                        currFormId,
                        binding.QuestionFillerListItemLayoutTitleText.text.toString(),
                        Question.TYPE_TIME
                    )

//                    findNavController().previousBackStackEntry?.savedStateHandle?.set("type",
//                        Question.TYPE_TIME
//                    )
                    findNavController().popBackStack()
                }
            }
        }

        return binding.root
    }

}