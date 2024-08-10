package com.pritom.assets.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pritom.assets.R


fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this).load(url).placeholder(R.drawable.movie_nav_ic)
            .error(R.drawable.settings_nav_ic).into(this)
    } else {
        Glide.with(this).load(R.drawable.settings_nav_ic).into(this)
    }
}