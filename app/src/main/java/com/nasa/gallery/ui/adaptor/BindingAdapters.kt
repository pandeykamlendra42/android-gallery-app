package com.nasa.gallery.ui.adaptor

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.nasa.gallery.data.model.Image
import java.text.SimpleDateFormat
import java.util.*


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
    @JvmStatic
    @BindingAdapter("setTime")
    fun bindTime(textView: TextView, time: String) {
        val format = SimpleDateFormat("yyyy-mm-dd HH:mm")
        val date1: Date = format.parse(time)
        var newFormat = SimpleDateFormat("hh:mm aa");
        var formattedDate = newFormat.format(date1)
        textView.text = formattedDate
    }
    @JvmStatic
    @BindingAdapter("setAmount")
    fun bindAmount(textView: TextView, amount: String) {
        textView.text = "\u20B9".plus(" ").plus(amount)
    }
    @JvmStatic
    @BindingAdapter("setTimeDiff")
    fun bindTimeDiff(textView: TextView, image: Image) {
        val format = SimpleDateFormat("yyyy-mm-dd HH:mm")
        val date1: Date = format.parse(image.date)
        textView.text = image.date
    }
}