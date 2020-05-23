package com.rishika.newsapp.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Rishika on 23/05/20.
 */
@BindingAdapter("loadImage")
fun loadImage(iv: ImageView, url: String?) {
    if (url.isNullOrEmpty())
        return
    else
        Glide.with(iv)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(iv)
}

@BindingAdapter("showDateAndTime", "errorText", "dateFormat", requireAll = false)
fun showDateAndTime(
    tv: TextView,
    date: Date?,
    errorText: String? = null,
    dateFormat: String? = null
) {
    if (date == null) {
        tv.text = errorText ?: "NuN"
        return
    }
    val simpleDateFormat = SimpleDateFormat(
        dateFormat ?: "dd-MMM-yyyy h:mm a",
        Locale.getDefault()
    )
    tv.text = simpleDateFormat.format(date)
}