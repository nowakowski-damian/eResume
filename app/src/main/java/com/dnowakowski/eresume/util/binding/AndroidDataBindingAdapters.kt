package com.dnowakowski.eresume.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Damian Nowakowski on 27 January 2020
 */

@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: List<T>?) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(data.orEmpty())
    }
}

@BindingAdapter("imgUrl")
fun loadImage(view: ImageView, url: String?) {
    if( !url.isNullOrEmpty() ) {
        Glide.with(view).load(url).into(view)
    }
}

@BindingAdapter("imgUrlCircled")
fun loadImageCircled(view: ImageView, url: String?) {
    if( !url.isNullOrEmpty() ) {
        Glide.with(view).load(url)
            .apply(RequestOptions.circleCropTransform())
            .into(view)
    }
}
