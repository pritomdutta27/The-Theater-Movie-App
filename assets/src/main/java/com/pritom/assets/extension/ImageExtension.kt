package com.pritom.assets.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pritom.assets.R


fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this).load(url).placeholder(R.drawable.no_image_placeholder)
            .error(R.drawable.no_image_placeholder).into(this)
    } else {
        Glide.with(this).load(R.drawable.no_image_placeholder).into(this)
    }
}