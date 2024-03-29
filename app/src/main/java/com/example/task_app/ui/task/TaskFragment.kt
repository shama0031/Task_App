package com.example.task_app.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.task_app.App
import com.example.task_app.R
import com.example.task_app.databinding.FragmentTaskBinding
import com.example.task_app.model.Task

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        task = arguments?.getSerializable("Key12") as Task?
        binding.etTitle.setText(task?.title)
        binding.etDesc.setText(task?.title)

        if (task != null) {
            binding.btnSave.text = "Update"
            binding.btnSave.setOnClickListener {
                val task = task?.copy(
                    title = binding.etTitle.text.toString(),
                    desc = binding.etDesc.text.toString()

                )
                App.db.taskDao().update(task!!)
                findNavController().navigateUp()
            }
        } else {
            binding.btnSave.setOnClickListener {
                val task = Task(
                    title = binding.etTitle.text.toString(),
                    desc = binding.etDesc.text.toString()

                )
                App.db.taskDao().insert(task)
                findNavController().navigateUp()
            }
        }
    }

}
