package com.example.ss1_githubuser.tools

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .circleCrop()
        .into(this)
}