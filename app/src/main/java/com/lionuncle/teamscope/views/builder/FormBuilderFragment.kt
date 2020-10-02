package com.lionuncle.teamscope.views.builder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.viewmodel.FormViewModel
import com.lionuncle.teamscope.views.MainActivity


class FormBuilder : Fragment() {

    lateinit var viewModel: FormViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        try {
            viewModel =  ViewModelProvider(this).get(FormViewModel::class.java)
        }catch (e: Exception){
            Toast.makeText(context,"Please enter title",Toast.LENGTH_SHORT).show()
        }
        val v: View = inflater.inflate(R.layout.fragment_form_builder, container, false)
        val nextBtn: LottieAnimationView = v.findViewById(R.id.FragmentFormBuilderNextBtn)
        val titleText = v.findViewById<TextView>(R.id.FragmentFormBuilderFormTitleText)

        nextBtn.setOnClickListener {
            if (titleText.text.toString() == ""){ Toast.makeText(context,"Please enter title",Toast.LENGTH_SHORT).show(); return@setOnClickListener}
            val bundle = bundleOf("formTitle" to titleText.text.toString())


            val formId = viewModel.createNewForm(MainActivity.userId,titleText.text.toString())

            bundle.putString("currFormId",formId)

            findNavController().navigate(R.id.action_FormBuilder_to_createFormFragment,bundle)
        }
        // We use a String here, but any type that can be put in a Bundle is supported



        return v
    }
}