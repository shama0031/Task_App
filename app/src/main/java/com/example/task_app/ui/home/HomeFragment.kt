package com.example.task_app.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task_app.App
import com.example.task_app.R
import com.example.task_app.databinding.FragmentHomeBinding
import com.example.task_app.model.Task
import com.example.task_app.ui.home.adapter.TaskAdapter
import com.example.task_app.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter = TaskAdapter(this::onClick, this::onClickUpdate)

    private fun onClickUpdate(task: Task) {
        val bundle = bundleOf("Key12" to task)
        findNavController().navigate(R.id.taskFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        setData()
        fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun onClick(task: Task) {
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle(getString(R.string.delete_title))
        alert.setPositiveButton(getString(R.string.yes)) { d, _ ->
            App.db.taskDao().delete(task)
            setData()
        }
        alert.setNegativeButton(getString(R.string.no)) { d, _ ->
            d.cancel()
        }
        alert.create().show()
    }

    private fun setData() {
        val tasks = App.db.taskDao().getAll()
        adapter.addTasks(tasks)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}