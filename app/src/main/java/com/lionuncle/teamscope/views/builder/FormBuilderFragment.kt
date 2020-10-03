package com.lionuncle.teamscope.views.builder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lionuncle.teamscope.R
import com.lionuncle.teamscope.databinding.FragmentFormBuilderBinding
import com.lionuncle.teamscope.viewmodel.FormViewModel
import com.lionuncle.teamscope.views.MainActivity


class FormBuilder : Fragment() {

    lateinit var viewModel: FormViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentFormBuilderBinding>(
            inflater,
            R.layout.fragment_form_builder,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(FormViewModel::class.java)

        binding.FragmentFormBuilderNextBtn.setOnClickListener {
            if (binding.FragmentFormBuilderFormTitleText.text.toString() == "") {
                Toast.makeText(context, "Please enter title", Toast.LENGTH_SHORT)
                    .show(); return@setOnClickListener
            }
            val bundle =
                bundleOf("formTitle" to binding.FragmentFormBuilderFormTitleText.text.toString())

            val formId = viewModel.createNewForm(
                MainActivity.userId,
                binding.FragmentFormBuilderFormTitleText.text.toString()
            )

            bundle.putString("currFormId", formId)

            findNavController().navigate(R.id.action_FormBuilder_to_createFormFragment, bundle)
        }

        return binding.root
    }
}