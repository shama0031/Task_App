package com.example.task_app.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.task_app.R
import com.example.task_app.databinding.ItemTaskBinding
import com.example.task_app.model.Task
import com.example.task_app.ui.task.TaskFragment

class TaskAdapter(private val onClick: (Task) -> Unit, private val onClickUpdate: (Task) -> Unit): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val list = arrayListOf<Task>()



    fun addTasks(tasks:List<Task>){
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.
        from(parent.context)
            ,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list.get(position))
    }
    private fun navigateToTaskFragment(){
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root){

        fun bind(task: Task) = with(binding){
            tvTitle.text = task.title
            tvDesc.text = task.desc
            itemView.setOnLongClickListener() {
                onClick(task)
                false
            }
            itemView.setOnClickListener {
                onClickUpdate(task)
            }
        }
    }
}