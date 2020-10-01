package com.lionuncle.teamscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView


class FormBuilder : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v: View = inflater.inflate(R.layout.fragment_form_builder, container, false)
        val nextBtn: LottieAnimationView = v.findViewById(R.id.FragmentFormBuilderNextBtn)
        val titleText = v.findViewById<TextView>(R.id.FragmentFormBuilderFormTitleText)

        nextBtn.setOnClickListener {
            if (titleText.text.toString() == ""){ Toast.makeText(context,"Please enter title",Toast.LENGTH_SHORT).show(); return@setOnClickListener}
            val bundle = bundleOf("formTitle" to titleText.text.toString())
            findNavController().navigate(R.id.action_FormBuilder_to_createFormFragment,bundle)
        }
        // We use a String here, but any type that can be put in a Bundle is supported


        return v
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController();
        // We use a String here, but any type that can be put in a Bundle is supported
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("type")?.observe(
            viewLifecycleOwner) { result ->
            // Do something with the result.
            Toast.makeText(context,"Hi",Toast.LENGTH_SHORT).show()
        }
    }
}