package com.lionuncle.teamscope.views.builder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.models.Question
import com.lionuncle.teamscope.viewmodel.QuestionViewModel


class AddQuestionFragment : Fragment() {

    lateinit var currFormId: String
    lateinit var viewModel: QuestionViewModel
    lateinit var title: EditText
    lateinit var shortAnswer: TextView
    lateinit var number: TextView
    lateinit var time: TextView
    lateinit var spinner: Spinner
    lateinit var saveBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_question_dialog, container, false)
        title = view.findViewById(R.id.QuestionFillerListItemLayoutTitleText)
        shortAnswer = view.findViewById(R.id.QuestionFillerListItemLayoutShortAnswerText)
        number = view.findViewById(R.id.QuestionFillerListItemLayoutNumberText)
        time = view.findViewById(R.id.QuestionFillerListItemLayoutTimeText)
        spinner = view.findViewById(R.id.FragmentAddQuestionSpinner)
        saveBtn = view.findViewById(R.id.FragmentAddQuestionDoneBtn)
        viewModel = ViewModelProvider(this).get(QuestionViewModel()::class.java)
        currFormId = arguments?.getString("currFormId").toString()


        spinner.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position){
                    0 -> {
                        shortAnswer.visibility = View.VISIBLE
                        number.visibility = View.GONE
                        time.visibility = View.GONE
                    }
                    1 -> {
                        shortAnswer.visibility = View.GONE
                        number.visibility = View.VISIBLE
                        time.visibility = View.GONE
                    }
                    2 -> {
                        shortAnswer.visibility = View.GONE
                        number.visibility = View.GONE
                        time.visibility = View.VISIBLE
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        saveBtn.setOnClickListener {
            when (spinner.selectedItemPosition){
                 0 -> {
                     viewModel.createNewQuestion(currFormId,title.text.toString(),Question.TYPE_SHORT_ANSWER)

//                     findNavController().previousBackStackEntry?.savedStateHandle?.set("type",
//                         Question.TYPE_SHORT_ANSWER
//                     )
                     findNavController().popBackStack()
                 }
                1 -> {
                    viewModel.createNewQuestion(currFormId,title.text.toString(),Question.TYPE_NUMBER)
//
//                    findNavController().previousBackStackEntry?.savedStateHandle?.set("type",
//                        Question.TYPE_NUMBER
//                    )
                    findNavController().popBackStack()
                }
                2 -> {
                    viewModel.createNewQuestion(currFormId,title.text.toString(),Question.TYPE_TIME)

//                    findNavController().previousBackStackEntry?.savedStateHandle?.set("type",
//                        Question.TYPE_TIME
//                    )
                    findNavController().popBackStack()
                }
            }
        }

        return view
    }

}