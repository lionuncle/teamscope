package com.lionuncle.teamscope

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.lionuncle.teamscope.viewmodel.FormViewModel
import java.time.Duration
import java.util.*

class FragmentCreateForm : Fragment() {

     lateinit var viewModel: FormViewModel
    lateinit var formTitle: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_create_form, container, false)
        viewModel = ViewModelProvider(this).get(FormViewModel::class.java)
        val saveBtn: Button = v.findViewById(R.id.FragmentCreateFormSaveBtn)
        val addBtn: LottieAnimationView = v.findViewById(R.id.FragmentCreateFormAddBtn)
        val recyclerView: RecyclerView = v.findViewById(R.id.FragmentCreateFormRecyclerView)
        formTitle = v.findViewById(R.id.FragmentCreateFormTitleText)
        addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_createFormFragment_to_addQuestionDialogFragment)
        }
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("type")?.observe(
            viewLifecycleOwner) { result ->
            // Do something with the result.
            when (result){
                Option.TYPE_SHORT_ANSWER -> Toast.makeText(context,"short answer",Toast.LENGTH_SHORT).show()
                Option.TYPE_NUMBER -> Toast.makeText(context,"number",Toast.LENGTH_SHORT).show()
                Option.TYPE_TIME -> Toast.makeText(context,"time",Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getAllFormsOfUser("Heelo", object : FireStoreResult {
            override fun onFormsGetResult(formsList: ArrayList<Form>) {
                recyclerView.setLayoutManager(LinearLayoutManager(context));
                recyclerView.setHasFixedSize(true);

                recyclerView.adapter = FormAdapter(formsList)
            }
        })



        return v
    }
    override fun onResume() {
        super.onResume()
        val fTitle = arguments?.getString("formTitle")
        if (fTitle != null) formTitle.text = fTitle

    }


}