package com.example.task_app.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.task_app.databinding.ItemOnboardingBinding
import com.example.task_app.model.OnBoarding
import com.example.task_app.utils.loadImage

class OnBoardingAdapter(private val onClick:()->Unit): Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf(
        OnBoarding("https://e7.pngegg.com/pngimages/215/344/png-clipart-computer-icons-task-management-action-item-compliance-icon-angle-text.png",
            "Title1",
            "Desc1"),
        OnBoarding("https://png.pngtree.com/png-clipart/20220530/original/pngtree-checklist-note-product-for-buying-payment-png-image_7768972.png",
            "Title2",
            "Desc2"),
        OnBoarding("https://png.pngtree.com/png-clipart/20220719/original/pngtree-task-list-png-image_8368036.png",
            "Title3",
            "Desc3")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root){
        fun bind(onBoarding: OnBoarding) = with(binding) {
            tvTitle.text = onBoarding.title
            tvDesc.text = onBoarding.desc
            ivBoard.loadImage(onBoarding.image)
            skip.setOnClickListener {
                onClick()
            }
            btnStart.setOnClickListener { onClick() }
            skip.isInvisible = adapterPosition == list.lastIndex
            btnStart.isVisible = adapterPosition == list.lastIndex
        }
    }
}