package com.lionuncle.teamscope

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController


class AddQuestionDialogFragment : Fragment() {

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
        title = view.findViewById(R.id.FragmentAddQuestionTitleText)
        shortAnswer = view.findViewById(R.id.FragmentAddQuestionShortAnsText)
        number = view.findViewById(R.id.FragmentAddQuestionNumberText)
        time = view.findViewById(R.id.FragmentAddQuestionTimeText)
        spinner = view.findViewById(R.id.FragmentAddQuestionSpinner)
        saveBtn = view.findViewById(R.id.FragmentAddQuestionDoneBtn)


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
                     findNavController().previousBackStackEntry?.savedStateHandle?.set("type", Option.TYPE_SHORT_ANSWER)
                     findNavController().popBackStack()
                 }
                1 -> {
                    findNavController().previousBackStackEntry?.savedStateHandle?.set("type", Option.TYPE_NUMBER)
                    findNavController().popBackStack()
                }
                2 -> {
                    findNavController().previousBackStackEntry?.savedStateHandle?.set("type", Option.TYPE_TIME)
                    findNavController().popBackStack()
                }
            }
        }

        return view
    }

}