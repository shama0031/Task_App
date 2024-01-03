package com.example.task_app.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?){
    Glide.with(this).load(url).into(this)
}